import React, { useState } from 'react'
import { useNavigate, Link } from "react-router-dom";
import api from '../../api/posts'

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [res, setRes] = useState('');
    let navigate = useNavigate();
    const sendInfo = async () => {
        const credentials = { email: email, password: password };
        try {
            const response = await api.post('api/v1/login', credentials);
            setRes(response.data);
            window.sessionStorage.setItem("token", JSON.stringify(res));
            navigate(`/dashboard-admin`);
        } catch (err) {
            if (err.response) {

            } else {
                console.log('Error')
            }
        }
    }

    return (
        <div className='row'>
            <div className='col-sm-4'>

            </div>
            <div className='col-sm-4'>
                <div className="card">
                    <div className="card-header">
                        Login
                    </div>
                    <div className="card-body">
                        <div className='form-group'>
                            <label>Email</label>
                            <input
                                type='text'
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                className='form-control form-control-sm'>
                            </input>
                        </div>
                        <br></br>
                        <div className='form-group'>
                            <label>Password</label>
                            <input
                                type='password'
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                className='form-control form-control-sm'>
                            </input>
                        </div>
                        <br></br>
                        <div className='form-group'>
                            <button className='btn btn-sm btn-primary' onClick={sendInfo}>Login</button>
                            <Link to='/forgot-password'>Forgot password? Reset from here.</Link>
                        </div>
                    </div>
                </div>
            </div>
            <div className='col-sm-4'>

            </div>
        </div>
    )
}

export default Login