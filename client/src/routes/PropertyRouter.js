import React from "react";
import PropertyDetails from "../components/PropertyDetails";
import Navbar from "../components/Navbar";
const PropertyRoute = () => {
  return (
    <div>
      <Navbar />
      <h1> Property Details Page</h1>
      <PropertyDetails />
    </div>
  );
};

export default PropertyRoute;
