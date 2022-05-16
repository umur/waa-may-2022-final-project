import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import api from '../../api/posts';

const SignupForm = () => {
    const [firstName, setFirstName] =useState('');
    const [lastName, setLastName] =useState('');
    const [email, setEmail] =useState('');
    const [password, setPassword] =useState('');
    const [role, setRole] =useState('');
    let navigate = useNavigate();

    const handleSubmit = async (e)=>{
        e.preventDefault();

        const newUser = {id: 3, firstname:firstName, lastname: lastName, email:email, password:password, role:role}

        try{
            const response = await api.post('api/v1/users', newUser);

            alert ('User created successfully');

            setEmail('');
            setFirstName('');
            setLastName('');
            setPassword('');
            setRole('');
            navigate(`/login`);

        }catch(err){
            console.log('Error: ${ err.message }');
        }

    }
    return (
        <div className='row'>
            <div className='col-sm-3'></div>
            <div className='col-sm-6'>
                <div className="card">
                    <div className="card-header">
                        Signup
                    </div>
                    <div className="card-body">
                        <div className='form-group'>
                            <label>First Name</label>
                            <input
                                type='text'
                                value={firstName}
                                onChange={(e)=>setFirstName(e.target.value)}
                                className='form-control form-control-sm' />
                        </div>
                        <br/>
                        <div className='form-group'>
                            <label>Last Name</label>
                            <input
                                type='text'
                                value={lastName}
                                onChange={(e)=>setLastName(e.target.value)}
                                className='form-control form-control-sm' />
                        </div>
                        <br/>
                        <div className='form-group'>
                            <label>Email</label>
                            <input
                                type='email'
                                value={email}
                                onChange={(e)=>setEmail(e.target.value)}
                                className='form-control form-control-sm' />
                        </div>
                        <br/>
                        <div className='form-group'>
                            <label>Password</label>
                            <input
                                type='password'
                                value={password}
                                onChange={(e)=>setPassword(e.target.value)}
                                className='form-control form-control-sm' />
                        </div>
                        <br/>
                                            
                        <div className='form-group'>
                            <label>Signup As</label>
                            <select name="cars" id="cars"
                            className='form-control form-control-sm'
                            onChange={(e)=>setRole(e.target.selectedOptions[0].text)}>
                                <option value="">--Select--</option>
                                <option value="1">LANDLORD</option>
                                <option value="2">TENANT</option>
                                
                            </select>
                            
                        </div>

                        <br/>
                        <br/>
                        <button className='btn btn-sm btn-primary' onClick={handleSubmit}>Submit</button>
                        
                    </div>
                </div>
            </div>
            <div className='col-sm-3'></div>
        </div>
    )
}

export default SignupForm;