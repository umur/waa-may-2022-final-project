import React, { useState, useContext, useEffect } from "react";
import "./assets/css/app/index.css";
import { Routes, Route, Navigate, useLocation } from "react-router-dom";
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
    <ThemeProvider theme={theme}>
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
          <Route
            path="/admin"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Dashboard />
              </AuthWrapper>
            }
          />
          <Route
            path="/admin/tenants"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Tenants />
              </AuthWrapper>
            }
          />
          <Route
            path="/admin/tenants/:id"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Tenant />
              </AuthWrapper>
            }
          />
          <Route
            path="/admin/tenants/new"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <NewTenant />
              </AuthWrapper>
            }
          />
        </Routes>
      </AuthContext.Provider>
    </ThemeProvider>
  );
}

export default App;
