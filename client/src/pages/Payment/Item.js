import React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import "./index.css";
import DefaultImage from "../../assets/img/default-house.jpeg";
import { Box, Typography } from '@mui/material';
import moment from 'moment';

const Item = ({ propertyRentalHistory }) => {
  if (!propertyRentalHistory) return (<></>);

  const { property } = propertyRentalHistory

  const {
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

  const startDate = moment(propertyRentalHistory.startDate);
  const endDate = moment(propertyRentalHistory.endDate);
  const diff = endDate.diff(startDate);
  const diffDuration = moment.duration(diff);

  console.log(propertyRentalHistory)

  return (
    <>
      <Card
        sx={{ maxWidth: 400, minWidth: 400 }}
        className="card-item"
      >
        <CardMedia
          component="img"
          height="240"
          image={photos.length > 0 ? photos[0] : DefaultImage}
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
            <p>{numberOfBedrooms} bedroom</p>
            <p>{numberOfBathrooms} bathroom</p>
            <p>Start Date: {moment(startDate).format('MMM DD YYYY')}</p>
            <p>End Date: {moment(endDate).format('MMM DD YYYY')}</p>
            <Box sx={{ fontWeight: 'bold', m: 1 }}>
            Subtotal: ${diffDuration.days() * rentAmount}
            </Box>
          </div>
        </CardContent>
      </Card>
    </>
  );
};
export default Item;
