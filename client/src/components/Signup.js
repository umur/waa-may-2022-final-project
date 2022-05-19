import React from 'react'
import { Form, FormGroup, Label, Input, FormText, Button } from 'reactstrap'
import { useState } from 'react';
import Axios from 'axios';
import {useNavigate } from "react-router-dom"


const Signup = () => {

    const [userDetails, setUserDetails] = useState({ firstName: "", lastName: "", email: "", password: "", role: "" })
    let navigate = useNavigate(); 
    const routeChange = () =>{ 
        let path = `/login`; 
        navigate(path);
      }
    const signupButton = () => {
        Axios.post('http://localhost:8080/api/auth/register', userDetails).then(() => {
            console.log('success');
        }).catch(() => {
            console.log('error');
        })
        alert("Saved successfulyy, Please enter your email and password for login")
        routeChange()

    }

    return (
        <div>
            <br></br>
            <label >First name:</label><br></br>
            <input type="text" style={{ padding: 10 }} required onChange={(event) => {
                setUserDetails((prevState) => {
                    return { ...prevState, firstName: event.target.value }
                })
            }} /><br></br>
            <label >Last name:</label><br></br>
            <input type="text" style={{ padding: 10 }} required onChange={(event) => {
                setUserDetails((prevState) => {
                    return { ...prevState, lastName: event.target.value }
                })
            }} /><br></br>
            <label >Email:</label><br></br>
            <input type="text" style={{ padding: 10 }} required onChange={(event) => {
                setUserDetails((prevState) => {
                    return { ...prevState, email: event.target.value }
                })
            }} /><br></br>
            <label >Password:</label><br></br>
            <input type="password" style={{ padding: 10 }} required onChange={(event) => {
                setUserDetails((prevState) => {
                    return { ...prevState, password: event.target.value }
                })
            }} /><br></br>
            <label >Choose a role </label><br></br>
            <select name='role' id='role' required style={{ padding: 10 }} onChange={(event) => {
                setUserDetails((prevState) => {
                    return { ...prevState, role: event.target.value }
                })
            }}>
                <option placeholder='choose'>Choose</option>
                <option value="tenant">Tenant</option>
                <option value="landlord">LandLord</option>
                <option value="admin">Admin</option>
            </select>
            <br></br><br></br>
            <button className='btn' style={{ backgroundColor: "blue" }} onClick={signupButton}>Signup</button>
        </div>
    )
}

export default Signup