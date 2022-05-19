import axios from "axios";
import { Fragment, useState } from "react";
import React from "react";
import { getBearer } from "../../util/Utility";


const Register = () => {


    const bearer = getBearer();

    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [firstname, setFirstname] = useState();
    const [lastname, setLastname] = useState();
    const [role, setRole] = useState();



    const persistData = async (event) => {
        event.preventDefault();
        console.log(email);
        console.log(password)
        console.log(firstname)
        console.log(lastname)
        console.log(role)
        //persistProperty();
    }

    const persistProperty = async () => {
        let result = await axios.post('http://localhost:8080/api/v1/users', {headers: {Authorization: bearer}});

    }

    return (
        <div>

<h1 className="add-property-title">Register</h1>
            <div className="add-property">



                <form onSubmit={persistData}>

                    <div >
                        Email
                        <input type='text' onChange={e => setEmail(e.target.value)} />
                    </div>
                    
                    <div >
                    Password
                        <input type='text' onChange={e => setPassword(e.target.value)} />
                    </div>

                    <div >
                    FirstName
                        <input type='text' onChange={e => setFirstname(e.target.value)} />
                    </div>

                    <div >
                    Lastname
                        <input type='text' onChange={e => setLastname(e.target.value)} />
                    </div>


                    <div>
                        Role
                        <select onChange={e => setRole(e.target.value)}>

                            <option key = {3} >Tenant</option>
                            <option key = {2} >Landlord</option>

                        </select>
                    </div>


                    <button>Save</button>

                </form>

            </div>


        </div>
            
       
    )

}

export default Register;