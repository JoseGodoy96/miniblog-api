function UserForm({nuevoUsername, setNuevoUsername, nuevoEmail, setNuevoEmail, crearUsuario}) {
	return (
		<>
			<input value={nuevoUsername} onChange={(e) => setNuevoUsername(e.target.value)} type="text" placeholder='Nombre Usuario'/>
			<input value={nuevoEmail} onChange={(e) => setNuevoEmail(e.target.value)} type="email" placeholder='Email'/>
			<button onClick={crearUsuario}>Enviar</button>
		</>
	)
}

export default UserForm