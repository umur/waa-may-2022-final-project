import { useState, useEffect } from "react";
import Properties from "../Shared/Properties/Properties";
import Service from "../Shared/Service";
import "./Admin.css";

const Admin = () => {
  const urls = Service;
  const urlTenants = "http://localhost:5000/tenants";
  const url2Landlords = "http://localhost:5000/landlords";
  const url3 = "http://localhost:5000/property";
  const url4 = "http://localhost:5000/property"; // toggle Active status

  const url6 = "http://172.19.141.27:8080/api/users/admin/byRole/landlord";

  // var requestOptions = {
  //   method: 'GET',
  //   redirect: 'follow',
  //   mode: 'Access-Control-Allow-Origin'
  // };

  // fetch("172.19.141.27:8080/api/properties/admin/totalIncome/Fairfield", requestOptions)
  //   .then(response => response.text())
  //   .then(result => console.log(result))
  //   .catch(error => console.log('error', error));

  const [landlords, setLandlords] = useState([]);
  const [tenants, setTenants] = useState([]);
  const [properties, setProperties] = useState([]);
  const [totalIncome, setTotalIncome] = useState(0);
  var requestGetOptions = {
    method: "GET",
    redirect: "follow",
  };

  var requestPutOptions = {
    method: 'PUT',
    redirect: 'follow'
  };
  useEffect(() => {
    console.log(urls);
    // getProperties();
    getLandlords();
    getTenants();
    getTotalIncome();

    // fetch("172.19.141.27:8080/api/properties/admin/totalIncome/Fairfield", requestOptions)
    //   .then(response => response.text())
    //   .then(result => console.log(result))
    //   .catch(error => console.log('error', error));
  }, []);

  const getProperties = async () => {
    const propertyList = await fetchData(url3);
    setProperties(propertyList);
  };

  const getLandlords = async () => {
    const landlordList = await fetchData(Service.MostRecentLandlordsUrl);
    setLandlords(landlordList);
  };

  const getTenants = async () => {
    const tenantList = await fetchData(Service.MostRecentTenantsUrl);
    console.log("List : " + tenantList);
    setTenants(tenantList);
  };

  const getTotalIncome = async () => {
    const totalIncome = await fetchData(
      Service.TotalIncomePerLocation,
      requestGetOptions
    );
    if (totalIncome) {
      setTotalIncome(totalIncome.income);
    } else {
      setTotalIncome(0);
    }
  };

  const fetchData = async (url) => {
    console.log("URL Here : ", url);
    const res = await fetch(url);
    const data = await res.json();

    return data;
  };

  const toggleLandlordStatus = (status, landlord) => {
    setLandlords(
      landlords.map((l) => {
        if (landlord.id == l.id) {
          l.active = status;
        }
        return l;
      })
    );
    console.log("Status : ", status);
    const statusUrl = `http://172.19.141.27:8080/api/users/admin/activation/${landlord.email}/${status}`;
    // const toggle = await fetchProperties(url4);
    updateStatus(statusUrl);
  };

  const updateStatus = async (url) =>  {
    
    
    fetch(url, requestPutOptions)
      .then(response => response.text())
      .then(result => console.log(result))
      .catch(error => console.log('error', error));
  }

  const toggleTenantStatus = async (status, tenant) => {
    setTenants(
      tenants.map((l) => {
        if (tenant.id == l.id) {
          l.active = status;
        }
        return l;
      })
    );

    
    const statusUrl = `http://172.19.141.27:8080/api/users/admin/activation/${tenant.email}/${status}`;
    updateStatus(statusUrl);
  };

  return (
    <div className="admin ">
      <div className="spacing"></div>
      {/* //Total Income : $123456 */}
      <div
        className="total-income"
        onClick={(e) => {
          getTotalIncome();
        }}
      >
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
                <li className="list-group-item" key={landlord.id}>
                  <p className="inner-p">
                    {landlord.firstName + " " + landlord.lastname}
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
                <li className="list-group-item" key={tenant.id}>
                  <p className="inner-p">
                    {tenant.firstName + " " + tenant.lastname}
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
