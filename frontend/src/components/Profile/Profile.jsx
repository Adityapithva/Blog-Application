import Navbar from "../Navbar/Navbar";
import axios from "axios";
import { useState, useEffect } from "react";
import "./Profile.css";
const Profile = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = sessionStorage.getItem("jwt");
                const response = await axios.get("http://localhost:8080/user/myprofile", {
                    headers: {
                        "Authorization": `Bearer ${token}`
                    }
                });
                setData(response.data);
                console.log(response.data);
            } catch (err) {
                console.log("error fetching data", err);
            }

        }
        fetchData();
    }, []);
    return <>
        <Navbar></Navbar>
    </>
}
export default Profile;