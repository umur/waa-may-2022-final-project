import React, { useState, useEffect } from "react";
import "./assets/css/app/index.css";
import { Routes, Route, } from "react-router-dom";
import { AuthContext } from "./context/AuthContext";
import AuthWrapper from "./auth/AuthWrapper";
import ROLE from "./auth/Role";
import Dashboard from "./pages/Dashboard";
import Tenants from "./pages/Tenant/Tenants";
import NewTenant from "./pages/Tenant/NewTenant";
import { ThemeProvider } from "@mui/material/styles";
import { theme } from "./theme";
import Tenant from './pages/Tenant/Tenant';
import Landlords from './pages/Landlord/Landlords';

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
          <Route
            path="/"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Dashboard />
              </AuthWrapper>
            }
          />
          <Route
            path="/tenants"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Tenants />
              </AuthWrapper>
            }
          />
          <Route
            path="/tenants/:id"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Tenant />
              </AuthWrapper>
            }
          />
          <Route
            path="/tenants/new"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <NewTenant />
              </AuthWrapper>
            }
          />
          <Route
            path="/landlords"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Landlords />
              </AuthWrapper>
            }
          />
          <Route
            path="/landlords/:id"
            element={
              <AuthWrapper role={[ROLE.Admin]}>
                <Tenant />
              </AuthWrapper>
            }
          />
          <Route
            path="/landlords/new"
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
