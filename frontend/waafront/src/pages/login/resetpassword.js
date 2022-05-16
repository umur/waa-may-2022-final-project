import React, { useState, useEffect } from 'react'
import api from '../../api/posts'
import { useSearchParams, useNavigate } from "react-router-dom";

function ResetPassword() {
    let navigate = useNavigate();
    const [password, setPassword] = useState('');
    const [token, setToken] = useState('');
    const [jwtRes, setJwtRes] = useState('');
    const [searchParams] = useSearchParams();
    const [visibilityCSS, setVisibilityCSS] = useState('hidden');

    useEffect(() => {
        setToken(searchParams.get('token'));
    }, []);

    const sendInfo = async () => {
       

        const bodyParams = { password: password, token: token };
   
        try {
            const resp = await api.put(`/api/v1/password/reset-password`,  bodyParams);

            if (resp.status === 200) {
                const response = await api.post('api/v1/login', {email: resp.data?.email, password});
                
                window.sessionStorage.setItem("token", JSON.stringify(response.data?.jwtToken));
                navigate(`/dashboard-admin`);
            }
        } catch (error) {
            setVisibilityCSS('visible');
        }
        
        
    }

    return (
        <>
            <div className='row'>
                <div className='col-sm-4'>

                </div>
                <div className='col-sm-4'>
                    <div className="card-body">
                        <div className='form-group'>
                            <label>Password</label>
                            <input
                                type='password'
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                className='form-control form-control-sm'>
                            </input>
                        </div>

                        <div className='form-group'>
                            <label>Confirm Password</label>
                            <input
                                type='password'
                                className='form-control form-control-sm'>
                            </input>
                        </div>
                        <br/>
                        <div className='form-group'>
                            <button className='btn btn-sm btn-primary' onClick={sendInfo}>Save</button>
                        </div>

                        <div style={{visibility: visibilityCSS}}>
                            <br/>
                            <p>Error try again later</p>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

export default ResetPassword;