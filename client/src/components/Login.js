import React, { Component, useState } from 'react'
import Axios  from 'axios';
import Tenant from './Tenant';
import {useNavigate } from "react-router-dom"


const Login = () => {

    const [loginDetails, setLoginDetails] = useState({email:"", password:"", role:""});

    const tempUser ={email:"bla@gmail.com", password:"123", role:"landlord"};

    let navigate = useNavigate(); 
    const routeChange = () =>{ 
    let path="";
    if(loginDetails.role === "admin"){
        path = `/login/admin`; 
    }
    else if(loginDetails.role === "landlord"){
        path = `/login/landlord`; 
    }
    else {
        path = `/login/tenant`; 
    }
    navigate(path);
    //console.log(path);
  }
    const loginButton = () => {
        Axios.post('http://localhost:8080/api/auth/login', loginDetails
        ).then((response) => {
            if(response.data.accessToken == ""){
                routeChange();
            }
            else alert("invalid email or password! Please try again");
        }).catch(() => {
            console.log('error');
        })
        //routeChange()
        //console.log("logged in");
       
    }
    return (
        <div >
            <br></br>
            <input type="text" placeholder='email' style={{ padding: 10 }} required onChange={(event) =>{
                    setLoginDetails((prevState) =>{
                        return {...prevState, email: event.target.value }
                    })
                    
            }}/>
            <input type="password" placeholder='password' style={{ padding: 10 }} required onChange={(event) =>{
                    setLoginDetails((prevState) =>{
                        return {...prevState, password: event.target.value }
                    })
                    
            }}/>
            <br></br><br></br>
            <label >Choose a role </label>
            <select name='role' id='role' required style={{ padding: 10 }} onChange={(event) =>{
                    setLoginDetails((prevState) =>{
                        return {...prevState, role: event.target.value }
                    })
                   
            }}>
                <option placeholder='choose'>Choose</option>
                <option value="tenant">Tenant</option>
                <option value="landlord">LandLord</option>
                <option value="admin">Admin</option>
            </select>
            <br></br><br></br>
            <button className='btn' style={{ backgroundColor: "blue" }} onClick={loginButton}>Login</button>

        </div>
    )
}

export default Login