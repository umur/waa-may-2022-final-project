import React, { useState, useEffect } from 'react'
import { useSelector } from 'react-redux'
import api from '../../api/posts'
import UsersTable from '../users/usersTable';
import PropertiesTable from '../properties/propertiesTable';
import { Row, Col } from 'antd';
import ReactECharts from 'echarts-for-react';
import {BarChart, } from 'echarts/charts';

import ReactEChartsCore from 'echarts-for-react/lib/core';
import * as echarts from 'echarts/core';
import {
  GridComponent,
  TooltipComponent,
  TitleComponent,
  DatasetComponent,
} from 'echarts/components';
import {
  CanvasRenderer,
} from 'echarts/renderers';
echarts.use(
  [TitleComponent, TooltipComponent, GridComponent, BarChart, CanvasRenderer]
);

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
  propertyType: {
  },
  user: {
    
  },
  rent: [],
};

const DashboardAdmin = () => {

  const user = useSelector((state) => state.user.value); //token, role, email, id

  const config = {
    headers: { Authorization: `Bearer ${user.token}` }
  };

  const [tenants, setTenants] = useState([{
    id:0,
    email: '',
    password: '',
    lastname: '',
    active:'',
    LastLoggedInAt:'',
    role:'',
    rents:{},
    property:{}
  }]);
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
  }, [])
  
  return (
    <>

      <Row>
        <Col span={11}>
          <h4>Last 10 Recent Tenant</h4>
          <UsersTable userList = {tenants}></UsersTable>
        </Col>
        <Col span={11} offset={2}>
          <h4>Last 10 Properties Rented</h4>
          <PropertiesTable propertyList = {properties}></PropertiesTable>
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
  )
}

function getOption() {
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
        data: [150, 230, 224, 218, 135, 147, 260],
        type: 'line'
      }
    ]
  };
}


function getOption2() {
  return {
  color: ['#80FFA5', '#00DDFF', '#37A2FF', '#FF0087', '#FFBF00'],
  title: {
    text: 'Gradient Stacked Area Chart'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'cross',
      label: {
        backgroundColor: '#6a7985'
      }
    }
  },
  legend: {
    data: ['Line 1', 'Line 2', 'Line 3', 'Line 4', 'Line 5']
  },
  toolbox: {
    feature: {
      saveAsImage: {}
    }
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: [
    {
      type: 'category',
      boundaryGap: false,
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    }
  ],
  yAxis: [
    {
      type: 'value'
    }
  ],
  series: [
    {
      name: 'Line 1',
      type: 'line',
      stack: 'Total',
      smooth: true,
      lineStyle: {
        width: 0
      },
      showSymbol: false,
      areaStyle: {
        opacity: 0.8,
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: 'rgb(128, 255, 165)'
          },
          {
            offset: 1,
            color: 'rgb(1, 191, 236)'
          }
        ])
      },
      emphasis: {
        focus: 'series'
      },
      data: [140, 232, 101, 264, 90, 340, 250]
    },
    {
      name: 'Line 2',
      type: 'line',
      stack: 'Total',
      smooth: true,
      lineStyle: {
        width: 0
      },
      showSymbol: false,
      areaStyle: {
        opacity: 0.8,
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: 'rgb(0, 221, 255)'
          },
          {
            offset: 1,
            color: 'rgb(77, 119, 255)'
          }
        ])
      },
      emphasis: {
        focus: 'series'
      },
      data: [120, 282, 111, 234, 220, 340, 310]
    },
    {
      name: 'Line 3',
      type: 'line',
      stack: 'Total',
      smooth: true,
      lineStyle: {
        width: 0
      },
      showSymbol: false,
      areaStyle: {
        opacity: 0.8,
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: 'rgb(55, 162, 255)'
          },
          {
            offset: 1,
            color: 'rgb(116, 21, 219)'
          }
        ])
      },
      emphasis: {
        focus: 'series'
      },
      data: [320, 132, 201, 334, 190, 130, 220]
    },
    {
      name: 'Line 4',
      type: 'line',
      stack: 'Total',
      smooth: true,
      lineStyle: {
        width: 0
      },
      showSymbol: false,
      areaStyle: {
        opacity: 0.8,
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: 'rgb(255, 0, 135)'
          },
          {
            offset: 1,
            color: 'rgb(135, 0, 157)'
          }
        ])
      },
      emphasis: {
        focus: 'series'
      },
      data: [220, 402, 231, 134, 190, 230, 120]
    },
    {
      name: 'Line 5',
      type: 'line',
      stack: 'Total',
      smooth: true,
      lineStyle: {
        width: 0
      },
      showSymbol: false,
      label: {
        show: true,
        position: 'top'
      },
      areaStyle: {
        opacity: 0.8,
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: 'rgb(255, 191, 0)'
          },
          {
            offset: 1,
            color: 'rgb(224, 62, 76)'
          }
        ])
      },
      emphasis: {
        focus: 'series'
      },
      data: [220, 302, 181, 234, 210, 290, 150]
    }
  ]}}
export default DashboardAdmin;



