import Button from "react-bootstrap/Button";
import { useState, useEffect } from "react";
import Service from "../Service";

const Rent = ({ property }) => {
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [name, setName] = useState("");

  useEffect(() => {
    console.log("Rent", property.rentAmount);
  }, []);

  const rentProperty = () => {
    var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "property": {
    "id": 19,
    "propertyName": "appartment with 11111 bedrooms",
    "address": {
      "id": 1,
      "street": "1000 N st",
      "city": "Fairfield",
      "zipCode": 52257,
      "state": "IOWA"
    },
    "propertyType": {
      "id": 1,
      "type": "Appartment"
    },
    "numberOfBedrooms": 5,
    "numberOfBathrooms": 2,
    "rentAmount": property.rentAmount,
    "propertyPhotos": [
      {
        "id": 21,
        "photoUrl": "https://waapropertymang052022fr.s3.amazonaws.com/properties/2.jpgg"
      },
      {
        "id": 20,
        "photoUrl": "https://waapropertymang052022fr.s3.amazonaws.com/properties/9.jpg"
      }
    ],
    "isOccupied": true,
    "ownedBy": {
      "id": 2,
      "email": "hassan@miu.edu",
      "firstName": "hassan",
      "lastname": "hassan",
      "role": {
        "id": 2,
        "description": "tenant"
      }
    },
    "lastRentedBy": {
      "id": 3,
      "email": "john@gmail.com",
      "firstName": "John",
      "lastname": "Mickel",
      "role": {
        "id": 3,
        "description": "landlord"
      }
    }
  },
  "rentedBy": {
    "id": 3,
    "email": "john@gmail.com",
    "firstName": "John",
    "lastname": "Mickel",
    "role": {
      "id": 3,
      "description": "landlord"
    }
  },
  "rentedFrom": startDate,
  "rentedTo": endDate,
  "rentedAmount": 5000
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch(Service.TenantRentProperty, requestOptions)
  .then(response => response.text())
  .then(result => {
    console.log(result);
    alert("Property Rented");
  })
  .catch(error =>  {
    console.log('error', error)
    alert("An error occurred!");
  });
  };
  return (
    <>
      <div className="form-control">
        <label>Start Date</label>
        <input
          type="text"
          placeholder="YYYY-MM-DD"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
        />
      </div>
      <div className="form-control">
        <label>End Date</label>
        <input
          type="text"
          placeholder="YYYY-MM-DD"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}  
        />
      </div>
      <div className="form-control">
        <label>Property Name</label>
        <input
          type="text"
          placeholder="Property Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>
      <div>
        <Button variant="primary" onClick={rentProperty}>
          Rent
        </Button>
      </div>
    </>
  );
};

export default Rent;
