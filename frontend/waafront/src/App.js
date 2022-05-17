import logo from "./logo.svg";
// import './App.css';
import Home from "./pages/home/Home";
import React, { useState } from "react";
import "antd/dist/antd.css";
import "./index.css";
import Nav from "./components/nav/Nav";
import SideBar from "./components/leftsidebar/sidebar";
import Breadcrumb from "./components/breadcrumb/breadcrumb";
import User from "./pages/users/index";
import { Routes, Route } from "react-router-dom";
import { Layout, Menu } from "antd";
import Properties from "./pages/properties/index";
import Login from "./pages/login/login";
import "bootstrap/dist/css/bootstrap.min.css";
import DashboardAdmin from "./pages/dashbord/dashboard-admin";
import DashboardLandlord from "./pages/dashbord/dashboard-landlord";
import DashboardTenant from "./pages/dashbord/dashboard-tenant";
import SignupForm from "./pages/users/signup-form";

import ForgotPassword from "./pages/login/forgotpassword";
import ResetPassword from "./pages/login/resetpassword";

import { useSelector } from "react-redux";

const { Header, Content, Sider } = Layout;

function App() {
  let userRole = "";
  let userName = "";

  if (window.sessionStorage.getItem("userRole")) {
    userRole = window.sessionStorage.getItem("userRole");
    userName = window.sessionStorage.getItem("userName");
  }

  //const sessionVal = JSON.parse(window.sessionStorage.getItem('token')).jwtToken ;

  {
    const user = useSelector((state) => state.user.value);

    userRole = user.role;
  }

  // const [isAdmin, setIsAdmin] = useState(false);
  // const [isTenant, setIsTenant] = useState(false);
  // const [isLandLord, setIsLandLord] = useState(false);

  return (
    <div className="App">
      <Layout>
        <Nav role={userRole}></Nav>

        <Layout style={{ padding: "0 24px 24px" }}>
          {/* <Breadcrumb></Breadcrumb> */}
          <Content
            className="site-layout-background"
            style={{
              padding: 24,
              margin: 0,
              marginTop: 20,
              minHeight: 700,
            }}
          >
            <Routes>
              <Route path="/" element={<Home></Home>} />
              <Route path="/login" element={<Login></Login>} />
              <Route path="/signup" element={<SignupForm></SignupForm>} />
              <Route
                path="/dashboard-admin"
                element={<DashboardAdmin></DashboardAdmin>}
              />
              <Route
                path="/dashboard-tenant"
                element={<DashboardTenant></DashboardTenant>}
              />
              <Route
                path="/dashboard-landlord"
                element={<DashboardLandlord></DashboardLandlord>}
              />
              <Route
                path="/forgot-password"
                element={<ForgotPassword></ForgotPassword>}
              />
               <Route
                path="/user"
                element={<User></User>}
              />
              <Route
                path="/reset-password"
                element={<ResetPassword></ResetPassword>}
              />
              <Route path="/properties" element={<Properties></Properties>} />
            </Routes>
          </Content>
        </Layout>
      </Layout>
    </div>
  );
}

export default App;
