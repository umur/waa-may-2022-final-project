import React, { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import Header from "../components/Header";
import PropertyItem from "../components/PropertyItem";

const Home = () => {
  const { isSignedIn } = useContext(AuthContext);

  return (
    <>
      <Header />
      <div className="container card-list">
        <PropertyItem property={null} />
        <PropertyItem property={null} />
        <PropertyItem property={null} />

        <PropertyItem property={null} />
        <PropertyItem property={null} />
        <PropertyItem property={null} />
        <PropertyItem property={null} />
      </div>
    </>
  );
};

export default Home;
