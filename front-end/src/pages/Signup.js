import React from 'react'
import { useForm } from 'react-hook-form'
import { Link, useNavigate } from 'react-router-dom'
import { createAsyncThunk } from '@reduxjs/toolkit';
import { useDispatch } from 'react-redux';
import axios from 'axios';

const Signup = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { register, handleSubmit, watch, formState: { errors } } = useForm({
        defaultValues: {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            confirmPassword: "",
        }
    });

    
    
  const doSignup = createAsyncThunk('/signup', async (credendials) => {
    try {
      const res = await axios.post('http://localhost:8080/api/v1/uaa/signup', credendials);
      return res.data;
    } catch (error) {
      console.log(error);
    }
  })

    const onSubmit = async (data) => {
        const withOutConfirmPass = {
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
            password: data.password,
        }
        const result = await dispatch(doSignup(withOutConfirmPass));
        alert('Successfully registered, Login in to continue');
        navigate('/login');
      }
    

    return (
        <div className='hold-transition register-page'>
            <div className="register-box">
                <div className="register-logo">
                    <h4>Property Management</h4>
                </div>
                <div className="card">
                    <div className="card-body register-card-body">
                        <p className="login-box-msg">Register a new membership</p>
                        <form action="" method="post" onSubmit={handleSubmit(onSubmit)}>
                            <div className="input-group mb-3">
                                <input
                                    type="text"
                                    {...register("firstName", { required: 'First Name is required' })}
                                    className="form-control"
                                    placeholder="First name" />
                                    <p className='text-danger'>{errors.firstName?.message}</p>
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-user" />
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input
                                    type="text"
                                    {...register("lastName", { required: 'Last Name is required' })}
                                    className="form-control"
                                    placeholder="Last name" />
                                    <p className='text-danger'>{errors.lastName?.message}</p>
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-user" />
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input
                                    type="email"
                                    {...register("email", { required: 'Email is required' })}
                                    className="form-control"
                                    placeholder="Email" />
                                    <p className='text-danger'>{errors.email?.message}</p>
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-envelope" />
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input
                                    type="password"
                                    {...register("password", { required: 'Password is required' })}
                                    className="form-control"
                                    placeholder="Password" />
                                    <p className='text-danger'>{errors.password?.message}</p>
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-lock" />
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input
                                    type="password"
                                    className="form-control"
                                    {...register(
                                        "confirmPassword",
                                        { 
                                            required: 'Confirm Password is required',
                                            validate: (val) => {
                                                if (watch('password') != val) {
                                                  return "Your passwords do no match";
                                                }
                                              },
                                        }
                                        )
                                    }
                                    placeholder="Retype password" />
                                    <p className='text-danger'>{errors.confirmPassword?.message}</p>
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-lock" />
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-12">
                                    <button type="submit" className="btn btn-primary btn-block">Register</button>
                                </div>
                            </div>
                        </form>
                        <Link to='/login'>I already have a membership</Link>

                    </div>
                </div>
            </div>

        </div>
    )
}

export default Signup