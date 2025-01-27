"use client";

import "@/styles/css/refugee-log-page.css";
import {useState} from "react";
import {useRouter} from "next/navigation";

export default function Log(){

    const Refugee_LOG_URL = "http://localhost:8080/blockchain/api/location/log";
    const router = useRouter();
    const [formData, setFormData] = useState({immigrantId: "", location: ""});
    const [error, setError] = useState("");


    const handleChange = (e) => {
        setFormData(prevState => ({...prevState,
        [e.target.name]: e.target.value}));
    };


    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        localStorage.setItem("locationLog", formData.immigrantId + "-" + formData.location);

        try {
            // Basic validation
            if (!formData.immigrantId.trim() || !formData.location.trim()) {
                throw new Error("Please fill in all fields");
            }

            localStorage.setItem("locationLog", formData.immigrantId + "-" + formData.location);

            console.log(formData);

            const response = await fetch(Refugee_LOG_URL, {
                method: "POST",
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("jwtToken")}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData),
            });

            const data = await response.text();

            if (!response.ok) {
                throw new Error(data.message || "Authentication failed");
            }
            console.log(data);

            if (!data) {
                throw new Error("No token received from server");
            }

            setFormData({immigrantId: "", location: ""});
            await router.push("/home");

        } catch (error) {
            console.error("Login error:", error);
            setError(error.message || "An error occurred during login");
        }
    };

    return (
        <>
            <div className="logo-wrapper">
                <h1>TranscendID</h1>
            </div>

            {/* Form Wrapper */}
            <div className="wrapper">
                <form>
                    <h2>Refugee Log Page</h2>

                    <div className="input-box">
                        <label>Refugee ID</label>
                        <input
                            type="text"
                            id="refugee-id"
                            name="immigrantId"
                            value={formData.immigrantId}
                            onChange={handleChange}
                            placeholder="Refugee ID"
                            required
                        />
                    </div>

                    <div className="input-box">
                        <label htmlFor="location">Location</label>
                        <input
                            type="location"
                            id="location"
                            name="location"
                            value={formData.location}
                            onChange={handleChange}
                            placeholder="Location"
                            required
                        />
                    </div>

                    <button type="submit" className="btn" onClick={handleSubmit}>
                        Submit
                    </button>

                </form>
            </div>
        </>
    );
}