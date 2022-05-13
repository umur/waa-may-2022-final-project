import React from "react";
import { ReactComponent as Logo } from "../../assets/img/logo.svg";
import "./index.css";

const Header = () => {
  return (
    <div className="header">
      <Logo />
      <div className="logo-text">GigaBits</div>
    </div>
  );
};
export default Header;
