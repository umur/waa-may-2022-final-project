import React, { useEffect, useState } from 'react'
import ReactEcharts from "echarts-for-react";
import BreadCrumb from '../components/BreadCrumb';
import axios from 'axios';

const Report = () => {

    const [state, setState] = useState([]);
    const [incomePerState, setIncomePerLocation] = useState([]);
    const [selectedState, setSelectedState] = useState("");
    const [incomePerStreet, setIncomePerStreet] = useState([]);
    const [selectedCity, setSelectedCity] = useState("");

    useEffect(() => {
      getReport();
    }, []);
  
    let token = JSON.parse(localStorage.getItem("token"));


    const getReport = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/reports/location-base",
          {
            headers: {
              Authorization: `Bearer ${token.accessToken}`,
            },
          }
        );
        setState([...response.data]);
        // console.log(state);
      } catch (error) {
        console.log(error);
      }
    };

    const handleClick = async (e) => {
        // console.log(e.name);
        setSelectedState(e.name);
        try {
            const response = await axios.get(
              `http://localhost:8080/api/v1/reports/location-base?state=${e.name}`,
              {
                headers: {
                  Authorization: `Bearer ${token.accessToken}`,
                },
              }
            );
            setIncomePerLocation([...response.data]);
            setIncomePerStreet([])
          } catch (error) {
            console.log(error);
          }

    }

    const handleCityClick = async (e) => {
        console.log(e.name);
        setSelectedCity(e.name);
        try {
            const response = await axios.get(
              `http://localhost:8080/api/v1/reports/location-base?state=${selectedState}&city=${e.name}`,
              {
                headers: {
                  Authorization: `Bearer ${token.accessToken}`,
                },
              }
            );
            setIncomePerStreet([...response.data]);
          } catch (error) {
            console.log(error);
          }

    }



    const sameValueForChart = {
        backgroundColor: "rgb(43, 51, 59)",
        toolbox: {
            show: true,
            feature: {
                mark: {
                    show: true
                },
                magicType: {
                    show: true,
                    type: ["pie", "funnel"]
                },

            }
        },

        tooltip: {
            trigger: "item",
            formatter: "{a}<br/><strong>{b}</strong>: {c}"
        },
        calculable: true,
        legend: {
            icon: "circle",
            x: "center",
            y: "50px",
            data: "check",
            textStyle: {
                color: "#fff"
            }
        },
    }


    const seriesSameValue = {
        name: 'Total Income',
        type: 'pie',
        avoidLabelOverlap: false,
        label: {
            show: false,
            position: 'center'
        },
        emphasis: {
            label: {
                show: true,
                fontSize: '30',
                fontWeight: 'bold'
            }
        },
        labelLine: {
            show: false
        },
    }

    const option = {
       ...sameValueForChart,
        series: [
            {
                ...seriesSameValue,
                data: state
            }
        ]
    };

    const cityOption = {
        ...sameValueForChart,
        series: [
            {
               ...seriesSameValue,
                data: incomePerState
            }
        ]

    }

    const streetOption = {
        ...sameValueForChart,
        series: [
            {
               ...seriesSameValue,
                data: incomePerStreet
            }
        ]

    }

    const onEvents = {
        'click': handleClick,
      }
    
      const onEventsCity = {
          'click' : handleCityClick,
      }

    return (
        <div className="content-wrapper">
            <BreadCrumb name="Report" />
            <section className="content">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <div className="card-header">
                                    <h3 className="card-title">Total Income</h3>
                                </div>
                                <div className="card-body">
                                    <ReactEcharts  option={option} onEvents={onEvents}/>
                                </div>

                                {
                                    incomePerState.length ? 
                                        <div className="card-body">
                                            <p className='text-primary'>Total Income of {selectedState}'s Cities</p>
                                            <ReactEcharts  option={cityOption} onEvents={onEventsCity}/>
                                        </div>
                                    
                                    : ""
                                }

                                {
                                    incomePerState.length && incomePerStreet.length ? 
                                        <div className="card-body">
                                            <p className='text-primary'>Total Income of {selectedCity}'s Streets</p>
                                            <ReactEcharts  option={streetOption}/>
                                        </div>
                                    
                                    : ""
                                }

                            </div>
                        </div>
                    </div>
                </div>
            </section >
        </div >
    );
}

export default Report