function PostList({ posts }) {
	return (
	<ul>
		{posts.map((post) => (
        <li key={post.id}>
			{post.title} - {post.content} - {post.autorUsername}
        </li>
		))}
    </ul>
	)
}

export default PostList