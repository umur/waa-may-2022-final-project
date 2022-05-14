import React from "react";
import { ReactComponent as Logo } from "../../assets/img/logo.svg";
import "./index.css";
import Filter from "../Filter";
import { Link } from "react-router-dom";
import LogoutIcon from "@mui/icons-material/Logout";
import Button from "@mui/material/Button";

const Header = ({ type = "default", onFilter = () => {} }) => {
  return (
    <div className="header">
      <Link to="/" style={{ textDecoration: "none" }}>
        <div className="logo">
          <Logo />
          <div className="logo-text">GigaBits</div>
        </div>
      </Link>
      <div className="right">
        {type === "filter" && <Filter onFilter={onFilter} />}
        <div>
          <Button variant="text">
            <LogoutIcon /> Logout
          </Button>
        </div>
      </div>
    </div>
  );
};
export default Header;
