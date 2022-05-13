import React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import "./index.css";
import { useNavigate } from "react-router-dom";

const PropertyItem = ({ property }) => {
  const navigate = useNavigate();

  let {
    id,
    numberOfBedrooms,
    numberOfBathrooms,
    photos,
    propertyName,
    rentAmount,
    city,
    state,
    propertyType,
  } = property;
  return (
    <>
      <Card
        sx={{ maxWidth: 300, minWidth: 300 }}
        className="card-item"
        onClick={() => navigate("/property/" + id)}
      >
        <CardMedia
          component="img"
          height="140"
          image="https://images.unsplash.com/photo-1600607687939-ce8a6c25118c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2906&q=80"
          alt="green iguana"
        />
        <CardContent>
          <h4 className="title">${rentAmount}</h4>
          <p className="sub-title">
            {city}, {state}
          </p>
          <div className="card-body">
            <p>
              {propertyType} {propertyName}
            </p>
            .<p>{numberOfBedrooms} bedroom</p>.
            <p>{numberOfBathrooms} bathroom</p>
          </div>
        </CardContent>
        {/* <CardActions className="card-action">
          <Button size="small" variant="outlined">
            Learn More
          </Button>
        </CardActions> */}
      </Card>
    </>
  );
};
export default PropertyItem;
