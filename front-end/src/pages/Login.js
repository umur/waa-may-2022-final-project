import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useForm } from "react-hook-form";
import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';
import { useDispatch } from 'react-redux';
import { authActions } from '../store';


export const Login = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [badCredential, setBadCredential] = useState("");

  const { register, handleSubmit, formState: { errors } } = useForm({
    defaultValues: {
      email: "",
      password: "",
    }
  });

  const doLogin = createAsyncThunk('/login', async (credendials) => {
    try {
      const res = await axios.post('http://localhost:8080/api/v1/uaa/login', credendials);
      return res.data;
    } catch (error) {
      // console.log(error);
      setBadCredential(error.response.data.message)
    }
  })

  const onSubmit = async (data) => {
    const result = await dispatch(doLogin(data));
    console.log({result})
    if(result?.payload?.accessToken) {
      dispatch(authActions.loginSuccessful());
      navigate('/dashboard');
      localStorage.setItem('token', JSON.stringify(result.payload))
    }else{
      navigate('/login');
    }
  }


  return (
    <div className='hold-transition login-page'>
      <div className="login-box">
        <div className="login-logo">
          <h4>Property Management</h4>
        </div>
        <div className="card">
          <div className="card-body login-card-body">
            <p className="login-box-msg">Sign in</p>
            <form action="" method="post" onSubmit={handleSubmit(onSubmit)}>
              <div className="input-group mb-3">
                <input type="email" {...register("email", { required: 'Email is required' })} className="form-control" placeholder="Email" />
                <span className='text-danger'>{errors.email?.message}</span>
                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-envelope" />
                  </div>
                </div>
              </div>
              <div className="input-group mb-3">
                <input type="password" {...register("password", { required: 'Password is required' })} className="form-control" placeholder="Password" />
                <span className='text-danger'>{errors.password?.message}</span>
                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-lock" />
                  </div>
                </div>
              </div>
              {badCredential? <p className='text-danger'>{badCredential}</p> : ""}
              <div className="row">
                <div className="col-12">
                  <input
                    type="submit"
                    className="btn btn-primary btn-block"
                    value="Sign In"
                  />
                </div>
              </div>
            </form>
            <p className="mb-1">
              <a href="forgot-password.html">I forgot my password</a>
            </p>
            <p className="mb-0">
              <Link to='/signup'>Register a new membership</Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  )
}
