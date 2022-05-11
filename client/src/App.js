import React, { useState, useContext, useEffect } from "react";
import "./assets/css/app/index.css";
import { Routes, Route, Navigate, useLocation } from "react-router-dom";
import Home from "./pages/Home";
import { AuthContext } from "./context/AuthContext";
import List from "./pages/List";
import AuthWrapper from "./auth/AuthWrapper";
import ROLE from "./auth/Role";
import PropertyDetail from "./pages/PropertyDetail";
import Dashboard from './pages/admin/Dashboard';
import Tenants from './pages/admin/tenant/Tenants';
import NewTenant from './pages/admin/tenant/NewTenant';

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
    setRole("admin")
  }, []);

  return (
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
          path="/admin/tenants/new"
          element={
            <AuthWrapper role={[ROLE.Admin]}>
              <NewTenant />
            </AuthWrapper>
          }
        />
      </Routes>
    </AuthContext.Provider>
  );
}

export default App;
