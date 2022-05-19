import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import api from "../../api/posts";
import UsersTable from "../users/usersTable";
import PropertiesTable from "../properties/propertiesTable";
import { Row, Col } from "antd";
import ReactECharts from "echarts-for-react";
import { BarChart } from "echarts/charts";

import ReactEChartsCore from "echarts-for-react/lib/core";
import * as echarts from "echarts/core";
import {
  GridComponent,
  TooltipComponent,
  TitleComponent,
  DatasetComponent,
} from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";
echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  BarChart,
  CanvasRenderer,
]);

let propertiesObject = {
  id: 0,
  name: "",
  street: "",
  city: "",
  state: "",
  zip: "",
  numberOfBedrooms: 0,
  numberOfBathrooms: 0,
  rentAmount: 0,
  securityDepositAmount: 0,
  occupied: false,
  listed: true,
  photos: [],
  propertyType: {},
  user: {},
  rent: [],
};

const DashboardAdmin = () => {
  const user = useSelector((state) => state.user.value); //token, role, email, id

  const config = {
    headers: { Authorization: `Bearer ${user.token}` },
  };

  const [tenants, setTenants] = useState([
    {
      id: 0,
      email: "",
      password: "",
      lastname: "",
      active: "",
      LastLoggedInAt: "",
      role: "",
      rents: {},
      property: {},
    },
  ]);
  const [properties, setProperties] = useState([propertiesObject]);

  async function getLastTenTenants() {
    let res = await api.get(`api/v1/users/get-by-role?role=ADMIN`, config);
    setTenants(res.data);
  }

  async function getLastTenRentedProperty() {
    let res = await api.get(`/api/v1/properties/last-ten-rented`, config);
    setProperties(res.data);
  }

  const [, forceUpdate] = useState({});
  useEffect(() => {
    getLastTenTenants();
    getLastTenRentedProperty();
    forceUpdate({});
  }, []);

  return (
    <>
      <Row>
        <Col span={11}>
          <h4>Last 10 Recent Tenant</h4>
          <UsersTable userList={tenants}></UsersTable>
        </Col>
        <Col span={11} offset={2}>
          <h4>Last 10 Properties Rented</h4>
          <PropertiesTable propertyList={properties}></PropertiesTable>
        </Col>
      </Row>

      <Row>
        <Col span={11}>
          <h4>Last 10 Recent Tenant</h4>
          <ReactECharts
            option={getOption()}
            notMerge={true}
            lazyUpdate={true}
            theme={"theme_name"}
            // onChartReady={this.onChartReadyCallback}
            // onEvents={EventsDict}
            // opts={}
          />
        </Col>
        <Col span={11} offset={2}>
          <h4>Last 10 Properties Rented</h4>
          <ReactECharts
            option={getOption()}
            notMerge={true}
            lazyUpdate={true}
            theme={"theme_name"}
            // onChartReady={this.onChartReadyCallback}
            // onEvents={EventsDict}
            // opts={}
          />
        </Col>
      </Row>

      <ReactEChartsCore
        echarts={echarts}
        option={getOption2()}
        notMerge={true}
        lazyUpdate={true}
        theme={"theme_name"}
        // onChartReady={this.onChartReadyCallback}
        // onEvents={EventsDict}
      />
    </>
  );
};

function getOption() {
  return {
    xAxis: {
      type: "category",
      data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        data: [150, 230, 224, 218, 135, 147, 260],
        type: "line",
      },
    ],
  };
}

function getOption2() {
  return {
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [120, 200, 150, 80, 70, 110, 130],
        type: 'bar',
        showBackground: true,
        backgroundStyle: {
          color: 'rgba(180, 180, 180, 0.2)'
        }
      }
    ]
  };}
export default DashboardAdmin;
