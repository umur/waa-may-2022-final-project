import { faker } from "@faker-js/faker";
import * as React from "react";
import dayjs from "dayjs";
import { Box, Grid, Paper } from "@mui/material";
import DataTable from "../components/DataTable";
import Layout from "./Layout";
import ReactECharts from 'echarts-for-react';

// Import the echarts core module, which provides the necessary interfaces for using echarts.
import * as echarts from "echarts/core";
// Import charts, all with Chart suffix
import { BarChart, PieChart, } from "echarts/charts";
// import components, all suffixed with Component
import { GridComponent, CalendarComponent, ToolboxComponent, TooltipComponent, TitleComponent, TimelineComponent, LegendComponent, DataZoomComponent, VisualMapComponent } from "echarts/components";
// Import renderer, note that introducing the CanvasRenderer or SVGRenderer is a required step
import { CanvasRenderer } from "echarts/renderers";
import DisplayTopTenData from "components/DisplayTopTenData";

// Register the required components
echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  CanvasRenderer,
  PieChart,
  VisualMapComponent,
  LegendComponent,
  ToolboxComponent,
  DataZoomComponent,
  TimelineComponent,
  CalendarComponent,
  BarChart,
]);

const option = {
  title: {
    text: "Total income",
    subtext: "",
    x: "center",
  },
  tooltip: {
    trigger: "item",
    formatter: "{a} <br/>{b} : {c} ({d}%)",
  },
  legend: {
    orient: "vertical",
    left: "left",
    data: ["Property A", "Property B", "Property C", "Property D", "Property E"],
  },
  series: [
    {
      name: "Income",
      type: "pie",
      radius: "55%",
      center: ["50%", "60%"],
      data: [
        { value: 335, name: "Property A", id: 1 },
        { value: 310, name: "Property B", id: 2 },
        { value: 234, name: "Property C", id: 3 },
        { value: 135, name: "Property D", id: 4 },
        { value: 1548, name: "Property E", id: 5 },
      ],
      itemStyle: {
        emphasis: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: "rgba(0, 0, 0, 0.5)",
        },
      },
    },
  ],
};

const lineChartOption = {
  title: {
    text: 'Number of properties rented'
  },
  toolbox: {
    feature: {
      saveAsImage: {},
      dataZoom: {},
      restore: {}
    }
  },
  tooltip: {},
  legend: {
    data: ['Properties']
  },
  xAxis: {
    data: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7']
  },
  yAxis: {},
  series: [{
    name: 'Properties',
    type: 'line',
    data: [5, 20, 36, 10, 10, 20, 0]
  }]
};


function Dashboard() {

  const tenantColumn = [
    { id: "id", label: "Id", minWidth: 10 },
    { id: "firstName", label: "First Name", minWidth: 170 },
    { id: "lastName", label: "Last Name", minWidth: 170 },
    {
      id: "createdAt",
      label: "Date",
      minWidth: 170,
      align: "right",
      format: (value) => dayjs(value).format("MMM DD YYYY"),
    },
    {
      id: "gender",
      label: "Gender",
      minWidth: 170,
      align: "right",
      format: (value) => value,
    },
  ];

  const propertyColumn = [
    { id: "id", label: "Id", minWidth: 10 },
    { id: "firstName", label: "First Name", minWidth: 170 },
    { id: "lastName", label: "Last Name", minWidth: 170 },
    {
      id: "created_at",
      label: "Date",
      minWidth: 170,
      align: "right",
      format: (value) => dayjs(value).format("MMM DD YYYY"),
    },
    {
      id: "gender",
      label: "Gender",
      minWidth: 170,
      align: "right",
      format: (value) => value,
    },
  ];

  /* -------------------------------------------------------------------------- */
  /*                                    Chart                                   */
  /* -------------------------------------------------------------------------- */
  const getOption = () => {
    return option;
  };

  function onChartReady(echarts) {
    console.log('echarts is ready', echarts);
  }

  function onChartClick(param, echarts) {
    console.log(param, echarts);
  };

  function onChartLegendselectchanged(param, echarts) {
    console.log(param, echarts);
  };

  /* -------------------------------------------------------------------------- */
  /*                                  End Chart                                 */
  /* -------------------------------------------------------------------------- */

  return (
    <Layout title="Dashboard">
      <Grid container spacing={1}>
        <DisplayTopTenData title="Newly added tenants" url="/admin/tenants" columns={tenantColumn} />

        <DisplayTopTenData title="Recently rented properties" url="/admin/tenants" columns={propertyColumn} />

        <Grid item xs={12} rowSpacing={1}>
          <Paper>
            <ReactECharts
              option={getOption()}
              style={{ height: 400 }}
              onChartReady={onChartReady}
              onEvents={{
                click: onChartClick,
                legendselectchanged: onChartLegendselectchanged,
              }}
            />
          </Paper>
          <Box margin={1} />
          <Paper>
            <ReactECharts
              option={lineChartOption}
              style={{ height: 400 }}
            />
          </Paper>
        </Grid>
      </Grid>
    </Layout>
  );
}

export default Dashboard;
