import "./LandlordProperties.css";
import { useState, useEffect } from "react";
import Card from "react-bootstrap/Card";
import { useNavigate } from "react-router-dom";
import Service from "../../Shared/Service";

const LandlordPropertyDetails = ({ property }) => {
  const navigate = useNavigate();
  const [isViewDetails, setIsViewDetails] = useState(false);
  const toggleIsViewDetails = () => {
    setIsViewDetails(!isViewDetails);
  };

  const editProperty = (event) => {
    event.preventDefault();
    //,{state:property}
    console.log("Property here : ", property);
    navigate("/edit-property", { state: property });
  };

  const deleteProperty = (event) => {
    event.preventDefault();

    var myHeaders = new Headers();
    myHeaders.append("email", "hassan@miu.edu");

    var requestOptions = {
      method: "DELETE",
      headers: myHeaders,
      redirect: "follow",
    };

    fetch(`${Service.DeleteProperty}${property.id}/false`, requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log(result);
        window.alert("Property Deleted !");
      })
      .catch((error) => {
        window.alert("An Error Occured !");
        console.log("error", error);
      });
  };

  useEffect(() => {
    console.log("");
  }, []);

  return (
    <div className="container" style={{ width: "22rem", display: "flex" }}>
      <Card style={{ width: "22rem", margin: "10px" }}>
        <Card.Img
          variant="top"
          src={
            property.propertyPhotos[0]
              ? property.propertyPhotos[0].photoUrl
              : "https://waapropertymang052022fr.s3.amazonaws.com/properties/12.png"
          }
        />
        <Card.Body className="center-align">
          <Card.Title>
            <b>{property.propertyName}</b>
          </Card.Title>
          <Card.Text className="left-align">
            <p className="left-container">
              {property.propertyType && (
                <>
                  <b>Type: </b> {property.propertyType.type}
                </>
              )}
            </p>
            <p className="left-container">
              <b>Zip Code: </b> {property.address.zipCode}
            </p>
          </Card.Text>
          <Card.Text className="left-align">
            <p className="left-container">
              <b>City: </b>
              {property.address.city}
            </p>
            <p className="left-container">
              <b>State: </b> {property.address.state}
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
                <b>Is Occupied:</b>{" "}
                {property.isOccupied ? "Occupied" : "Not Occupied"}
              </Card.Text>
              <Card.Text className="left-align">
                <b>Is Deleted:</b>{" "}
                {property.deleted ? "Deleted" : "Not Deleted"}
              </Card.Text>
              <Card.Text className="left-align">
                <b> Rent for:</b> ${property.rentAmount} per month
              </Card.Text>
              <Card.Text className="left-align">
                <b> Security deposit:</b> ${property.securityDepositAmount}
              </Card.Text>
            </>
          )}
          {isViewDetails && (
            <>
              <label>
                <b>See Less</b>
              </label>
              <p>
                <button
                  onClick={(e) => {
                    editProperty(e);
                  }}
                >
                  Edit Property
                </button>
              </p>
              <p>
                <button
                  onClick={(e) => {
                    deleteProperty(e);
                  }}
                >
                  Delete Property
                </button>
              </p>
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

export default LandlordPropertyDetails;
