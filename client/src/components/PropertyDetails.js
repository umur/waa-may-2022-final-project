import React from "react";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import ListImages from "../components/slideShow";

export const PropertyDetails = () => {
  const { id } = useParams();
  const [propertyState, setProertyState] = useState({});

  const fetchProerty = () => {
    axios
      .get(`http://localhost:8081/properties/${id}`)
      .then((response) => {
        setProertyState(response.data);
      })
      .catch((error) => {
        console.log(error.message);
      });
  };

  useEffect(() => {
    fetchProerty();
  });

  return (
    <div>
      <h1>{propertyState.name}</h1>
      <h1>{propertyState.description}</h1>
      <ListImages
        images={[
          "https://bartlebrothers.com/wp-content/uploads/2015/02/Condos-and-houses-in-San-Diego-California.jpg",
          "https://www.goalproperties.com/wp-content/uploads/2019/07/3239-Runneymede-large.jpg",
        ]}
      />
    </div>
  );
};

export default PropertyDetails;
