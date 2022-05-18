import { useState, useEffect } from "react";
import Properties from "../Shared/Properties/Properties";
import LandlordProperties from "./LandlordProperties/LandlordProperties";
import SearchBar from "../Shared/SearchBar/SearchBar";
import Service from "../Shared/Service";
import { Link } from "react-router-dom";
import Button from "react-bootstrap/Button";

import "./Landlord.css";

const Landlord = () => {
  const [isLeaseEnding, setIsLeaseEnding] = useState(false);

  const [properties, setProperties] = useState([]);
  const [keyword, setKeyword] = useState("");

  useEffect(() => {
    getProperties();
  }, []);

  const filterProperties = () => {
    var myHeaders = new Headers();
    myHeaders.append("email", "hassan@miu.edu");

    var requestOptions = {
      method: "GET",
      headers: myHeaders,
      redirect: "follow",
    };///`Fairfield/false

    fetch(
      `${Service.LandlordFilterByCity}${keyword}`,
      requestOptions
    )
      .then((response) => response.json())
      .then((result) => {
        setProperties(result);
      })
      .catch((error) => console.log("error", error));
  };

  const getProperties = async () => {
    const propertyList = await fetchProperties(Service.GetPropertiesOwnedBy);

    console.log("Normal : ", propertyList);
    setProperties(propertyList);
  };

  const fetchProperties = async (url) => {
    const res = await fetch(url);
    const data = await res.json();

    return data;
  };

  const getLeasesInOneMonth = async () => {
    const result = await fetchProperties(Service.LeasesInOneMonth);
    const newPropList = [];
    result.forEach((e) => {
      newPropList.push(e.property);
    });
    // const propertyList = [...result.property];
    // const propertyList = result.filter((p) => p.property);
    // console.log("result : ", result);
    console.log("propertyList  leases in one month : ", newPropList);
    setProperties(newPropList);
  };

  const toggleLeaseEnds = async (status) => {
    console.log("status : ", status);
    setIsLeaseEnding(status);

    if (status) {
      getLeasesInOneMonth();
    } else {
      getProperties();
    }
    // if (status) {
    // const propertyList = await fetchProperties("http://172.19.141.27:8080/api/properties/landlord/leases/john@gmail.com");
    // console.log("LIST : ", propertyList);
    // setProperties(propertyList);
    // } else {
    //   getProperties();
    // }
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

      <Link to="/add-property">
        <button>Add Property</button>
      </Link>

      <div>
        Display Properties with Lease ending in one month
        <p style={{ margin: "4px", display: "inline-block" }}>
          <input
            type="checkbox"
            checked={isLeaseEnding}
            value={isLeaseEnding}
            onChange={(e) => toggleLeaseEnds(e.currentTarget.checked)}
          />
        </p>
      </div>
      <Properties properties={properties} />
    </div>
  );
};

export default Landlord;
