import React, { useState } from 'react'
import { useNavigate} from 'react-router';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Landlord = () => {
//  import React, { useState } from "react";



///

  let navigate = useNavigate(); 
    const routeChange = () =>{ 
        let path = `/property`; 
        navigate(path);
      }
    

  return (
    <div><Link to="/login" className="header btn" >Signout</Link>
      <br/> <br/>
      
      <button className='btn' style={{backgroundColor:"green"}} onClick={routeChange}>Add properties</button>
      <button className='btn' style={{backgroundColor:"green"}}>My properties</button>

    </div>
  )
}

export default Landlord