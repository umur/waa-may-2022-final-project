import { useState, useEffect } from "react";
import Properties from "../Shared/Properties/Properties";
import SearchBar from "../Shared/SearchBar/SearchBar";
import Service from "../Shared/Service";

const Tenant = () => {
  const [properties, setProperties] = useState([]);

  useEffect(() => {
    getProperties();
  }, []);

  const getProperties = async () => {
    const propertyList = await fetchProperties();
    setProperties(propertyList);
  };

  const fetchProperties = async () => {
    const res = await fetch(Service.TenantGetAllProperties);
    const data = await res.json();
    console.log(data);

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
