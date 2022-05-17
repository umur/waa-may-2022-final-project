import React, { useEffect, useState } from "react";
import { useForm } from 'react-hook-form';

import axios from "axios";
import { getRole } from '../utils/role';


const Dashboard = () => {

    let role = getRole();



    const [state, setState] = useState([]);
    const [tenants, setTenants] = useState([]);
    const [incomes, setIncomes] = useState([]);
    const [propertiesEnding, setPropertiesEnding] = useState([]);


    useEffect(() => {
        getProperty();
        getTenants();
        getIncomes()
        getPropertiesEnding();
    }, []);

    const getPropertiesEnding = async () => {
        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const response = await axios.get(
                "http://localhost:8080/api/v1/properties/filter-top-10-lease-in-month",
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`,
                    },
                }
            );
            setPropertiesEnding([...response.data]);
        } catch (error) {
            console.log(error);
        }
    };

    const getProperty = async () => {
        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const response = await axios.get(
                "http://localhost:8080/api/v1/properties/filter-last-10-rented",
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

    const getTenants = async () => {
        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const response = await axios.get(
                "http://localhost:8080/api/v1/users/top10-recent-tenants",
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`,
                    },
                }
            );
            setTenants([...response.data]);
        } catch (error) {
            console.log(error);
        }
    };


    const getIncomes = async () => {
        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const response = await axios.get(
                "http://localhost:8080/api/v1/reports/location-base",
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`,
                    },
                }
            );
            setIncomes([...response.data]);
        } catch (error) {
            console.log(error);
        }
    };


    return (
        <div className="content-wrapper">
            {/* Content Header (Page header) */}
            <div className="content-header">
                <div className="container-fluid">
                    <div className="row mb-2">
                        <div className="col-sm-6">
                            <h1 className="m-0">Dashboard</h1>
                        </div>{/* /.col */}
                        <div className="col-sm-6">
                            <ol className="breadcrumb float-sm-right">
                                <li className="breadcrumb-item"><p>Home</p></li>
                            </ol>
                        </div>{/* /.col */}
                    </div>{/* /.row */}
                </div>{/* /.container-fluid */}
            </div>
            {/* /.content-header */}
            {/* Main content */}
            <section className="content">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <div className="px-4 pt-4 d-flex justify-content-between">
                                    <h3 className="card-title">List of Property</h3>
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
                                                </tr>)
                                            )}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>{/* /.container-fluid */}
            </section>
            <section className="content">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <div className="px-4 pt-4 d-flex justify-content-between">
                                    <h3 className="card-title">List of Most Recent Tenant</h3>
                                </div>

                                <div className="card-body">
                                    <table
                                        id="example2"
                                        className="table table-bordered table-hover"
                                    >
                                        <thead>
                                            <tr>
                                                <th>First Name</th>
                                                <th>Last Name</th>
                                                <th>Email</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {tenants.map((item) => (
                                                <tr key={item.email}>
                                                    <td>{item.firstName}</td>
                                                    <td>{item.lastName}</td>
                                                    <td>{item.email}</td>
                                                </tr>)
                                            )}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>{/* /.container-fluid */}
            </section>

            <section className="content">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <div className="px-4 pt-4 d-flex justify-content-between">
                                    <h3 className="card-title">Location Based Income</h3>
                                </div>

                                <div className="card-body">
                                    <table
                                        id="example2"
                                        className="table table-bordered table-hover"
                                    >
                                        <thead>
                                            <tr>
                                                <th>State Name</th>
                                                <th>Total Income</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {incomes.map((item) => (
                                                <tr key={item.name}>
                                                    <td>{item.name}</td>
                                                    <td>{item.value}</td>
                                                </tr>)
                                            )}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>{/* /.container-fluid */}
            </section>

            {role == "LANDLORD" ?
                <>
                    <section className="content">
                        <div className="container-fluid">
                            <div className="row">
                                <div className="col-12">
                                    <div className="card">
                                        <div className="px-4 pt-4 d-flex justify-content-between">
                                            <h3 className="card-title">List of Properties whose leases end in a month</h3>
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
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {propertiesEnding.map((property) => (
                                                        <tr key={property.id}>
                                                            <td>{property.propertyName}</td>
                                                            <td>{property.propertyType}</td>
                                                            <td>{property.noOfBedRoom}</td>
                                                            <td>{property.noOfBathRoom}</td>
                                                            <td>{property.rentAmount}</td>
                                                            <td>{property.securityDepositAmount}</td>
                                                        </tr>)
                                                    )}
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>{/* /.container-fluid */}
                    </section>
                </> : ""}



            {/* /.content */}
        </div>
    )
}

export default Dashboard