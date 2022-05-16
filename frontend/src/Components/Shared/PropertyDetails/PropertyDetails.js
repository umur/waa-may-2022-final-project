import "./PropertyDetails.css";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { useState } from "react";
import Rent from "../Rent/Rent";

const PropertyDetails = ({ property }) => {
  const [isViewDetails, setIsViewDetails] = useState(false);
  const toggleIsViewDetails = () => {
    setIsViewDetails(!isViewDetails);
  };
  return (
    <div className="container" style={{ width: "22rem", display: "flex" }}>
      <Card style={{ width: "22rem", margin: "10px" }}>
        <Card.Img variant="top" src="https://i.ibb.co/g9vzZQQ/property.jpg" />
        <Card.Body className="center-align">
          <Card.Title>
            <b>{property.propertyName}</b>
          </Card.Title>
          <Card.Text className="left-align">
            <p className="left-container">
              <b>Type: </b> {property.propertyType}
            </p>
            <p className="left-container">
              <b>Zip Code: </b> {property.zipCode}
            </p>
          </Card.Text>
          <Card.Text className="left-align">
            <p className="left-container">
              <b>City: </b>
              {property.city}
            </p>
            <p className="left-container">
              <b>State: </b> {property.state}
            </p>
          </Card.Text>
          <Card.Text className="left-align"></Card.Text>
          <Card.Text className="left-align"></Card.Text>

          <Card.Text className="left-align">
            {!isViewDetails && (
              <>
                <label>
                  <b>See More </b>
                </label>
                <p style={{ margin: "4px", display: "inline-block" }}>
                  <input
                    type="checkbox"
                    checked={isViewDetails}
                    value={isViewDetails}
                    onChange={(e) => setIsViewDetails(e.currentTarget.checked)}
                  />
                </p>
              </>
            )}
          </Card.Text>
          {isViewDetails && (
            <>
              <Card.Text className="left-align">
                <b>No. of Bedrooms: </b> {property.numberOfBedrooms}
              </Card.Text>
              <Card.Text className="left-align">
                <b>No. of Bathrooms:</b> {property.numberOfBathrooms}
              </Card.Text>
              <Card.Text className="left-align">
                <b> Rent for:</b> ${property.rentAmount} per month
              </Card.Text>
              <Card.Text className="left-align">
                <b> Security deposit:</b> ${property.securityDepositAmount}
              </Card.Text>

              <Rent />
            </>
          )}
          {isViewDetails && (
            <>
              <label>
                <b>See Less</b>
              </label>
              <p style={{ margin: "4px", display: "inline-block" }}>
                <input
                  type="checkbox"
                  checked={isViewDetails}
                  value={isViewDetails}
                  onChange={(e) => setIsViewDetails(e.currentTarget.checked)}
                />
              </p>
            </>
          )}
        </Card.Body>
      </Card>
    </div>
  );
};

export default PropertyDetails;
