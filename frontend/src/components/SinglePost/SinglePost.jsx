/* eslint-disable react/prop-types */
import "./SinglePost.css";
const SinglePost = ({post}) => {
    return <>
        <div className="blog-card">
            <div className="card-header">
                <h2>{post.title}</h2>
                <p className="author-name">by {post.author}</p>
                <p className="created-at">{new Date(post.createdAt).toLocaleDateString()}</p>
            </div>

            <div className="card-content">
                <p>{post.content}</p>
            </div>

            <div className="card-footer">
                <div className="tags">
                    {post.tags.map((tag, index) => (
                        <span key={index} className="tag">
                            #{tag}
                        </span>
                    ))}
                </div>
            </div>
        </div>
    </>
}
export default SinglePost;