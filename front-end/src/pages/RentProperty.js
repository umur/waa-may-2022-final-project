import React, { useEffect, useState } from 'react'
import BreadCrumb from '../components/BreadCrumb'
import axios from 'axios';
import { Link } from 'react-router-dom';

const RentProperty = () => {

    const [properties, setProperties] = useState([]);

    useEffect(() => {
        getReport();
    }, []);

    let token = JSON.parse(localStorage.getItem("token"));


    const getReport = async () => {
        try {
            const response = await axios.get(
                "http://localhost:8080/api/v1/properties/filter-property-not-rented",
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`,
                    },
                }
            );
            console.log(response.data)
            setProperties([...response.data]);
            console.log(properties);
        } catch (error) {
            console.log(error);
        }
    };

    const propertiesView = () => {
        return (
            properties.map((item) => {
                return (
                <div className="card mr-2" key={item.id} style={{ width: '18rem' }}>
                    {
                        item?.photos[0] ? <img className="card-img-top" src={`http://localhost:8080/api/v1/properties/${item.id}/images/${item?.photos[0]?.id}`} alt="Card image cap" /> : 
                        <img className="card-img-top" src= "https://i.pravatar.cc/"/>
                    }
                    
                    <div className="card-body">
                        <h5 className="card-title">{item.propertyType}</h5>
                        {/* <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> */}
                        <p className='card-text m-0'>Name : {item.propertyName}</p>
                        <p className='card-text m-0'>Rent Amount : {item.rentAmount}</p>
                        <p className='card-text m-0'>No. of bathrooms : {item.noOfBathroom}</p>
                        <p className='card-text m-0'>No. of bedrooms : {item.noOfBedRoom}</p>
                        <p className='card-text m-0'>Security Deposit : {item.securityDepositAmount}</p>

                        <Link to={`rent-form/${item.id}`} className="btn btn-primary mt-2">Rent</Link>
                    </div>
                </div>
                )

            })
        )
    }

    return (
        <div className="content-wrapper">
            <BreadCrumb name="Rent Property" />
            <section className="content">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <div className="card-header">
                                    <h3 className="card-title">Total Income</h3>
                                </div>
                                <div className="card-body d-flex flex-wrap">
                                    {propertiesView()}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section >
        </div >
    )
}

export default RentProperty