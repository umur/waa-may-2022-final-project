import React from "react";
import LoginForm from "../components/Account/Login";
import Navbar from "../components/Navbar";
const Login = () => {
  return (
    <div>
      <Navbar />
      <h1> Login Form</h1>
      <LoginForm />
    </div>
  );
};

export default Login;