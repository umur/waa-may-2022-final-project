import { useState, useEffect } from "react";
import Properties from "../Shared/Properties/Properties";
import SearchBar from "../Shared/SearchBar/SearchBar";
import "./Admin.css";

const Admin = () => {
  const urlTenants = "http://localhost:5000/tenants";
  const url2Landlords = "http://localhost:5000/landlords";
  const url3 = "http://localhost:5000/property";
  const url4 = "http://localhost:5000/property"; // toggle Active status

  const [landlords, setLandlords] = useState([]);
  const [tenants, setTenants] = useState([]);
  const [properties, setProperties] = useState([]);
  const totalIncome = 123500;

  useEffect(() => {
    getProperties();
    getLandlords();
    getTenants();
  }, []);

  const getProperties = async () => {
    const propertyList = await fetchProperties(url3);
    setProperties(propertyList);
  };

  const getLandlords = async () => {
    const landlordList = await fetchProperties(url2Landlords);
    setLandlords(landlordList);
  };

  const getTenants = async () => {
    const tenantList = await fetchProperties(urlTenants);
    setTenants(tenantList);
  };

  const fetchProperties = async (url) => {
    const res = await fetch(url);
    const data = await res.json();

    return data;
  };

  const toggleLandlordStatus = async (status, landlord) => {
    setLandlords(
      landlords.map((l) => {
        if (landlord.id == l.id) {
          l.active = status;
        }
        return l;
      })
    );
    // const toggle = await fetchProperties(url4);
  };

  const toggleTenantStatus = async (status, tenant) => {
    setTenants(
      tenants.map((l) => {
        if (tenant.id == l.id) {
          l.active = status;
        }
        return l;
      })
    );
  };

  return (
    <div className="admin ">
      <SearchBar />

      {/* //Total Income : $123456 */}
      <div className="total-income">
        <h2>
          Total Income{" "}
          {"$" +
            totalIncome.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")}
        </h2>
      </div>

      {/* // 10 most recent tenants */}
      <div>
        <div className="list-container">
          <h2>Most Recent Landlords</h2>
          <ul className="list-group">
            <li className="list-group-item heading">
              <p className="inner-p">Name</p>
              <p className="inner-p">Activate/Deactivate</p>
            </li>
            {landlords.map((landlord, index) => (
              <>
                <li className="list-group-item" key={index}>
                  <p className="inner-p">
                    {landlord.firstName + " " + landlord.lastName}
                  </p>
                  <p className="inner-p">
                    <input
                      type="checkbox"
                      checked={landlord.active}
                      value={landlord.active}
                      onChange={(e) => {
                        toggleLandlordStatus(e.currentTarget.checked, landlord);
                      }}
                    />
                  </p>
                </li>
              </>
            ))}
          </ul>
        </div>
        <div className="list-container">
          <h2>Most Recent Tenants</h2>
          <ul className="list-group">
            <li className="list-group-item heading">
              <p className="inner-p">Name</p>
              <p className="inner-p">Activate/Deactivate</p>
            </li>
            {tenants.map((tenant, index) => (
              <>
                <li className="list-group-item" key={index}>
                  <p className="inner-p">
                    {tenant.firstName + " " + tenant.lastName}
                  </p>
                  <p className="inner-p">
                    <input
                      type="checkbox"
                      checked={tenant.active}
                      value={tenant.active}
                      onChange={(e) => {
                        toggleTenantStatus(e.currentTarget.checked, tenant);
                      }}
                    />
                  </p>
                </li>
              </>
            ))}
          </ul>
        </div>
      </div>

      {/* // 10 most recent landlords */}

      <Properties properties={properties} />
    </div>
  );
};

export default Admin;
