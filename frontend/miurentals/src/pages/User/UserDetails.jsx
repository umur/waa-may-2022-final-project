import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import axios from "axios";
import { ContainerPrincipalDetail, ContainerPUser } from "./Userstyled";
import { ContainerFormProperties } from "../../components/Forms/FormProperties.styled";
import { ContainerPUser2 } from "./Userstyled";
import { ContainerInputProperties } from "../../components/Forms/FormProperties.styled";
import Nav from "../../components/Nav/Nav";
import { ContainerPSign } from "../../components/Forms/FormLogin.styled";

const UserDetail = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const id = searchParams.get("id");
  const [user, setUser] = useState([]);
  const getDetail = async () => {
    try {
      const { data } = await axios.get(
        `http://localhost:8080/api/v1/users/${id}`
      );
      setUser(data);
      console.log("malvadao", data);
    } catch (e) {
      console.log("erro");
      console.log(e.message);
    }
  };

  useEffect(() => {
    getDetail();
  }, []);
  let fullname = user.firstname + " " + user.lastname;
  return (
    <>
      <Nav></Nav>
      <ContainerFormProperties>
        <ContainerPUser>
          <p>Information of user : {fullname}</p>
        </ContainerPUser>
        <form>
          <ContainerPUser2>
            First Name:
            <ContainerInputProperties
              value={user.firstname}
              onChange={(event) => {
                setUser({
                  ...user,
                  firstname: event.target.value,
                });
              }}
            ></ContainerInputProperties>
            <br />
            Last Name:
            <ContainerInputProperties
              value={user.lastname}
              onChange={(event) => {
                setUser({
                  ...user,
                  lastname: event.target.value,
                });
              }}
            ></ContainerInputProperties>
            <br />
            Email:
            <ContainerInputProperties
              value={user.email}
              onChange={(event) => {
                setUser({
                  ...user,
                  email: event.target.value,
                });
              }}
            ></ContainerInputProperties>
            <br />
            Password:
            <ContainerInputProperties
              value={user.password}
              onChange={(event) => {
                setUser({
                  ...user,
                  password: event.target.value,
                });
              }}
            ></ContainerInputProperties>
          </ContainerPUser2>
        </form>
      </ContainerFormProperties>
    </>
  );
};

export default UserDetail;
