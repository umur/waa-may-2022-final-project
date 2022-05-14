import React from "react";
import { ContainerLogin } from "./Login.styled";
import { BoxLogin } from "./Login.styled";
import FormLogin from "../../components/Forms/FormLogin";
import Nav from "../../components/Nav/Nav"
const Login = () =>{
    return (
    <>
    <Nav />
    <ContainerLogin>     
     <FormLogin></FormLogin>  
    </ContainerLogin>
    </>
    )
}

export default Login;