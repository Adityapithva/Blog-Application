import Navbar from "../Navbar/Navbar";
import axios from "axios";
import { useState, useEffect } from "react";
import SinglePost from "../SinglePost/SinglePost";
import "./Home.css";
const Home = () => {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const token = sessionStorage.getItem("jwt");
                const response = await axios.get("http://localhost:8080/post/reading", {
                    headers: {
                        "Authorization": `Bearer ${token}`,
                    },
                });
                setPosts(response.data);
                console.log(response.data);
            } catch (err) {
                console.log("Error occurred:", err);
            }
        };
        fetchPosts();
    }, []);

    return (
        <>
            <Navbar />
            <div className="m-home">
                {posts.length !== 0 ? (
                    posts.map((post) => (
                        <SinglePost key={post.id} post={post} />
                    ))
                ) : (
                    <h3>No posts found</h3>
                )}
            </div>
        </>
    );
};

export default Home;
