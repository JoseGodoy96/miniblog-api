function PostList({ posts, borrarPost }) {
	return (
	<ul>
		{posts.map((post) => (
        <li key={post.id}>
			{post.title} - {post.content} - {post.autorUsername}
			<button onClick={() => borrarPost(post.id)} className="eliminar">Eliminar</button>
        </li>
		))}
    </ul>
	)
}

export default PostList