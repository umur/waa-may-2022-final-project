import React, { useEffect, useState } from "react";
import BreadCrumb from "../../components/BreadCrumb";
import { useForm } from 'react-hook-form';

import axios from "axios";
import { Link, Route, Routes, useNavigate } from "react-router-dom";
import UpdateProperty from "./UpdateProperty";
import { isLandLord } from '../../utils/role';

const PropertyList = () => {

  const [state, setState] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    if(!isLandLord()){
      navigate('/not-found');
    }
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

//filter start
const { register, handleSubmit, formState: { errors } } = useForm({
    defaultValues: {
      propertyType: "",
      noOfBedRoom: "",
      state: "",
      city: "",
    }
  });

const [filterTypeState, setFilterTypeState] = useState("");
  const handleOnFilterTypeChange = (e) => {
    setFilterTypeState(e.target.value);
  }

const onSubmit = async (data) => {
  let token = JSON.parse(localStorage.getItem("token"));
  let url = "http://localhost:8080/api/v1/properties";

  url = filterTypeState == "type" ?  "http://localhost:8080/api/v1/properties/filter-property-by-type?type=" + data.propertyType : url;

  url = filterTypeState == "roomNo" ? "http://localhost:8080/api/v1/properties/filter-property-by-roomno?noofroom=" + data.noOfBedRoom : url;

  if (filterTypeState == "address" && data.state != "" && data.city != "")
    url = "http://localhost:8080/api/v1/properties/filter-property-by-address?state=" + data.state + "&city=" + data.city;
  else if (filterTypeState == "address" && data.state != null && data.city == "")
    url = "http://localhost:8080/api/v1/properties/filter-property-by-address?state=" + data.state;
  else if (filterTypeState == "address" && data.state == "" && data.city != null)
    url = "http://localhost:8080/api/v1/properties/filter-property-by-address?city=" + data.city;
console.log(url);
  const response = await axios.get(url,
    {
      headers: {
        Authorization: `Bearer ${token.accessToken}`,
      },
    }
  );
  setState([...response.data]);

}

  return (
    <div className="content-wrapper">
      <BreadCrumb name="Properties" />
      <section className="content">
        <div className="container-fluid">
          <div className="row">
            <div className="col-12">
              <div className="card">
                <div className="px-4 pt-4 d-flex justify-content-between">
                  <h3 className="card-title">List of property</h3>

                  <form  method="post" onSubmit={handleSubmit(onSubmit)} >

                  <div className="form-group" style={{display: "flex"}}>
                    <select 
                      className="form-control"  
                      style={{width: 200 + 'px'}}
                      onChange={handleOnFilterTypeChange}
                      >
                      <option value="" disabled>Select Filter Type</option>
                      <option value="type">Filter By Property Type</option>
                      <option value="roomNo">Filter By Room No</option>
                      <option value="address">Filter By Address</option>
                    </select>
                    {filterTypeState == "type" ?  <input style={{width: 250 + 'px', margin: 0+"px" + 10+"px"}} 
                    {...register("propertyType", { required: 'Property Type is required' })}
                    type="text" class="form-control" placeholder="Property Type" /> : ""
                    }

                    {filterTypeState == "roomNo" ? <input style={{width: 100 + 'px'}}
                    {...register("noOfBedRoom", { required: 'Bed room number is required' })} 
                    type="text" class="form-control" placeholder="Room No" /> : ""}
                    
                    {
                    filterTypeState == "address" ? 
                    (<><input style={{width: 200 + 'px'}}
                    {...register("state")}
                    type="text" class="form-control" placeholder="State" />
                    <input style={{width: 200 + 'px'}}
                    {...register("city")}
                    type="text" class="form-control" placeholder="city" /></>) : ""
                  }

                  {filterTypeState ? <input type="submit" class="btn btn-primary" value="Search" /> : ""}
                  
                  </div>   
                 
                    </form>               
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
