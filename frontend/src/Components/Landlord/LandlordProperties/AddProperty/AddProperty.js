import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import Service from "../../../Shared/Service";
import "./AddProperty.css";

const AddProperty = () => {
  const [propertyName, setPropertyName] = useState("");
  const [password, setPassword] = useState("");
  const [selectedProperty, setSelectedProperty] = useState("");
  const [propertyTypes, setPropertyTypes] = useState([]);
  const [numberOfBedrooms, setNumberOfBedrooms] = useState(0);
  const [numberOfBathrooms, setNumberOfBathrooms] = useState(0);
  const [rentAmount, setRentAmount] = useState(0);
  const [securityDepositAmount, setSecurityDepositAmount] = useState(0);

  const propertyPhotos = [
    {
      photoUrl: "rrrrrrrrrrrrrrrrr",
    },
    {
      photoUrl:
        "https://rrrrrrrrrrrrrrreeeeeeee-6bb5.kxcdn.com/wp-content/uploads/2020/07/01150010/living-room-full2.jpg",
    },
  ];

  const ownedBy = {
    id: 2,
    email: "john@gmail.com",
    firstName: "John",
    lastname: "Mickel",
    password: "123",
    role: {
      id: 3,
      role: "landlord",
    },
    active: true,
    deleted: false,
    lastLoggedInAt: "2022-05-11",
  };

  // "isOccupied": false,
  // "visible": false,
  // "deleted": false

  const [street, setStreet] = useState("");
  const [city, setCity] = useState("");
  const [zipCode, setZipCode] = useState(0);
  const [state, setState] = useState("");

  const url = "http://localhost:8080/api/v1/sign-in";

  // get property types - http://172.17.12.80:8080/api/properties/propertyTypes

  useEffect(() => {
    getPropertyTypes();
  }, []);

  const getPropertyTypes = async () => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };
    const res = await fetch(Service.GetPropertyType, requestOptions)
      .then((response) => response.json())
      .then((data) => {
        setPropertyTypes(data);
      })
      .catch((error) => console.log(error));
  };


  const addProperty = async (e) => {
    e.preventDefault();
    // const propertyTypesData = await addPropertySend(Service.AddProperty);
    const propertyTypesData = await addPropertySend();
    console.log("propertyTypesData : ", propertyTypesData);
    // setPropertyTypes(propertyTypesData);
  };

  const addPropertySend = async () => {
    const propertyTypeSelected = propertyTypes.filter(
      (p) => p.type == selectedProperty
    );
    console.log("propertyTypeSelected : ", propertyTypeSelected);

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      "id": 55,
      propertyName: propertyName,
      address: {
        "id": 1,
        "street": "1000 N st",
        "city": "Fairfield",
        "zipCode": 52257,
        "state": "IOWA"
    },
      propertyType: propertyTypeSelected[0],
      numberOfBedrooms: parseInt(numberOfBedrooms),
      numberOfBathrooms: parseInt(numberOfBathrooms),
      rentAmount: parseInt(rentAmount),
      propertyPhotos: [
        {
          id: 26,
          photoUrl:
            "https://waapropertymang052022fr.s3.amazonaws.com/properties/10.jpg",
        },
        {
          id: 27,
          photoUrl:
            "https://waapropertymang052022fr.s3.amazonaws.com/properties/7.jpg",
        },
      ],
      isOccupied: false,
      ownedBy: {
        id: 3,
        email: "john@gmail.com",
        firstName: "John",
        lastname: "Mickel",
        role: {
          id: 3,
          description: "landlord",
        },
        active: false,
      },
      lastRentedBy: null,
      securityDepositAmount: parseInt(securityDepositAmount),
    });

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch("http://172.17.12.80:8080/api/properties/landlord", requestOptions)
      .then((response) => response.text())
      .then((result) => console.log(result))
      .catch((error) => console.log("error", error));
  };

  // const onSubmit = (e) => {
  //   e.preventDefault();

  //   // if (!username || !password) {
  //   //   alert("Please add username and password");
  //   //   return;
  //   // }

  //   // loginUser({ username, password });

  //   // setUsername("");
  //   // setPassword("");
  // };

  const handleDropDown = (value) => {
    console.log("value : ", value);
    setSelectedProperty(value);
  };

  // const loginUser = async (user) => {
  //   const requestOptions = {
  //     method: "POST",
  //     headers: { "Content-Type": "application/json" },
  //     body: JSON.stringify(user),
  //   };
  //   fetch(url, requestOptions)
  //     .then((response) => response.json())
  //     .then((data) => {
  //       console.log("Sign in response : ", data);
  //     });
  // };

  return (
    <div className="main-container">
      <div className="center">
        <h2>Add Property</h2>
      </div>
      <form className="add-form" onSubmit={addProperty}>
        {/* <form className="add-form" > */}
        <div className="form-control">
          <div className="left">
            <label>Property Name</label>
            <input
              type="text"
              placeholder="Property Name"
              value={propertyName}
              onChange={(e) => setPropertyName(e.target.value)}
            />
          </div>
          <div className="right">
            <select
              className="dropdown"
              name="selectedProperty"
              value={selectedProperty}
              onChange={(e) => handleDropDown(e.currentTarget.value)}
            >
              {propertyTypes.map((types) => (
                <option key={types.id} value={types.type}>
                  {types.type}
                </option>
              ))}
            </select>
          </div>
        </div>

        <div className="form-control">
          <div className="left">
            <label>Street</label>
            <input
              type="text"
              placeholder="Street"
              value={street}
              onChange={(e) => setStreet(e.target.value)}
            />
          </div>
          <div className="right">
            <label>City</label>
            <input
              type="text"
              placeholder="City"
              value={city}
              onChange={(e) => setCity(e.target.value)}
            />
          </div>
        </div>

        <div className="form-control">
          <div className="left">
            <label>Zip Code</label>
            <input
              type="number"
              placeholder="Zip Code"
              value={zipCode}
              onChange={(e) => setZipCode(e.target.value)}
            />
          </div>

          <div className="right">
            <label>State</label>
            <input
              type="text"
              placeholder="State"
              value={state}
              onChange={(e) => setState(e.target.value)}
            />
          </div>
        </div>

        <div className="form-control">
          <div className="left">
            <label>Number of Bedrooms</label>
            <input
              type="number"
              placeholder="Number of Bedrooms"
              value={numberOfBedrooms}
              onChange={(e) => setNumberOfBedrooms(e.target.value)}
            />
          </div>

          <div className="right">
            <label>Number of Bathrooms</label>
            <input
              type="number"
              placeholder="Number of Bathrooms"
              value={numberOfBathrooms}
              onChange={(e) => setNumberOfBathrooms(e.target.value)}
            />
          </div>
        </div>

        <div className="form-control">
          <div className="left">
            <label>Rent Amount</label>
            <input
              type="number"
              placeholder="Rent Amount"
              value={rentAmount}
              onChange={(e) => setRentAmount(e.target.value)}
            />
          </div>

          <div className="right">
            <label>Security Deposit Amount</label>
            <input
              type="number"
              placeholder="Security Deposit Amount"
              value={securityDepositAmount}
              onChange={(e) => setSecurityDepositAmount(e.target.value)}
            />
          </div>
        </div>

        <div className="center">
          <input
            type="submit"
            value="Add Property"
            className="btn btn-block button-padding"
          />
          {/* 
              <button onClick={addProperty}>
                Add */}
          {/* </button> */}
   
          
          <Link to="/landlord" className="button-margin">
            <input
              type="button"
              value="Cancel"
              className="btn btn-block button-padding"
            />
          </Link>
        </div>
      </form>
    </div>
  );
};

export default AddProperty;
