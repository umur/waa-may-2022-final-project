import { createAsyncThunk } from "@reduxjs/toolkit";
import { useForm } from "react-hook-form";
import { useDispatch } from "react-redux";
import axios from "axios";
import { useState } from "react";


const ForgotPassword = () => {

    const dispatch = useDispatch();
    const [message, setMessage] = useState("");

    const { register, handleSubmit, formState: { errors } } = useForm({
        defaultValues: {
          email: "",
        }
      });
    
      const onSubmit = async (data) => {
        const res = await axios.post('http://localhost:8080/api/v1/uaa/forgot-password', data);
        if(res.status == 200) {
            setMessage("Link is sent to your email to change the password, Please check",);
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
          <p className="login-box-msg">Enter Your Email to search for your account</p>
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
            {message?<p className="text-primary">{message}</p> : ''} 

            <div className="row">
              <div className="col-12">
                <input
                  type="submit"
                  className="btn btn-primary btn-block"
                  value="Search"
                />
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  )
}

export default ForgotPassword