import React, { useState, useEffect } from "react";
import "./assets/css/app/index.css";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import { AuthContext } from "./context/AuthContext";
import List from "./pages/List";
import AuthWrapper from "./auth/AuthWrapper";
import ROLE from "./auth/Role";
import PropertyDetail from "./pages/PropertyDetail";
import Dashboard from "./pages/admin/Dashboard";
import Tenants from "./pages/admin/tenant/Tenants";
import NewTenant from "./pages/admin/tenant/NewTenant";
import { ThemeProvider } from "@mui/material/styles";
import { theme } from "./theme";
import Tenant from "./pages/admin/tenant/Tenant";

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
        <Route path="/property/:id" element={<PropertyDetail />} />
        <Route
          path="/list"
          element={
            <AuthWrapper role={[ROLE.Tenant]}>
              <List />
            </AuthWrapper>
          }
        />
      </Routes>
    </AuthContext.Provider>
  );
}

export default App;
