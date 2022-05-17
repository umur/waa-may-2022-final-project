import React, { useEffect, useState } from "react";
import BreadCrumb from "../components/BreadCrumb";

import axios from "axios";
import { Link, Route, Routes, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";

const User = () => {

    const [state, setState] = useState([]);
    const [changePswBtnClicked, setChangePswBtnClicked] = useState(false);
    const [statusChangeBtnClicked, setStatusChangeBtnClicked] = useState(false);

    // const [userId, setUserId] = useState(null);

    const navigate = useNavigate();

    const { register, handleSubmit, watch, formState: { errors } } = useForm({
        defaultValues: {
            password: "",
        }
    });

    useEffect(() => {
        getUsers();
    }, []);

    const getUsers = async () => {
        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const response = await axios.get(
                "http://localhost:8080/api/v1/users",
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`,
                    },
                }
            );
            console.log(response.data)
            setState(response.data)
        } catch (error) {
            console.log(error);
        }
    };

    const handleChangePswBtnClick = (userId) => {
        navigate(`/dashboard/change-password/${userId}`);
    }

    const handleStatusChangeBtn = async(userId) => {
        // setUserId(userId);

        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const response = await axios.post(`http://localhost:8080/api/v1/users/user-active/${userId}`,null,
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`,
                    },
                }
            );
            if (response.status == 200) {
                // navigate("/dashboard/rent-property");
               const u = state.map(item => {
                    if(item.id == userId) {
                        item.active = item.active ? false : true;
                    }
                    return item;
               })
               setState(u);
            }
        } catch (error) {
            console.log(error)
        }

    }

    return (
        <div className="content-wrapper">
            <BreadCrumb name="Users" />
            <section className="content">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <div className="px-4 pt-4 d-flex justify-content-between">
                                    <h3 className="card-title">List of Users</h3>
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
                                                <th>Is Active</th>
                                                <th>Role</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>


                                            {state.map((user) => (
                                                <tr key={user.id}>
                                                    <td>{user.firstName}</td>
                                                    <td>{user.lastName}</td>
                                                    <td>{user.email}</td>
                                                    <td>{user.active ? "Active" : "Not Active"}</td>
                                                    <td>{user.role}</td>
                                                    <td>
                                                        <button onClick={() => handleChangePswBtnClick(user.id)} className="btn btn-primary">Change Psw</button>
                                                        <button
                                                            onClick={() => handleStatusChangeBtn(user.id)} className="btn btn-primary ml-2">{user.active ? "Make Deactivate" : "Make Active"}
                                                        </button>

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

export default User;
