function UserList({ users, borrarUsuario, empezarEdicion }) {
	return (
	<ul>
		{users.map((user) => (
        <li key={user.id}>
			{user.username} - {user.email}
			<button onClick={() => empezarEdicion(user)}>Editar</button>
			<button onClick={() => borrarUsuario(user.id)} className="eliminar">Eliminar</button>
        </li>
		))}
    </ul>
	)
}

export default UserList