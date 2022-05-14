import { useState, useEffect } from "react";
import "./App.css";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import Products from "./components/Products";
import SignIn from "./components/SignIn";
import SignUp from "./components/SignUp";
import Admin from "./components/Admin";
import Tenant from "./components/Tenant";
import Landlord from "./components/Landlord";

function App() {
  const url = "http://localhost:8080/api/v1/products";
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getProducts();
  }, []);

  const getProducts = async () => {
    const productList = await fetchProducts();
    setProducts(productList);
  };

  const getProduct = async (id) => {
    const product = await fetchProductById(id);
    console.log(product);
  };

  const fetchProducts = async () => {
    const res = await fetch(url);
    const data = await res.json();

    return data;
  };

  const fetchProductById = async (id) => {
    const res = fetch(`{url}/{id}`);
    const data = await res.json();
    return data;
  };

  return (
    <Router>
      <div className="container">
        <Routes>
          <Route
            path="/"
            element={
              <>
                <div className="App-header">
                  <Link className="header-button" to="/sign-in">Sign IN</Link>
                  <Link className="header-button" to="/sign-up">Sign Up</Link>
                  <Link className="header-button" to="/admin">Admin Dashboard</Link>
                  <Link className="header-button" to="/tenant">Tenant Dashboard</Link>
                  <Link className="header-button" to="/landlord">Landlord Dashboard</Link>
                </div>
                <div className="App">
                  <Products products={products} />
                </div>
              </>
            }
          />
          <Route path="/sign-in" element={<SignIn />} />
          <Route path="/sign-up" element={<SignUp />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/tenant" element={<Tenant />} />
          <Route path="/landlord" element={<Landlord />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;

