import React, { useEffect, useState } from "react";
import Nav from "../../components/Nav/Nav";

import axios from "axios";

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
      <h1 style={{ color: "white" }}>List All Users of the System</h1>;
      {users.length === 0 && <h1 style={{ color: "white" }}>Array vazio</h1>}
      {users.map((user) => (
        <p style={{ color: "white" }}>{user.firstname}</p>
      ))}
    </>
  );
};

export default ListUser;
