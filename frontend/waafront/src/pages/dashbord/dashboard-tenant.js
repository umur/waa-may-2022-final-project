import React from 'react'
import { useState, useEffect, useRef } from "react";
import api from "../../api/posts";

const DashboardTenant = () => {

  const { propertiesObject } = {};

  const [propertyListState, setPropertyListState] = useState([
    propertiesObject,
  ]);

  const fetchProducts = async () => {
    const result = await api.get("api/v1/properties");
    setPropertyListState(result.data);
  };

  useEffect(() => {
    fetchProducts();

  }, []);

  console.log(propertyListState);
  return (
    <div className='container'>
      <div className='row'>
        <div col-sm-2>

        </div>
        <div col-sm-10>

          <div className='row'>
            {
              propertyListState.map(obj => {
                return (
                  <div className='col-sm-3 pb-5' >
                    <div class="card">
                      <div class="card-header">
                       {obj.name}
                      </div>
                      <div class="card-body">
                        <h5 class="card-title">Bedrooms: {obj.numberOfBedrooms}, Rent: {obj.rentAmount}</h5>
                        <p class="card-text">Address: {obj.street}, &nbsp; {obj.city}, &nbsp; {obj.state} - {obj.zip}</p>
                        <a href="#" class="btn btn-primary">Rent</a>
                      </div>
                    </div>
                  </div>
                )
              })
            }

          </div>

        </div>
        <div col-sm-2>

        </div>
      </div>

    </div>
  )
}

export default DashboardTenant;