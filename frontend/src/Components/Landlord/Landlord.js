import { useState, useEffect } from "react";
import Properties from "../Shared/Properties/Properties";
import SearchBar from "../Shared/SearchBar/SearchBar";

const Landlord = () => {
  
  const [isLeaseEnding, setIsLeaseEnding] = useState(false);

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
      <div>
        Display Properties with Lease ending in one month
        <p style={{ margin: "4px", display: "inline-block" }}>
                <input
                  type="checkbox"
                  checked={isLeaseEnding}
                  value={isLeaseEnding}
                  onChange={(e) => setIsLeaseEnding(e.currentTarget.checked)}
                />
              </p>
        
      </div>
      <Properties properties={properties} />
    </div>
  );
};

export default Landlord;
