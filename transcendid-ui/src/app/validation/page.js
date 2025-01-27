"use client";

import "@/styles/css/refugee-registration.css";
import { useRouter} from "next/navigation";
import {useSelector} from "react-redux";

export default function RegisterResponse(){
    const val = useSelector((state) => state.validation);

    const router = useRouter();

    return (
        <>{/* Logo Section */}
                <div className="response-logo-wrapper">
                    <h1>TranscendID</h1>
                </div>

                {/* Response Section */}
                <div className="response-wrapper">
                    <h2>Validation Response</h2>

                    <dl>
                        <div className="content-to-response">
                            <dt>Validation Result</dt>
                            <dd>{ val.isValid }</dd>
                        </div>
                    </dl>

                    {/* Return Home Button */}
                    <div className="return-home">
                        <button type="button" className="return-home-btn" >
                            <a href="/home" style={{textDecoration: "none"}}>
                            Return Home
                            </a>
                        </button>
                    </div>
                </div>
    </>


    );







}