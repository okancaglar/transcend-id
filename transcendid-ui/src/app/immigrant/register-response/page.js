"use client";

import "@/styles/css/refugee-registration.css";
import { useRouter} from "next/navigation";

export default function RegisterResponse(){

    const router = useRouter();

    return (
        <>{/* Logo Section */}
                <div className="response-logo-wrapper">
                    <h1>TranscendID</h1>
                </div>

                {/* Response Section */}
                <div className="response-wrapper">
                    <h2>Refugee Registration Response</h2>

                    <dl>
                        <div className="content-to-response">
                            <dt>Refugee ID:</dt>
                            <dd>{ localStorage.getItem("uuid") }</dd>
                        </div>
                        <div className="content-to-response">
                            <dt>Private Key:</dt>
                            <dd>{localStorage.getItem("privateKey")}</dd>
                        </div>
                    </dl>

                    {/* Return Home Button */}
                    <div className="return-home">
                        <button
                            type="button"
                            className="return-home-btn"
                        >
                            <a href="/home" style={{textDecoration: "none"}}>
                            Return Home
                            </a>
                        </button>
                    </div>
                </div>
    </>


    );







}