import { useState } from "react"
import { BrowserRouter as Router, Routes, Link, Route } from "react-router-dom"
import Login from "./components/Login";
import Signup from "./components/Signup";
import Tenant from "./components/Tenant";
import Admin from "./components/Admin";
import Landlord from "./components/Landlord";
import PropertyDetails from "./components/PropertyDetails";

function App() {
//check also
  return (
    <div className="container">
      <h1>Welcome to FireBNB</h1>
      <Router>
        <nav>
          <div>  
            <Link to="/" className="header btn">Home</Link>
            <Link to="/login" className="header btn" >Login</Link>
            <Link to="/signup" className="header btn" >Signup</Link>
          </div>
        </nav>
        <Routes>
          <Route path="/" />
          <Route path="/login" element={<Login/>} />
          <Route path="/signup" element={<Signup/>} />
          <Route path="/login/tenant" element={<Tenant/>} />
          <Route path="/login/admin" element={<Admin/>} />
          <Route path="/login/landlord" element={<Landlord/>} />
          <Route path="/property" element={<PropertyDetails/>} />
        </Routes>
      </Router>

    </div>
  );
}

export default App;
