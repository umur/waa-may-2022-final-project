import React from "react";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import ListImages from "../components/slideShow";

export const PropertyDetails = () => {
  const params = useParams();
  const [propertyState, setProertyState] = useState({
    name: "",
    description: "",
    images: [],
  });

  const fetchProerty = () => {
    axios
      .get(`http://localhost:8081/properties/${params.id}`)
      .then((response) => {
        setProertyState(response.data);
      })
      .catch((error) => {
        console.log(error.message);
      });
  };

  useEffect(() => {
    fetchProerty();
  }, []);

  return (
    <div>
      <h1>{propertyState.name}</h1>
      <h1>{propertyState.description}</h1>
      <ListImages images={propertyState.images} />
    </div>
  );
};

export default PropertyDetails;
