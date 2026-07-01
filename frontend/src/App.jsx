import { useState, useEffect } from 'react'

function App() {
	const [users, setUsers] = useState([])

	useEffect(() => {
		fetch('http://localhost:8080/api/user')
		.then((response) => response.json())
		.then((data) => setUsers(data))
		.catch((error) => console.error('Error al cargar usuarios:', error))
	}, [])

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
    </>
	)
}

export default App
