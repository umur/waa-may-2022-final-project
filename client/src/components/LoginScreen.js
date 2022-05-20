import React from 'react';
import './Login.css';
import axios from "axios";
import { useState } from "react";

export default function Login({ setToken }) {


  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [firstname, setFirstname] = useState();
  const [lastname, setLastname] = useState();
  const [role, setRole] = useState();
  const [success, setSuccess] = useState();


  const onLogin = async (credentials) => {

    console.log(credentials);
    let result = await axios.post('http://localhost:8080/api/v1/auth/login', credentials);
    console.log(result.data);
    return result.data;
  }

  const handleSubmit = async e => {
    e.preventDefault();
    const token = await onLogin({
      email,
      password
    });
    localStorage.setItem('uid', token.userDto.id);
    localStorage.setItem('urole', token.userDto.role);
    localStorage.setItem('token', 'Bearer ' + token.accessToken);
    console.log(token.accessToken);
    setToken(token.accessToken);
  }




  const persistData = async (event) => {
    event.preventDefault();
    const token = await registerUser({
      email,
      password,
      firstname,
      lastname,
      role
    });
  }

  const registerUser = async (credentials) => {
    console.log(credentials)
    let result = await axios.post('http://localhost:8080/api/v1/auth/register', credentials);
    setSuccess("true");
  }




  return (
    <div>
      <div className="login-wrapper">
        <h1>Please Log In</h1>
        <form onSubmit={handleSubmit}>
          <label>
            <p>Username</p>
            <input type="text" onChange={e => setEmail(e.target.value)} />
          </label>
          <label>
            <p>Password</p>
            <input type="password" onChange={e => setPassword(e.target.value)} />
          </label>
          <div>
            <button type="submit">Submit</button>
          </div>
        </form>
      </div>


      <div className="login-wrapper" style={{"padding-top" : "30px"}}>

        <h1 className="add-property-title">Register</h1>
        <div className="add-property">



          <form onSubmit={persistData}>

            <div >
              <p>Email</p>
              <input type='text' onChange={e => setEmail(e.target.value)} />
            </div>

            <div >
              <p>Password</p>
              <input type='text' onChange={e => setPassword(e.target.value)} />
            </div>

            <div >
              <p>FirstName</p>
              <input type='text' onChange={e => setFirstname(e.target.value)} />
            </div>

            <div >
              <p>Lastname</p>
              <input type='text' onChange={e => setLastname(e.target.value)} />
            </div>


            <div>
              <p>Role</p>
              <select onChange={e => setRole(e.target.value)}>

                <option key={3} >Tenant</option>
                <option key={2} >Landlord</option>

              </select>
            </div>


            <button>Save</button>

          </form>

        </div>

{
  success == "true" ? 
  <h1>Register success</h1> : null
}

      </div>
    </div>
  )
}