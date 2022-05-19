import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import api from "../../api/posts";
import UsersTable from "../users/usersTable";
import PropertiesTable from "../properties/propertiesTable";
import PropertyReduce from "../properties/propertiesReduce";
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

const DashboardLandlord = () => {
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

  async function getLastTenRentedProperty() {
    let res = await api.get(`/api/v1/properties/last-ten-rented`, config);
    setProperties(res.data);
  }

  //const [, forceUpdate] = useState({});
  useEffect(() => {
    //   getLastTenTenants();
    getLastTenRentedProperty();
    //   forceUpdate({});
  }, []);
  return (
    <Row>
      <Col span={11}>
        <h4>Total income per location</h4>
        <PropertyReduce propertyList={properties}></PropertyReduce>
      </Col>
      <Col span={11} offset={2}>
        <h4>Last 10 Properties Rented</h4>
        <PropertiesTable propertyList={properties}></PropertiesTable>
      </Col>
    </Row>
  );
};

export default DashboardLandlord;
