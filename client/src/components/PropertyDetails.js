import React from "react";
import { useParams } from "react-router-dom";
import PopertiesData from "./PropertiesData";

export const PropertyDetails = () => {
  const { id } = useParams();

  let prop = PopertiesData.find((p) => p.id == id);

  console.log(prop);
  console.log(prop.title);

  return <div></div>;
};

export default PropertyDetails;
