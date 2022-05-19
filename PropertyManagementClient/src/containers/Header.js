import React from "react";

import { Link } from 'react-router-dom';
import { getRole } from "../util/Utility";
import { useNavigate } from "react-router";

// import logo from '../../assets/logos/miu-logo.png';



const Header = (props) => {

    const role = getRole();

    let result;

    const navigate = useNavigate();

    const logOutEvent =(event) => {
        event.preventDefault();
        localStorage.removeItem('uid');
        localStorage.removeItem('urole');
        localStorage.removeItem('token');
        console.log(props);
        props.setToken();
        navigate("/");
    }

    if (role === "TENANT") {
        result = <header>
            <nav>
                <ul>
                    <li><Link to="/properties"> Properties</Link></li>
                    <li><a onClick={logOutEvent}>Logout</a></li>
                    
                </ul>
            </nav>
        </header>
    } else if (role === "LANDLORD") {
        result = <header>
            <nav>
                <ul>
                <li><Link to="/properties"> Properties</Link></li>
                    <li><Link to="/landlord-charts"> Landlord Charts</Link></li>
                    <li><Link to="/add-property"> Add Property</Link></li>
                    <li><a onClick={logOutEvent}>Logout</a></li>
                </ul>
            </nav>
        </header>
    } else {
        result = <header>
            <nav>
                <ul>
                    <li><Link to="/admin-charts"> Admin Charts</Link></li>
                    <li><a onClick={logOutEvent}>Logout</a></li>
                </ul>
            </nav>
        </header>
    } 



    return result;
}



export default Header;