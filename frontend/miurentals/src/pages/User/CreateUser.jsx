import React from "react";
import Nav from "../../components/Nav/Nav";
import FormCreateUsers from "../../components/Forms/FormCreateUser";
import { ContainerInputProperties } from "../../components/Forms/FormProperties.styled";
import FormRegister from "../../components/Forms/FormRegister";
const CreateUser = function () {
  return (
    <>
      <Nav role="admin"></Nav>
      <FormRegister></FormRegister>
    </>
  );
};

export default CreateUser;
