import React, { useEffect, useState } from "react";
import BreadCrumb from "../../components/BreadCrumb";


import axios from "axios";
import { Link, Route, Routes, useNavigate } from "react-router-dom";
import UpdateProperty from "./UpdateProperty";

const PropertyList = () => {

  const [state, setState] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    getProperty();
  }, []);

  const getProperty = async () => {
    let token = JSON.parse(localStorage.getItem("token"));
    try {
      const response = await axios.get(
        "http://localhost:8080/api/v1/properties",
        {
          headers: {
            Authorization: `Bearer ${token.accessToken}`,
          },
        }
      );
      setState([...response.data]);
    } catch (error) {
      console.log(error);
    }
  };

  const handleOnDelete = async (event, propertyId) => {
    let token = JSON.parse(localStorage.getItem("token"));
    const confirm = window.confirm(`Are you sure to delete property with id: ${propertyId} `);

    if(confirm){
      const response = await axios.delete(
        `http://localhost:8080/api/v1/properties/${propertyId}`,
        {
          headers: {
            Authorization: `Bearer ${token.accessToken}`,
          },
        }
      );
      if(response.status===200){
        const properties = state.filter(property =>  property.id!=propertyId);
        setState(properties);
        alert('Properties deleted successfully');
        navigate("/dashboard/property");
      }
    }
  

  }

  return (
    <div className="content-wrapper">
      <BreadCrumb name="Properties" />
      <section className="content">
        <div className="container-fluid">
          <div className="row">
            <div className="col-12">
              <div className="card">
                <div className="card-header">
                  <h3 className="card-title">List of property</h3>
                </div>
                <div className="card-body">
                  <table
                    id="example2"
                    className="table table-bordered table-hover"
                  >
                    <thead>
                      <tr>
                        <th>Property Name</th>
                        <th>Property Type</th>
                        <th>Bed No</th>
                        <th>Bath Room No</th>
                        <th>Rent Amount</th>
                        <th>Security Deposit Amount</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>

                        
                      {state.map((property) => (
                                                <tr key={property.id}>
                                                <td>{property.propertyName}</td>
                                                <td>{property.propertyType}</td>
                                                <td>{property.noOfBedRoom}</td>
                                                <td>{property.noOfBathRoom}</td>
                                                <td>{property.rentAmount}</td>
                                                <td>{property.securityDepositAmount}</td>
                                                <td>
                                                <Link to={`propertyDetail/${property.id}`}><i className='fas fa-info-circle text-primary'></i></Link>
                                                <Link to={`updateProperty/${property.id}`}><i className='fas fa-edit text-primary ml-2'></i></Link>
                                                <i className='fas fa-trash text-danger ml-2 hand-over' onClick={(event) => handleOnDelete(event,property.id)}></i>
                                                </td>
                                            </tr>)
                      )}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      
    </div>
    
  );
};

export default PropertyList;
