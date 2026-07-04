import { useState, useEffect } from 'react'

function App() {
	const [users, setUsers] = useState([])
	const [posts, setPosts] = useState([])
	const [nuevoUsername, setNuevoUsername] = useState('')
	const [nuevoEmail, setNuevoEmail] = useState('')
	const [nuevoTitle, setNuevoTitle] = useState('')
	const [nuevoContent, setNuevoContent] = useState('')
	const [autorSeleccionado, setAutorSeleccionado] = useState('')
	

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

	const crearPost = () => {
		if (nuevoTitle.trim() === '' || nuevoContent.trim() === '' || autorSeleccionado === '') {
			console.error('Faltan campos por rellenar')
			return
		}

		fetch('http://localhost:8080/api/post', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({title: nuevoTitle, content: nuevoContent, autorId: Number(autorSeleccionado)})
		})
		.then((response) => {
			if (!response.ok) {
				throw new Error('El servidor rechazó la peticion')
			}
			return response.json()
		})
		.then((nuevo) => {
			setPosts([...posts, nuevo])
			setNuevoTitle('')
			setNuevoContent('')
			setAutorSeleccionado('')
		})
		.catch((error) => console.error('Error al crear el post', error))
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
		<h2>Nuevo posts</h2>
		<input value={nuevoTitle} onChange={(e) => setNuevoTitle(e.target.value)} type="text" placeholder='Titulo'/>
		<input value={nuevoContent} onChange={(e) => setNuevoContent(e.target.value)} type="text" placeholder='Escribe aqui...'/>
		<select value={autorSeleccionado} onChange={(e) => setAutorSeleccionado(e.target.value)}>
			<option value="">-- Elige un autor --</option>
			{users.map((user) => (
				<option key={user.id} value={user.id}>
					{user.username}
				</option>
			))}
		</select>
		<button onClick={crearPost}>Enviar</button>
    </>
	)
}

export default App
