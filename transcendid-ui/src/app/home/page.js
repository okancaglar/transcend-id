"use client";

import "@/styles/css/home.css";
import {useRouter} from "next/navigation";
import {useEffect, useState} from "react";
import { useDispatch } from "react-redux";
import {
    setRefugee,
    updateRefugee,
    clearRefugee,
} from "@/redux/refugeeSlice";
import {
    setRegisterResponse,
    clearRegisterResponse
} from "@/redux/registerResponseSlice";
import {
    setLocations,
    addLocation,
    clearLocations
} from "@/redux/locationSlice";

import {setValidation, clearValidation} from "@/redux/validationSlice";


export default function Page() {

    const GET_REFUGEE_URL = "http://localhost:8080/blockchain/api/immigrant/get";
    const GET_LOCATIONS_URL = "http://localhost:8080/blockchain/api/location/get";
    const VALIDATION_URL = "http://localhost:8080/blockchain/api/immigrant/validation";

    const dispatch = useDispatch();
    const router = useRouter();
    const [refugeeId, setRefugeeId] = useState("");
    const [locationRefugeeId, setLocationRefugeeId] = useState("");
    const [validationData, setValidationData] = useState({immigrantId:"", privateKey: ""});
    const [validationResult, setValidationResult] = useState("false");
    const [error, setError] = useState("");


    useEffect(() => {
        console.log("validationResult changed to:", validationResult);
    }, [validationResult]);



    const validationDataOnChange = (e) => {
        setValidationData(prev => ({
            ...prev,
            [e.target.name]: e.target.value
        }));
    };
    const validationResultOnChange = e => {
        setValidationResult(e.target.value);
    };

    const refugeeIdOnChange = e => {
        setRefugeeId(e.target.value);
    };

    const locationRefugeeIdOnChange = e => {
        setLocationRefugeeId(e.target.value);
    }

    const getRefugeeHandler = async e => {
        e.preventDefault();
        setError("");

        if (!refugeeId.trim()) {
            throw new Error("Please fill the refugee id");
        }

        try {
            console.log(refugeeId);
            const response = await fetch(GET_REFUGEE_URL,
                {
                    method: "POST",
                    headers: {
                        "Authorization": `Bearer ${localStorage.getItem("jwtToken")}`,
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({uuid: refugeeId})
                });

            const refugeeData = await response.json();
            console.log(refugeeData);

            if (!response.ok) {
                throw new Error("registering immigration is failed");
            }
            if (!refugeeData.uuid) {
                throw new Error("invalid data from server");
            }
            console.log(refugeeData);
            dispatch(setRefugee({
                uuid: refugeeData.uuid,
                publicKey:refugeeData.publicKey,
                name: refugeeData.name,
                surname: refugeeData.surname,
                ethnicity: refugeeData.ethnicity,
                creationTime: refugeeData.creationTime,
                officerId: refugeeData.officerId,
            }));

            setRefugeeId("");
            await router.push("/immigrant/get");
        } catch (e) {
            console.error("Register error", e);
            setError(e);
        }
    };

    const getLocationsHandler = async e => {
        e.preventDefault();
        setError("");

        if (!locationRefugeeId.trim()) {
            throw new Error("Please fill the refugee id");
        }

        try {
            console.log(locationRefugeeId);
            const response = await fetch(GET_LOCATIONS_URL,
                {
                    method: "POST",
                    headers: {
                        "Authorization": `Bearer ${localStorage.getItem("jwtToken")}`,
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({uuid: locationRefugeeId})
                });

            const locationsData = await response.json();
            if (!response.ok) {
                throw new Error("registering immigration is failed");
            }

            console.log(locationsData);
            dispatch(setLocations(locationsData));

            setLocationRefugeeId("");

            await router.push("/location/get");
        } catch (e) {
            console.error("Register error", e);
            setError(e);
        }
    };


    const validationHandler = async e => {
        e.preventDefault();
        setError("");


        if (!validationData.immigrantId.trim()) {
            throw new Error("Please fill the refugee id");
        }

        try {
            console.log(validationData.immigrantId);
            const response = await fetch(VALIDATION_URL,
                {
                    method: "POST",
                    headers: {
                        "Authorization": `Bearer ${localStorage.getItem("jwtToken")}`,
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(validationData)
                });

            const validationResponseData = await response.json();
            console.log(validationResponseData);

            if (!response.ok) {
                throw new Error("validation is failed");
            }
            dispatch(setValidation({isValid: validationResponseData.valid + ""}));
            await router.push("/validation");
        } catch (e) {
            console.error("Register error", e);
            setError(e);
        }finally {
            console.log(validationResult);
        }
    };



    return (
        <>
            <div className="logo-wrapper">
                <h1>TranscendID</h1>
            </div>

            <div className="wrapper">

                <div className="home-wrapper">

                    <div className="content">
                       <button type="button" className="reg-log-btn"
                               onClick={async event => {await router.push("/immigrant/register");}}
                       >Refugee Registration</button>
                    </div>

                    <div className="content">
                       <button type="button" className="reg-log-btn"
                               onClick={async event => {await router.push("/location/log");}}
                       >Location Log</button>
                    </div>

                    <div className="content">
                        <form onSubmit={getLocationsHandler}>
                            <h3>Get Location</h3>
                            <div className="input-box">
                                <label htmlFor="location">Location</label>
                                <input type="text" id="location" value={locationRefugeeId} onChange={locationRefugeeIdOnChange} placeholder="Refugee ID" required/>
                            </div>
                            <button type="submit" className="btn">Get Location</button>
                        </form>
                    </div>

                    <div className="content">
                        <form>
                            <h3>Get Refugee</h3>
                            <div className="input-box">
                                <label htmlFor="get-refugee">Refugee ID</label>
                                <input type="text" value={refugeeId} onChange={refugeeIdOnChange} id="get-refugee" placeholder="Refugee ID"
                                       required/>
                            </div>
                            <button type="submit" onClick={getRefugeeHandler} className="btn">Get Refugee</button>
                        </form>
                    </div>
                    <div className="content">
                            <h3>Validation</h3>
                            <div className="input-box">
                                <label>Refugee ID</label>
                                <input type="text" name="immigrantId" value={validationData.immigrantId} onChange={validationDataOnChange} placeholder="Refugee ID"
                                       required/>
                            </div>
                            <div className="input-box">
                                <label>Private Key</label>
                                <input type="text" name="privateKey" value={validationData.privateKey} onChange={validationDataOnChange} placeholder="Private Key"
                                       required/>
                            </div>
                            {/*<div className="input-box">
                                <label>Is Valid: </label>
                                <span>{validationResult}</span>
                            </div>*/}
                            <button onClick={validationHandler} className="btn">Validate</button>
                    </div>
                </div>
            </div>
        </>);
}