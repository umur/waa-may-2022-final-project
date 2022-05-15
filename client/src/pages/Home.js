import React, { useContext, useEffect, useId } from "react";
import { AuthContext } from "../context/AuthContext";
import Header from "../components/Header";
import PropertyItem from "../components/PropertyItem";
import { useAxios } from "../api/useAxios";
import Loading from "../components/Loading";
import MapsHomeWorkIcon from "@mui/icons-material/MapsHomeWork";
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
        {data.data.length > 0 ? (
          data.data.map((item) => (
            <PropertyItem key={item.id} property={item} />
          ))
        ) : (
          <>
            <div className="empty-list">
              <MapsHomeWorkIcon
                style={{ fontSize: "35px", marginBottom: "10px" }}
              />
              <h3> Sorry! We could not find any properties.</h3>
            </div>
          </>
        )}
      </div>
    </>
  );
};

export default Home;
