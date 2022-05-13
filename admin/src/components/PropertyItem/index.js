import React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import "./index.css";

const PropertyItem = ({ property }) => {
  //   let {
  //     numberOfBedrooms,
  //     numberOfBathrooms,
  //     photos,
  //     propertyName,
  //     streetAddress,
  //     city,
  //     state,
  //   } = property;
  return (
    <>
      <Card sx={{ maxWidth: 300, minWidth: 300 }} className="card-item">
        <CardMedia
          component="img"
          height="140"
          image="https://images.unsplash.com/photo-1600607687939-ce8a6c25118c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2906&q=80"
          alt="green iguana"
        />
        <CardContent>
          <h4 className="title">$800</h4>
          <p className="sub-title">Fairfield, Iowa</p>
          <div className="card-body">
            <p>Apartment</p>.<p>2 bedroom</p>.<p>1 bathroom</p>
          </div>
        </CardContent>
        <CardActions className="card-action">
          <Button size="small" variant="outlined">
            Learn More
          </Button>
        </CardActions>
      </Card>
    </>
  );
};
export default PropertyItem;
