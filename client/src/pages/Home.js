import React, { useContext, useEffect, useId } from "react";
import { AuthContext } from "../context/AuthContext";
import Header from "../components/Header";
import PropertyItem from "../components/PropertyItem";
import { useAxios } from "../api/useAxios";
import Loading from "../components/Loading";

const Home = () => {
  const { data, error, loading, execute, queryParam } = useAxios(
    "get",
    "/properties"
  );

  if (loading) {
    return (
      <>
        <Header />
        <Loading />
      </>
    );
  }

  return (
    <>
      <Header
        type="filter"
        onFilter={(data) => {
          execute(queryParam(data));
        }}
      />
      <div className="container card-list">
        {data.data.map((item) => (
          <PropertyItem key={item.id} property={item} />
        ))}
      </div>
    </>
  );
};

export default Home;
