import React from "react";
import { ContainerHome } from "./Home.styled";
import Nav from "../../components/Nav/Nav";
const Home = () => {
  return (
    <ContainerHome>
      <Nav role="landlord"></Nav>
    </ContainerHome>
  );
};
export default Home;
