import React from 'react'
import { useState, useEffect, useRef } from "react";
import api from "../../api/posts";
import { useSelector } from 'react-redux';
import { useNavigate, Link } from "react-router-dom";

const DashboardTenant = () => {
  let navigate = useNavigate();
  const user = useSelector((state) => state.user.value); //token, role, email, id

  const config = {
    headers: { Authorization: `Bearer ${user.token}` }
  };

  const { propertiesObject } = {};

  const [propertyListState, setPropertyListState] = useState([
    propertiesObject,
  ]);

  const fetchProducts = async () => {
    const result = await api.get("api/v1/properties",config);
    setPropertyListState(result.data);

    console.log(propertyListState);
  };

  

  useEffect(() => {
    fetchProducts();

  }, []);

  const handleRent = async (rentInfo)=>{
    try {
      

      let d = new Date();
    let year = d.getFullYear();
    let month = d.getMonth();
    let day = d.getDate();

    let c = new Date(year + 1, month, day);

      const rentObj = {startDate:d, endDate: c, user:{id:window.sessionStorage.getItem('id'), property:{id:rentInfo.id}} }
     
      const { data } = await api.post('api/v1/rents', rentObj, config);
     
     console.log(rentInfo);
     navigate(`/rent-confirmation`);
    } catch (e) {
      console.log(e.message);
    }

  }

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
                       {obj}
                      </div>
                      <div class="card-body">
                        <h5 class="card-title">Bedrooms: {obj.numberOfBedrooms}, Rent: {obj.rentAmount}</h5>
                        <p class="card-text">Address: {obj.street}, &nbsp; {obj.city}, &nbsp; {obj.state} - {obj.zip}</p>
                        <button class="btn btn-primary" onClick={()=> handleRent (obj.id)}>Rent</button>
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