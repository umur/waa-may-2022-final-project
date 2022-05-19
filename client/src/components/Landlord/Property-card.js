import "../PropertyStyle.css";
import React from "react";
import { NavLink } from "react-router-dom";
import { Button } from "@mui/material";

const PropertyCard = (props) => {
  return (
    <div className="property-card">
      <img src={props.imgsrc} alt="Property" />
      <h2 className="title">{props.title}</h2>
      <div className="details">
        <p>{props.description}</p>
        <div className="prop-btns">
          <NavLink to={`/properties/${props.id}`} className="btn">
            View
          </NavLink>
          <Button className="btn">Delete</Button>
        </div>
      </div>
    </div>
  );
};

export default PropertyCard;
