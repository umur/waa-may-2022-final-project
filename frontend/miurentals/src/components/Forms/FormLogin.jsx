import React from "react";
import { ContainerFormLogin } from "./FormLogin.styled";
import { ContainerPSign } from "./FormLogin.styled";
import { ContainerInput } from "./FormLogin.styled";
import { ContainerPass } from "./FormLogin.styled";
import { ContainerSubmit } from "./FormLogin.styled";
import { ContainerForgot } from "./FormLogin.styled";
import { ContainerForm } from "./FormLogin.styled";

const LoginForm = () =>{
    return (
        <ContainerFormLogin>
        <ContainerPSign> Sign in</ContainerPSign>
        <ContainerForm>
        <ContainerInput placeholder="Username"></ContainerInput>
        <ContainerPass placeholder="Password" type={"password"}></ContainerPass>
        <ContainerSubmit>Sign</ContainerSubmit>
        <ContainerForgot href="">Forgot Password</ContainerForgot>
        
        </ContainerForm>
        
        </ContainerFormLogin>
    )
}

export default LoginForm;
