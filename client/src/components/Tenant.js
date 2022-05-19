import React from 'react'
import { useState } from 'react';
import Axios from 'axios';
import { Link } from 'react-router-dom';

const Tenant = () => {
  const [properties, setProperties] = useState([]);

  // const deleteProperty =  (id) => {
  //    Axios.delete(`http://localhost:8080/api/properties/${id}`);
  //     // setProperties(properties.filter((val) => {
  //     //   return val.id != id;
  //     // }))
  //     setProperties(properties);
  //     getProperties();
    
  // }

  const getProperties = () => {
    Axios.get("http://localhost:8080/api/properties/").then((response) => {
      setProperties(response.data);
    })
  }

  return (
    <div><Link to="/login" className="header btn" >Signout</Link>
      <br/><br/>
      <button className='btn' style={{ backgroundColor: "green" }} onClick={getProperties}>Display properties</button>
      <div>
        {properties.map((val, key) => {
          return <div className='userList'>
            <div>
              <h3>Id: {val.id}</h3>
              <h3>Property Name: {val.propertyName}</h3>
              <h3>Street Address: {val.streetAddress}</h3>
              <h3>City: {val.city}</h3>
              <h3>State: {val.state}</h3>
              <h3>ZipCode: {val.zipCode}</h3>
              <h3>Property Type: {val.propertyType}</h3>
              <h3>Number of Bedrooms: {val.numberOfBedrooms}</h3>
              <h3>Number of Bathrooms: {val.numberOfBathrooms}</h3>
              <h3>Rent Amount: {val.rentAmount}</h3>
              <h3>Deposit Amount: {val.securityDepositAmount}</h3>
             

            </div>
            <div>
              {/* <button className='btn' style={{color:"red"}} onClick={() =>{
                deleteProperty(val.id)
              }}>Delete</button> */}
            </div>

          </div>


        })}
      </div>
    </div>
  )
}

export default Tenant