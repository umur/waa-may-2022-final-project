import { isVisible } from '@testing-library/user-event/dist/utils';
import React, {useState} from 'react'
import api from '../../api/posts'

function ForgotPassword(){


    const [email, setEmail] =useState('');

    const [visibilityCSS, setVisibilityCSS] = useState('hidden');

    const sendInfo = async ()=>{
        setVisibilityCSS('visible')
        const bodyParams = { email, link: 'http://localhost:3000/reset-password'};
        await api.post(`/api/v1/password/forgot-password`,  bodyParams);        
    }

    return (
        <>
            <div className='row'>
                <div className='col-sm-4'>

                </div>
                <div className='col-sm-4'>
                    <div className="card-body">
                        <div className='form-group'>
                            <label>Email</label>
                            <input
                                type='text'
                                value={email}
                                onChange={(e)=>setEmail(e.target.value)}
                                className='form-control form-control-sm'>
                            </input>
                        </div>
                        <br/>
                    
                        <div className='form-group'>
                            <button className='btn btn-sm btn-primary' onClick={sendInfo}>Reset Password</button>
                        </div>

                        <div style={{visibility: visibilityCSS}}>
                            <br/>
                            <p>Check link on your email</p>
                        </div>
                    </div>  
                </div>                        
            </div>
        </>
    )

}

export default ForgotPassword;