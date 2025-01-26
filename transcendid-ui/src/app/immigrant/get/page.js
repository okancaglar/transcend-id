"use client";
import "@/styles/css/refugee-registration.css";

import { useSelector } from "react-redux";

export default function Get(){
    const refugee = useSelector((state) => state.refugee);
    return(
        <>
            <div>
                {/* Logo Section */}
                <div className="get-refugee-logo-wrapper">
                    <h1>TranscendID</h1>
                </div>

                {/* Response Wrapper */}
                <div className="get-refugee-wrapper">
                    <h2>Refugee Registration Response</h2>

                    <dl>
                        <div className="content-to-response">
                            <dt>Refugee Id :</dt>
                            <dd>{refugee.uuid}</dd>
                        </div>

                        <div className="content-to-response">
                            <dt>Public Key :</dt>
                            <dd>{refugee.publicKey}</dd>
                        </div>

                        <div className="content-to-response">
                            <dt>Name :</dt>
                            <dd>{refugee.name}</dd>
                        </div>

                        <div className="content-to-response">
                            <dt>Surname :</dt>
                            <dd>{refugee.surname}</dd>
                        </div>

                        <div className="content-to-response">
                            <dt>Ethnicity :</dt>
                            <dd>{refugee.ethnicity}</dd>
                        </div>

                        <div className="content-to-response">
                            <dt>Creation Time :</dt>
                            <dd>{refugee.creationTime}</dd>
                        </div>

                        <div className="content-to-response">
                            <dt>Responsible Officer :</dt>
                            <dd>{refugee.officerId}</dd>
                        </div>
                    </dl>

                    <div className="return-home">
                        <button type="submit" className="return-home-btn">
                            <a href="/home" style={{ textDecoration: "none"}}>
                                Return Home
                            </a>
                        </button>
                    </div>
                </div>
            </div>
        </>
    );


}