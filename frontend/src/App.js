import { useState, useEffect } from "react";
import "./App.css";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import Header from "./Components/Shared/Header/Header";
import SignIn from "./Components/SignIn/SignIn";
import SignUp from "./Components/SignUp/SignUp";
import Admin from "./Components/Admin/Admin";
import Tenant from "./Components/Tenant/Tenant";
import Landlord from "./Components/Landlord/Landlord";
import AddProperty from "./Components/Landlord/LandlordProperties/AddProperty/AddProperty";

function App() {
  const url = "http://localhost:8080/api/v1/products";

  useEffect(() => {
    // getProducts();
    // getTasks();
  }, []);

  return (
    <Router>
      <Header />   
      <div className="container">
        <Routes>
          //add-property
          <Route path="/sign-in" element={<SignIn />} />
          <Route path="/sign-up" element={<SignUp />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/tenant" element={<Tenant />} />
          <Route path="/landlord" element={<Landlord />} />
          <Route path="/add-property" element={<AddProperty />} />
          <Route path="/" element={<SignIn />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;

