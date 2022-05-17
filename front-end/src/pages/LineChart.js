import axios from "axios";
import EChartsReact from "echarts-for-react";
import { useEffect, useState } from "react";
import BreadCrumb from "../components/BreadCrumb";

const LineChart = () => {
  const [dates, setDates] = useState([]);

  const [count, setCounts] = useState([]);

  const getReport = async () => {
    let token = JSON.parse(localStorage.getItem("token"));
    try {
      const response = await axios.get(
        "http://localhost:8080/api/v1/properties/filter-weekly-rented",
        {
          headers: {
            Authorization: `Bearer ${token.accessToken}`,
          },
        }
      );

      var days = response.data.map((d) => d.day);
      var values = response.data.map((d) => d.value);
      setDates(days);
      setCounts(values);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getReport();
  }, []);

  return (
    <div className="content-wrapper">
      <BreadCrumb name="Line Report" />
      <section className="content">
        <div className="container-fluid">
          <div className="row">
            <div className="col-12">
              <div className="card">
                <div className="card-header">
                  <h3 className="card-title">Weekly Rent</h3>
                </div>
                <div className="card-body">
                  <EChartsReact
                    option={{
                      xAxis: {
                        type: "category",
                        data: [...dates],
                      },
                      yAxis: {
                        type: "value",
                      },
                      series: [
                        {
                          data: [...count],
                          type: "line",
                        },
                      ],
                    }}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default LineChart;
