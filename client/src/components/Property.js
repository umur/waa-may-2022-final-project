import "./PropertyStyle.css";
import PropertyCard from "./Property-card";
import PopertiesData from "./PropertiesData";
import React from "react";

const Property = (props) => {
  return (
    <div className="main">
      <h1>{props.head}</h1>
      <div className="property-container">
        {PopertiesData.map((property, index) => {
          return (
            <PropertyCard
              key={index}
              id={property.id}
              imgsrc={property.imgsrc[0]}
              title={property.title}
              description={property.description}
              rent={property.rent}
              view={property.view}
            />
          );
        })}
      </div>
    </div>
  );
};

export default Property;
