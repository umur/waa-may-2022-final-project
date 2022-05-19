import "../PropertyStyle.css";
import PropertyCard from "../Landlord/Property-card";
import React from "react";
import { useEffect, useState } from "react";

import axios from "axios";

const AdminPage = (props) => {
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
      <div>
          <h1>
              Last 10 users 
          </h1>
      </div>
      <div>
          <h1>
              Charts
          </h1>
      </div>
    </div>
  );
};

export default AdminPage;