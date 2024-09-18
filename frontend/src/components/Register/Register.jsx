import "./Register.css";
import { useNavigate } from "react-router-dom";
import { useRef } from "react";
import axios from 'axios';
const Register = () => {
    let username = useRef();
    let email = useRef();
    let password = useRef();
    const nav = useNavigate();

    const handleSubmit = async(e) => {
        e.preventDefault();
        try{
            const response = await axios.post("http://localhost:8080/auth/register",{
                username:username.current.value,
                email:email.current.value,
                password:password.current.value
            });
            alert(response.data);
            password.current.value = '';
            username.current.value = '';
            email.current.value = '';
            nav("/");
        }catch(err){
            console.log(err);
        }
    }
    return <>
        <div className="r-container">
            <form className="form" onSubmit={handleSubmit}>
                <p className="form-title">Sign in to your account</p>
                <div className="input-container">
                    <input type="text" placeholder="Enter your name" ref={username}/>
                </div>
                <div className="input-container">
                    <input type="email" placeholder="Enter email" ref={email}/>
                    <span></span>
                </div>
                <div className="input-container">
                    <input type="password" placeholder="Enter password" ref={password}/>
                </div>
                <button type="submit" className="submit">
                    Sign in
                </button>

                <p className="signup-link">
                    Already have an account? <a href="/">Login</a>
                </p>
            </form>
        </div>
    </>
}
export default Register;