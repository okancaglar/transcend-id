"use client";

import {useState} from "react";
import { useRouter } from "next/navigation";
import "@/styles/css/refugee-registration.css";
export default function Register() {

    const REGISTER_URL = "http://localhost:8080/blockchain/api/immigrant/register";
    const router = useRouter();

    const [formData, setFormData] = useState({name:"", surname: "", ethnicity: ""});
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");

    const handleChange = (e) => {
        setFormData({...formData, [e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsLoading(true);
        setError("");

        if (!formData.name.trim() || !formData.surname.trim() || !formData.ethnicity.trim()) {
            throw new Error("Please fill the all field!");
        }

        try {
            const response = await fetch(REGISTER_URL,
                {
                    method: "POST",
                    headers: {
                        "Authorization": `Bearer ${localStorage.getItem("jwtToken")}`,
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(formData)
                });

            const data = await response.json();
            console.log(data);

            if (!response.ok) {
                throw new Error("registering immigration is failed");
            }
            if (!data.uuid && !data.privateKey) {
                throw new Error("invalid data from server");
            }
            console.log(data.uuid + "--" + data.privateKey);
            localStorage.setItem("uuid", data.uuid);
            localStorage.setItem("privateKey", data.privateKey);
            setFormData({name: "", surname: "", ethnicity: ""});
            await router.push("/immigrant/register-response");
        } catch (e) {
            console.error("Register error", e);
            setError(e);
        } finally {
            setIsLoading(false);
        }

    }



    return (
        <>
            <div className="logo-wrapper">
                <h1>TranscendID</h1>
            </div>
            <div className="wrapper">
                <form onSubmit={handleSubmit}>
                    <h2>Refugee Registration</h2>
                    <div className="input-box">
                        <label>Name</label>
                        <input type="text" placeholder="Name" name="name" value={formData.name} onChange={handleChange} required/>
                    </div>

                    <div className="input-box">
                        <label>Surname</label>
                        <input type="text" placeholder="Surname" name="surname" value={formData.surname} onChange={handleChange} required/>
                    </div>
                    <div className="input-box">
                        <label>Ethnicity</label>
                        <input type="text" placeholder="Ethnicity" name="ethnicity" value={formData.ethnicity} onChange={handleChange} required/>
                    </div>
                    <button type="submit" className="btn">Register</button>
                    {error && <div className="error-message">{error}</div>}
                </form>
            </div>
        </>
    );


}