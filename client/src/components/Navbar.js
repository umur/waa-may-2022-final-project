import "./NavbarStyle.css";
import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <div className="header">
      <Link to="/">
        <h1>Welcome to ...</h1>
      </Link>
      <ul className="navlist">
        <li>
          <Link to="/"> Home </Link>
        </li>
        <li>
          <Link to="/properties" className="btn">
            Properties
          </Link>
        </li>
        <li>
          <Link to="/register"> Register </Link>
        </li>
        <li>
          <Link to="/login"> Login </Link>
        </li>
      </ul>
    </div>
  );
};

export default Navbar;
