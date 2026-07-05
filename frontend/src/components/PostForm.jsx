function PostForm({ nuevoTitle, setNuevoTitle, nuevoContent, setNuevoContent, autorSeleccionado, setAutorSeleccionado, users, crearPost }) {
	return (
		<>
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

export default PostForm