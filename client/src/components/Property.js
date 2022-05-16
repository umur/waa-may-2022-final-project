import "./PropertyStyle.css";
import PropertyCard from "./Property-card";
import React from "react";
import { useEffect, useState } from "react";

import axios from "axios";

const Property = (props) => {
  const [propertiesState, setProertiesState] = useState([]);

  const fetchProerties = () => {
    axios
      .get("http://localhost:8080/properties")
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
              imgsrc={
                "https://bartlebrothers.com/wp-content/uploads/2015/02/Condos-and-houses-in-San-Diego-California.jpg"
              }
              title={property.title}
              description={property.description}
            />
          );
        })}
      </div>
    </div>
  );
};

export default Property;
