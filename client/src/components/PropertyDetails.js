import React from "react";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import TextMobileStepper from "../components/slideShow"

export const PropertyDetails = () => {
  const { id } = useParams();
  const [propertyState, setProertyState] = useState({});

  const fetchProerty = () => {
    axios.get(`http://localhost:8081/properties/${id}`)
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
      <TextMobileStepper />
    </div>
  );
};

export default PropertyDetails;
