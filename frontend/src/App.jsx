import { useState, useEffect } from 'react'

function App() {
	const [users, setUsers] = useState([])
	const [posts, setPosts] = useState([])
	const [nuevoUsername, setNuevoUsername] = useState('')
	const [nuevoEmail, setNuevoEmail] = useState('')
	

	useEffect(() => {
		fetch('http://localhost:8080/api/user')
		.then((response) => response.json())
		.then((data) => setUsers(data))
		.catch((error) => console.error('Error al cargar usuarios:', error))
	}, [])

	useEffect(() => {
		fetch('http://localhost:8080/api/post')
		.then((response) => response.json())
		.then((data) => setPosts(data))
		.catch((error) => console.error('Error al cargar los posts', error))
	}, [])

	const crearUsuario = () => {
	if (nuevoUsername.trim() === '' || nuevoEmail.trim() === '') {
    console.error('Faltan campos por rellenar')
	return
	}
	fetch('http://localhost:8080/api/user', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username: nuevoUsername, email: nuevoEmail })
	})
    .then((response) => {
    if (!response.ok) {
        throw new Error('El servidor rechazó la petición')
    }
    return response.json()
    })
    .then((nuevo) => {
		setUsers([...users, nuevo])
		setNuevoUsername('')
		setNuevoEmail('')
    })
    .catch((error) => console.error('Error al crear usuario:', error))
}

	return (
	<>
	<h1>Mini Blog</h1>
		<h2>Usuarios</h2>
		<ul>
			{users.map((user) => (
				<li key={user.id}>
					{user.username} - {user.email}
				</li>
			))}
		</ul>
		<h2>Nuevo usuario</h2>
		<input value={nuevoUsername} onChange={(e) => setNuevoUsername(e.target.value)} type="text" placeholder='Nombre Usuario'/>
		<input value={nuevoEmail} onChange={(e) => setNuevoEmail(e.target.value)} type="email" placeholder='Email'/>
		<button onClick={crearUsuario}>Enviar</button>
		<h2>Posts</h2>
		<ul>
			{posts.map((post) => (
				<li key={post.id}>
					{post.title} - {post.autorUsername}
				</li>
			))}
		</ul>
    </>
	)
}

export default App
