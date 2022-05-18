import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import BreadCrumb from '../components/BreadCrumb';

const ChangePassword = () => {
    const { id } = useParams();
    const navigate = useNavigate();

    const { register, handleSubmit, watch, formState: { errors } } = useForm({
        defaultValues: {
            password: "",
            confirmPassword: ""
        }
    });


    const onSubmit = async (data) => {
        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const res = await axios.post(`http://localhost:8080/api/v1/uaa/change-password/${id}`, data,
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`
                    },
                });
                if (res.status === 200) {
                    navigate("/dashboard/users");
                }
        } catch (error) {
            alert(error.response?.data?.message)
        }
    }

    return (

        <div className="content-wrapper">
            <BreadCrumb name="Change Password" />
            <section className="content">
                <div className="container-fluid">
                    <div className="card card-default">
                        <form action="" method="post" onSubmit={handleSubmit(onSubmit)}>
                            <div className="card-body">
                                <div className="row">
                                    <div className="form-group col-md-6 mb-3">
                                        <input
                                            type="password"
                                            {...register("password", { required: 'Password is required' })}
                                            className="form-control"
                                            placeholder="Password" />
                                        <p className='text-danger'>{errors.password?.message}</p>

                                    </div>
                                    <div className="form-group col-md-6 mb-3">
                                        <input
                                            type="password"
                                            className="form-control"
                                            {...register(
                                                "confirmPassword",
                                                {
                                                    required: 'Confirm Password is required',
                                                    validate: (val) => {
                                                        if (watch('password') !== val) {
                                                            return "Your passwords do no match";
                                                        }
                                                    },
                                                }
                                            )
                                            }
                                            placeholder="Retype password" />
                                        <p className='text-danger'>{errors.confirmPassword?.message}</p>
                                    </div>

                                </div>
                                <div className='row'>
                                    <div className="col-3">
                                        <input
                                            type="submit"
                                            className="btn btn-primary btn-block"
                                            value="Reset Password"
                                        />
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </div>

    )
}

export default ChangePassword