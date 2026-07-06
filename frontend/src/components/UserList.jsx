function UserList({ users, borrarUsuario }) {
	return (
	<ul>
		{users.map((user) => (
        <li key={user.id}>
			{user.username} - {user.email}
			<button onClick={() => borrarUsuario(user.id)}>Eliminar</button>
        </li>
		))}
    </ul>
	)
}

export default UserList