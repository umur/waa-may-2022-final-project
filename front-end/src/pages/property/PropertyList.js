import React, { useEffect, useState } from "react";
import BreadCrumb from "../../components/BreadCrumb";


import axios from "axios";

const PropertyList = () => {

  const [state, setState] = useState([]);

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
      console.log(state);
    } catch (error) {
      console.log(error);
    }
  };

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
                                                <i className='fas fa-info-circle text-primary'></i>
                                                    <i className='fas fa-edit text-primary ml-2'></i>
                                                    <i className='fas fa-trash text-danger ml-2'></i>
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
