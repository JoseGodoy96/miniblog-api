function PostList({ posts, borrarPosts }) {
	return (
	<ul>
		{posts.map((post) => (
        <li key={post.id}>
			{post.title} - {post.content} - {post.autorUsername}
			<button onClick={() => borrarPosts(post.id)}>Eliminar</button>
        </li>
		))}
    </ul>
	)
}

export default PostList