import { useState, useEffect } from "react";
import Properties from "../Shared/Properties/Properties";
import SearchBar from "../Shared/SearchBar/SearchBar";

const Tenant = () => {
  const url = "http://localhost:5000/property";
  const [properties, setProperties] = useState([]);

  useEffect(() => {
    getProperties();
  }, []);

  const getProperties = async () => {
    const propertyList = await fetchProperties();
    setProperties(propertyList);
  };

  const fetchProperties = async () => {
    const res = await fetch(url);
    const data = await res.json();

    return data;
  };

  return (
    <div className="landlord">
      <SearchBar />
      <Properties properties={properties} />
    </div>
  );
};

export default Tenant;
