import React, { useState } from 'react'
import { useNavigate} from 'react-router';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Axios from 'axios';

const Landlord = () => {
const [myProperties, setMyProperties] = useState({})

  const getMyProperties = () => {
    Axios.get("http://localhost:8080/api/users/").then((response) => {
      setMyProperties(response.data);
    })
  }

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
      <button className='btn' style={{backgroundColor:"green"}} onClick={getMyProperties}>My properties</button>

    </div>
  )
}

export default Landlord