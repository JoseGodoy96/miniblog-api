function UserForm({ nuevoUsername, setNuevoUsername, nuevoEmail, setNuevoEmail, guardarUsuario, editandoId }) {
	let textoBoton;
	
	if (editandoId === null) {
		textoBoton = "Enviar";
	} else {
		textoBoton = "Actualizar";
	}
	
	return (
		<>
			<input value={nuevoUsername} onChange={(e) => setNuevoUsername(e.target.value)} type="text" placeholder='Nombre Usuario'/>
			<input value={nuevoEmail} onChange={(e) => setNuevoEmail(e.target.value)} type="email" placeholder='Email'/>
			<button onClick={guardarUsuario} >{textoBoton}</button>
		</>
	)
}

export default UserForm