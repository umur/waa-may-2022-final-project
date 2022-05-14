import React, { useEffect, useState } from "react";
import Nav from "../../components/Nav/Nav";
import { Link } from "react-router-dom";
import axios from "axios";
import { ContainerPrincipal } from "./Userstyled";

const ListUser = () => {
  const [users, setUsers] = useState([]);
  const loadAllUsers = async () => {
    try {
      const { data } = await axios.get("http://localhost:8080/api/v1/users");
      setUsers(data);
      console.log("usuario", users);
      console.log("deu certo");
    } catch (e) {
      console.log(e.message);
    }
  };
  useEffect(() => {
    loadAllUsers();
  }, []);
  return (
    <>
      <Nav role="admin"></Nav>
      <ContainerPrincipal>
        <h1 style={{ color: "white" }}>List All Users of the System</h1>;
        {users.length === 0 && <h1 style={{ color: "white" }}>Array vazio</h1>}
        {users.map((user) => (
          <Link to={`/userdetails?id=${user.id}`}>
            <p key={user.id} style={{ color: "white" }}>
              {user.firstname}
            </p>
          </Link>
        ))}
      </ContainerPrincipal>
    </>
  );
};

export default ListUser;
