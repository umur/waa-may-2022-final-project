import React, { useState, useEffect } from "react";
import "./assets/css/app/index.css";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import { AuthContext } from "./context/AuthContext";
import List from "./pages/List";
import AuthWrapper from "./auth/AuthWrapper";
import ROLE from "./auth/Role";
import PropertyDetail from "./pages/PropertyDetail";
import Login from "./pages/Login";
import Register from "./pages/Register";
import ForgotPassword from "./pages/ForgotPassword";
import Checkout from 'pages/Checkout';


function App() {
  const [isSignedIn, setSignedIn] = useState(
    localStorage.getItem("token") ? true : false
  );
  const [user, setUser] = useState(null);
  const [role, setRole] = useState("User");

  const authContext = { isSignedIn, setSignedIn, user, setUser, role, setRole };

  return (
    <AuthContext.Provider value={authContext}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/forgotpassword" element={<ForgotPassword />} />
        <Route path="/property/:id" element={<PropertyDetail />} />
        <Route
          path="/list"
          element={
            <AuthWrapper role={[ROLE.Tenant]}>
              <List />
            </AuthWrapper>
          }
        />
        
        <Route path="/checkout/:id" element={<Checkout order={{amount: 1999, name:"Tenant Fullname", email: "tenant@gmail.com"}} />} />

      </Routes>
    </AuthContext.Provider>
  );
}

export default App;
