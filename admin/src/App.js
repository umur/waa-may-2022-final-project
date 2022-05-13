import React, { useState, useEffect } from "react";
import "./assets/css/app/index.css";
import { Routes, Route, } from "react-router-dom";
import Home from "./pages/Home";
import { AuthContext } from "./context/AuthContext";
import List from "./pages/List";
import AuthWrapper from "./auth/AuthWrapper";
import ROLE from "./auth/Role";
import PropertyDetail from "./pages/PropertyDetail";
import Dashboard from "./pages/admin/Dashboard";
import Tenants from "./pages/admin/Tenant/Tenants";
import NewTenant from "./pages/admin/Tenant/NewTenant";
import { ThemeProvider } from "@mui/material/styles";
import { theme } from "./theme";
import Tenant from './pages/admin/Tenant/Tenant';
import Landlords from './pages/admin/Landlord/Landlords';

function App() {
  const [isSignedIn, setSignedIn] = useState(
    localStorage.getItem("token") ? true : false
  );
  const [user, setUser] = useState(null);
  const [role, setRole] = useState("User");
  const authContext = { isSignedIn, setSignedIn, user, setUser, role, setRole };

  // FIXME: Use for testing purpose
  useEffect(() => {
    localStorage.setItem("token", "sample token");
    setRole(ROLE.Admin);
  }, []);

  return (
    <ThemeProvider theme={theme}>
      <AuthContext.Provider value={authContext}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/property/:id" element={<PropertyDetail />} />
          <Route
            path="/list"
            element={
              <AuthWrapper role={[ROLE.User]}>
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
          <Route
            path="/admin/landlords"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Landlords />
              </AuthWrapper>
            }
          />
          <Route
            path="/admin/landlords/:id"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Tenant />
              </AuthWrapper>
            }
          />
          <Route
            path="/admin/landlords/new"
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
