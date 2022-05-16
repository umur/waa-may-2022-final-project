import React from "react";
import { useParams } from "react-router-dom";
import PopertiesData from "./PropertiesData";

export const PropertyDetails = () => {
  const { id } = useParams();

  let prop = PopertiesData.find((p) => p.id == id);

  return <div>
    <h1>prop.name</h1>
  </div>;
};

export default PropertyDetails;
