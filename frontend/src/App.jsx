import { useState, useEffect } from 'react'
import UserList from './components/UserList'
import PostList from './components/PostList'
import UserForm from './components/UserForm'
import PostForm from './components/PostForm'

function App() {
	const [users, setUsers] = useState([])
	const [posts, setPosts] = useState([])
	const [nuevoUsername, setNuevoUsername] = useState('')
	const [nuevoEmail, setNuevoEmail] = useState('')
	const [nuevoTitle, setNuevoTitle] = useState('')
	const [nuevoContent, setNuevoContent] = useState('')
	const [autorSeleccionado, setAutorSeleccionado] = useState('')
	const [editandoId, setEditandoId] = useState(null)
	

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

	const borrarUsuario = (id) => {
		fetch(`http://localhost:8080/api/user/${id}`, {
			method: 'DELETE'
		})
		.then((response) => {
			if (!response.ok) {
				throw new Error('No se pudo borrar')
			}
			setUsers(users.filter((user) => user.id !== id))
		})
		.catch((error) => console.error('Error al borrar usuario:', error))
	}

	const borrarPost = (id) => {
		fetch(`http://localhost:8080/api/post/${id}`, {
			method: 'DELETE'
		})
		.then((response) => {
			if (!response.ok) {
				throw new Error('No se pudo borrar')
			}
			setPosts(posts.filter((post) => post.id !== id))
		})
		.catch((error) => console.error('Error al borrar usuario:', error))
	}

	const empezarEdicion = (user) => {
		setNuevoUsername(user.username)
		setNuevoEmail(user.email)
		setEditandoId(user.id)
	}


	return (
	<>
		<h1>Mini Blog</h1>
		<h2>Usuarios</h2>
		<UserList users={users} borrarUsuario={borrarUsuario} />
		<h2>Nuevo usuario</h2>
		<UserForm
			nuevoUsername={nuevoUsername}
			setNuevoUsername={setNuevoUsername}
			nuevoEmail={nuevoEmail}
			setNuevoEmail={setNuevoEmail}
			crearUsuario={crearUsuario}
			/>
		<h2>Posts</h2>
		<PostList posts={posts} borrarPost={borrarPost} />
		<h2>Nuevo posts</h2>
		<PostForm
			nuevoTitle={nuevoTitle}
			setNuevoTitle={setNuevoTitle}
			nuevoContent={nuevoContent}
			setNuevoContent={setNuevoContent}
			autorSeleccionado={autorSeleccionado}
			setAutorSeleccionado={setAutorSeleccionado}
			users={users}
			crearPost={crearPost}
		/>
    </>
	)
}

export default App
