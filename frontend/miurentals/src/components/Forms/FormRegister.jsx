import React, { useRef } from "react";
import {
  ContainerFormRegister,
  ContainerSubmit,
  ContainerFormRegisterBox,
  ContainerInput,
  ContainerTitle,
} from "./FormRegister.styled";
import {
  ContainerSelectStates,
  ContainerSelectTypes,
} from "./FormProperties.styled";
import axios from "axios";
const FormRegister = () => {
  const fnameRef = useRef(null);
  const lnameRef = useRef(null);
  const emailRef = useRef(null);
  const passwordRef = useRef(null);
  const repasswordRef = useRef(null);
  const roleRef = useRef(null);

  const clearForm = () => {
    fnameRef.current.value = "";
    lnameRef.current.value = "";
    emailRef.current.value = "";
    passwordRef.current.value = "";
    repasswordRef.current.value = "";
    roleRef.current.value = "";
  };

  const submitForm = async () => {
    const password = passwordRef.current.value;
    const repassword = repasswordRef.current.value;

    if (password !== repassword) {
      console.log("Password need to match");
      return;
    }
    const firstname = fnameRef.current.value;
    const lastname = lnameRef.current.value;
    const email = emailRef.current.value;
    const role = roleRef.current.value;

    console.log(firstname, lastname, email, role, password);
    try {
      const { data } = await axios.post("http://localhost:8080/api/v1/users", {
        firstname,
        lastname,
        email,
        password,
        role,
      });
      window.alert("Register Save");
      clearForm();
    } catch (e) {
      console.log(e.message);
    }
  };

  return (
    <ContainerFormRegister>
      <ContainerFormRegisterBox>
        <ContainerTitle>Registration Form</ContainerTitle>
        <ContainerInput
          placeholder="First Name"
          name="firstname"
          ref={fnameRef}
        ></ContainerInput>
        <ContainerInput
          placeholder="Last Name"
          name="lastname"
          ref={lnameRef}
        ></ContainerInput>
        <ContainerInput
          placeholder="Email"
          name="email"
          ref={emailRef}
        ></ContainerInput>
        <ContainerInput
          placeholder="Password"
          name="password"
          type="password"
          ref={passwordRef}
        ></ContainerInput>
        <ContainerInput
          placeholder="Repet Password"
          name="repassword"
          type="password"
          ref={repasswordRef}
        ></ContainerInput>
        <br />
        <br />
        <ContainerSelectStates
          ref={roleRef}
          style={{ backgroundCcolor: "white" }}
        >
          <option>Select Role</option>
          <option value="admin">Admin</option>
          <option value="landlord">Land Lord</option>
          <option value="tenant">Tenant</option>
        </ContainerSelectStates>
        <ContainerSubmit onClick={() => submitForm()}>
          Register User
        </ContainerSubmit>
      </ContainerFormRegisterBox>
    </ContainerFormRegister>
  );
};

export default FormRegister;
