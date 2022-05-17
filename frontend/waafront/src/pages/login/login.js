import React, { useState } from 'react'
import { useNavigate, Link } from "react-router-dom";
import api from '../../api/posts'
import { useDispatch } from 'react-redux';
import { login } from '../../features/user-slice';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [res, setRes] = useState('');
    let navigate = useNavigate();

    const dispath = useDispatch();

    const sendInfo = async () => {
        const credentials = { email: email, password: password };

        try{

            const response = await api.post('api/v1/login', credentials);
            setRes(response.data);
    
            const theTokenString = JSON.stringify(response.data) ;
            const theToken=JSON.parse(theTokenString).jwtToken;
            console.log(theToken);
            
            const decodedString = atob(theToken.split('.')[1]);
            const userRole = JSON.parse(decodedString).role[0].authority;
            const id=JSON.parse(decodedString).id;
            const userName = JSON.parse(decodedString).sub;
    
            window.sessionStorage.setItem("userRole", userRole);
            window.sessionStorage.setItem("userName", userName);
            window.sessionStorage.setItem("token", theToken);
            window.sessionStorage.setItem("id", id);

            if(userRole==='ADMIN'){
                navigate(`/dashboard-admin`);
            }else if(userRole==='TENANT'){
                navigate(`/dashboard-tenant`);
            }else if(userRole==='LANDLORD'){
                navigate(`/dashboard-landlord`);
            }
            
    
            dispath(login({ role: userRole, email: userName, token:theToken, id:id }));
        }catch (err){
            if(err.response){
                alert ('Login failed');
            }else{
                alert ('Login failed');
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
                            <label>Eail</label>
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