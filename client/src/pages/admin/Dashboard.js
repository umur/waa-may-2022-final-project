import { faker } from "@faker-js/faker";
import * as React from "react";
import dayjs from "dayjs";
import { Grid } from "@mui/material";
import DataTable from "../../components/DataTable";
import Layout from "./Layout";

function generateRow(index) {
  return {
    id: `${index}`,
    name: faker.name.findName(),
    date: `${faker.date.past()}`,
    streetName: faker.address.streetName(),
    image: faker.image.image(),
  };
}

function generateData(page, perPage, rowCount, orderBy, orderDirection) {
  let noOfItems = perPage;
  let nextPage = page + 1;

  if (nextPage * perPage > rowCount) {
    noOfItems = rowCount - page * perPage;
  }

  const data = [];

  for (let index = 1; index <= noOfItems; index++) {
    const id = index + page * perPage;

    data.push(generateRow(id));
  }

  return data;
}

function Dashboard(props) {
  const columns = [
    { id: "id", label: "Id", minWidth: 170 },
    { id: "name", label: "Name", minWidth: 170 },
    {
      id: "date",
      label: "Date",
      minWidth: 170,
      align: "right",
      format: (value) => dayjs(value).format("MMM DD YYYY"),
    },
    {
      id: "streetName",
      label: "Address",
      minWidth: 170,
      align: "right",
      format: (value) => value,
    },
    {
      id: "image",
      label: "Photo",
      minWidth: 170,
      align: "right",
      renderCell: (params) => (
        <img width={170} src={params.value} alt="property" />
      ),
    },
  ];

  const [order, setOrder] = React.useState("desc");
  const [orderBy, setOrderBy] = React.useState("name");
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const [rows, setRows] = React.useState([]);
  const [rowCount, setRowCount] = React.useState(34);

  const fetchData = React.useCallback(() => {
    setRows(generateData(page, rowsPerPage, rowCount, orderBy, order));
  }, [order, orderBy, page, rowsPerPage, rowCount]);

  React.useEffect(() => {
    fetchData();
  }, [fetchData]);

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  // const handleSelectAllClick = (event) => {
  //   if (event.target.checked) {
  //     const newSelecteds = rows.map((n) => n.name);
  //     setSelected(newSelecteds);
  //     return;
  //   }
  //   setSelected([]);
  // };

  const handleClick = (event, name) => {
    const selectedIndex = selected.indexOf(name);
    let newSelected = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, name);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1)
      );
    }

    setSelected(newSelected);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  // const isSelected = (name) => selected.indexOf(name) !== -1;

  return (
    <Layout title="Dashboard">
      <Grid container spacing={0}>
        {/* Recent Properties */}
        <Grid item xs={12}>
          <DataTable
            title={"Recent Properties"}
            order={order}
            orderBy={orderBy}
            selected={selected}
            rows={rows}
            columns={columns}
            handleRequestSort={handleRequestSort}
            handleClick={handleClick}
            rowCount={rowCount}
            rowsPerPage={rowsPerPage}
            page={page}
            handleChangePage={handleChangePage}
            handleChangeRowsPerPage={handleChangeRowsPerPage}
          />
        </Grid>
      </Grid>
    </Layout>
  );
}

export default Dashboard;
