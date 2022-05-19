import "../PropertyStyle.css";
import PropertyCard from "./Property-card";
import React from "react";
import { useEffect, useState } from "react";

import axios from "axios";

const LandlordPage = (props) => {
  const [propertiesState, setProertiesState] = useState([]);

  const fetchProerties = () => {
    axios
      .get("http://localhost:8081/properties")
      .then((response) => {
        setProertiesState(response.data);
      })
      .catch((error) => {
        console.log(error.message);
      });
  };

  useEffect(() => {
    fetchProerties();
  }, []);

  return (
    <div className="main">
      <h1>{props.head}</h1>
      <div className="property-container">
        {propertiesState.map((property, index) => {
          return (
            <PropertyCard
              key={index}
              id={property.id}
              imgsrc={property.images[0]}
              title={property.name}
              description={property.description}
            />
          );
        })}
      </div>
    </div>
  );
};

export default LandlordPage;
