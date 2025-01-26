"use client";

import "@/styles/css/get-location.css"
import { useSelector } from "react-redux";


export default function Get(){

    const locations = useSelector((state) => state.locations);

    return (
        <>
            <div className="logo-wrapper">
                <h1>TranscendID</h1>
            </div>

            <div className="wrapper">
                <h2>Get Location</h2>
                <div className="table-wrapper">
                    <br />
                    <table>
                        <thead>
                        <tr>
                            <th>Refugee ID</th>
                            <th>Location</th>
                            <th>Time</th>
                            <th>Officer</th>
                        </tr>
                        </thead>
                        <tbody>
                        {locations.map((loc, idx) => {
                            return (
                                <tr key={idx}>
                                    <td>{loc.immigrantId}</td>
                                    <td>{loc.location}</td>
                                    <td>{loc.timestamp}</td>
                                    <td>{loc.officerId}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>

                <div className="return-home">
                    <button type="submit" className="return-home-btn">
                        <a href="/home" style={{textDecoration: "none"}}>
                            Return Home
                        </a>
                    </button>
                </div>
            </div>
        </>
    );
}