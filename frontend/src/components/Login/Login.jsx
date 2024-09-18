/* eslint-disable react/no-unescaped-entities */
import axios from "axios";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
const Login = () => {
    let email = useRef();
    let password = useRef();
    const nav = useNavigate();
    const handleSubmit = async(e) => {
        e.preventDefault();
        try{
            const response = await axios.post("http://localhost:8080/auth/login",{
                email:email.current.value,
                password:password.current.value
            });
            sessionStorage.setItem("jwt",response.data.token);
            alert(response.data.message);
            nav("/home");
        }catch(err){
            console.log(err);
        }
    }
    return <>
        <div className="r-container">
            <form className="form" onSubmit={handleSubmit}>
                <p className="form-title">Login to your account</p>
                <div className="input-container">
                    <input type="email" placeholder="Enter email" ref={email}/>
                    <span></span>
                </div>
                <div className="input-container">
                    <input type="password" placeholder="Enter password" ref={password}/>
                </div>
                <button type="submit" className="submit">
                    Login
                </button>

                <p className="signup-link">
                    Don't have an account? <a href="/register">Sign up</a>
                </p>
            </form>
        </div>
    </>
}
export default Login;