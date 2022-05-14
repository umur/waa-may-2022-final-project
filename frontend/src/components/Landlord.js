import { useState, useEffect } from "react";
import Products from "./Products";

const Landlord = () => {
    const url = "http://localhost:8080/api/v1/products";
    const [products, setProducts] = useState([]);
  
    useEffect(() => {
      getProducts();
    }, []);
  
    const getProducts = async () => {
      const productList = await fetchProducts();
      setProducts(productList);
    };
  
    const fetchProducts = async () => {
      const res = await fetch(url);
      const data = await res.json();
  
      return data;
    };
  
    return (
      <div className="App">
        <Products products={products} />
      </div>
    );
}

export default Landlord;