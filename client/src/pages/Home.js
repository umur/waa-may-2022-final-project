import React, { useContext, useEffect, useId } from "react";
import { AuthContext } from "../context/AuthContext";
import Header from "../components/Header";
import PropertyItem from "../components/PropertyItem";
import { useAxios } from "../api/useAxios";

const Home = () => {
  const { data, error, loading } = useAxios("get", "/properties");

  if (loading) {
    return <h1>loading</h1>;
  }

  return (
    <>
      <Header />
      <div className="container card-list">
        {data.data.map((item) => (
          <PropertyItem key={item.id} property={item} />
        ))}
      </div>
    </>
  );
};

export default Home;
