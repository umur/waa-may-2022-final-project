import * as React from "react";
import dayjs from "dayjs";
import { Box, Grid } from "@mui/material";
import Layout from "./Layout";
import PieChartComponent from "components/PieChartComponent";
import LineChartComponent from "components/LineChartComponent"
import DisplayTopTenData from "components/DisplayTopTenData";

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

  return (
    <Layout title="Dashboard">
      <Grid container spacing={1}>
        <DisplayTopTenData title="Newly added tenants" url="/admin/tenants" columns={tenantColumn} />

        <DisplayTopTenData title="Recently rented properties" url="/admin/tenants" columns={propertyColumn} />

        <Grid item xs={6} rowSpacing={1}>

          <PieChartComponent />

          <Box margin={1} />
          <LineChartComponent />
        </Grid>
      </Grid>
    </Layout>
  );
}

export default Dashboard;
