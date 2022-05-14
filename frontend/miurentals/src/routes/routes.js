import React from "react";
import { Routes, Route } from "react-router-dom";
import Home from "../pages/Home/Home";
import Login from "../pages/Login/Login";
import Aboutus from "../pages/Aboutus/Aboutus";
import AddProperties from "../pages/AddProperties/AddProperties";
import LandProfile from "../pages/LandProfile/LandProfile";
import ListUser from "../pages/User/ListUser";
import CreateUser from "../pages/User/CreateUser";
import UserDetails from "../pages/User/UserDetails";

const Router = () => {
  return (
    <Routes>
      <Route exact path="/" element={<Home />} />
      <Route exact path="/login" element={<Login />} />
      <Route exact path="/aboutus" element={<Aboutus />} />
      <Route exact path="/addproperties" element={<AddProperties />} />
      <Route exact path="/landprofile" element={<LandProfile />} />
      <Route exact path="/listusers" element={<ListUser />} />
      <Route exact path="/createuser" element={<CreateUser />} />
      <Route exact path="/userdetails" element={<UserDetails />} />
      {/* <Route exact path="/erro" render={() => <ErrorPage />} /> */}
    </Routes>
  );
};

export default Router;
