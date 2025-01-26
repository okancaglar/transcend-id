"use client";
import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import "@/styles/css/login.css";

export default function Home() {
    const loginUrl = "http://localhost:8080/blockchain/api/login";
    const router = useRouter();
    const [formData, setFormData] = useState({ officerId: "", password: "" });
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");

    useEffect(() => {
        // Clear error when form data changes
        setError("");
    }, [formData]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsLoading(true);
        setError("");

        try {
            // Basic validation
            if (!formData.officerId.trim() || !formData.password.trim()) {
                throw new Error("Please fill in all fields");
            }

            const controller = new AbortController();
            const timeoutId = setTimeout(() => controller.abort(), 10000); // 10s timeout

            const response = await fetch(loginUrl, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData),
                signal: controller.signal,
            });

            clearTimeout(timeoutId);

            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.message || "Authentication failed");
            }

            if (!data.token) {
                throw new Error("No token received from server");
            }

            localStorage.setItem("jwtToken", data.token);
            await router.push("/home");

        } catch (error) {
            console.error("Login error:", error);
            setError(error.message || "An error occurred during login");
        } finally {
            setIsLoading(false);
        }
    };

    const handleChange = (e) => {
        setFormData(prev => ({
            ...prev,
            [e.target.name]: e.target.value
        }));
    };

    return (
        <>
        <div className="container">
            {/* Header Section */}
            <div className="logo-wrapper">
                <h1>TranscendID</h1>
            </div>
            <h2>BLOCKCHAIN-BASED</h2>
            <h2>REFUGEE IDENTITY MANAGEMENT SYSTEM</h2>
            <br />
            <h3>
                Managing refugee data with integrity and confidentiality is essential but challenging,
            </h3>
            <h3>
                especially in humanitarian contexts where data accuracy and security are critical.
            </h3>
            <br />
            <h3>Our goal is to prevent unauthorized access and data tampering,</h3>
            <h3>
                and to ensure accurate tracking of individual records across borders.
            </h3>
        </div>

            {/* Login Form Section */}
            <div className="wrapper">
                <form onSubmit={handleSubmit}>
                    <h2>Login</h2>

                    <div className="input-box">
                        <label htmlFor="officerId">Officer ID</label>
                        <input type="text" name="officerId" value={formData.officerId} onChange={handleChange} id="officerId" placeholder="Officer ID" required />
                        <i className="bx bxs-user"></i>
                    </div>

                    <div className="input-box">
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" name="password" value={formData.password} onChange={handleChange} placeholder="Password" required />
                        <i className="bx bxs-lock-alt"></i>
                    </div>

                    {/*<div className="remember-forgot">
                        <label>
                            <input type="checkbox" /> Remember me
                        </label>
                        <a href="#">Forgot Password</a>
                    </div>*/}

                    <button type="submit" className="btn">
                        Login
                    </button>
                </form>
            </div>
            </>
    );
}