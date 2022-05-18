import { useState, useEffect } from "react";
import Properties from "../Shared/Properties/Properties";
import Service from "../Shared/Service";
import Button from "react-bootstrap/Button";
import "./Tenant.css"

const Tenant = () => {
  const [properties, setProperties] = useState([]);
  const [keyword, setKeyword] = useState("");

  useEffect(() => {
    getProperties();
  }, []);

  const getProperties = async () => {
    const propertyList = await fetchProperties();
    setProperties(propertyList);
  };

  const filterProperties = () => {
    var myHeaders = new Headers();
    myHeaders.append("email", "hassan@miu.edu");

    var requestOptions = {
      method: "GET",
      headers: myHeaders,
      redirect: "follow",
    };///`Fairfield/false

    fetch(
      `${Service.TenantFilterByCity}${keyword}/false`,
      requestOptions
    )
      .then((response) => response.json())
      .then((result) => {
        setProperties(result);
      })
      .catch((error) => console.log("error", error));
  };

  const fetchProperties = async () => {
    const res = await fetch(Service.TenantGetAllProperties);
    const data = await res.json();
    return data;
  };

  return (
    <div className="landlord">
      <div className="Top-bar"></div>
      <div className="mainStyle">
        <label>Search for Property &nbsp;</label>
        <input
          className="BarStyling"
          key="random1"
          value={keyword}
          placeholder={"City"}
          onChange={(e) => setKeyword(e.target.value)}
        />

        <Button variant="primary" className="button" onClick={filterProperties}>
          Start Search
        </Button>
      </div>
      <Properties properties={properties} />
    </div>
  );
};

export default Tenant;
