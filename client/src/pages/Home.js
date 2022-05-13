import React, { useContext, useEffect, useId } from "react";
import { AuthContext } from "../context/AuthContext";
import Header from "../components/Header";
import PropertyItem from "../components/PropertyItem";
import { useAxios } from "../api/useAxios";

const Home = () => {
  const { isSignedIn } = useContext(AuthContext);
  const { data, error, loading, execute } = useAxios("post", "/properties");

  if (loading) {
    return <h1>loading</h1>;
  }

  return (
    <>
      <Header />
      <div
        onClick={() => {
          execute({ test: "test" });
        }}
      >
        click here
      </div>
    </>
  );
};

export default Home;
