import faker from "@faker-js/faker";
import { Button, Grid, Input, InputAdornment, Paper } from "@mui/material";
import dayjs from "dayjs";
import React, { useMemo, useState } from "react";
import DataTable from "../../../components/DataTable";
import SearchForm from '../../../components/SearchForm';
import Layout from "../Layout";
import { useNavigate } from "react-router-dom";
import StatusView from '../../../components/StatusView';
import AddIcon from '@mui/icons-material/Add';
import { ColumnTypes, RowActions } from '../../../components/DataTable/RowActions';
import Action from '../../../components/DataTable/Action';

function generateRow(index) {
  return {
    id: `${index}`,
    name: faker.name.findName(),
    date: `${faker.date.past()}`,
    streetName: faker.address.streetName(),
    status: faker.datatype.boolean(),
    actions: [RowActions.activate, RowActions.deactivate],
  };
}

function generateData(page, perPage, rowCount, orderBy, orderDirection, keywords) {
  console.log(page, orderBy, orderDirection, keywords);

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

function Landlords(props) {
  const columns = useMemo(
    () => [
      { id: "id", label: "Id" },
      { id: "name", label: "Name" },
      {
        id: "date",
        label: "Date of birth",
        align: "left",
        format: (value) => dayjs(value).format("MMM DD YYYY"),
      },
      {
        id: "streetName",
        label: "Address",
        align: "left",
        format: (value) => value,
      },
      {
        id: "status",
        label: "Status",
        align: "left",
        renderCell: (data) => {
          const status = data.value ? "active" : "pending"
          return <StatusView title={status} variant={status} />
        }
      },
      {
        id: "actions",
        label: "Actions",
        type: ColumnTypes.actions,
        renderCell: (data) => {
          const actions = data.value;
          const row = data.row;
          
          const buttons = actions.map(a => {
            return <Action onClick={() => onAction(a, row)} text={a} color={a} key={`${a}_${row.id}`} />
          })

          return <Grid container spacing={1}>
            {buttons}
          </Grid>
        }
      }
    ],
    []
  );

  const [order, setOrder] = React.useState("desc");
  const [orderBy, setOrderBy] = React.useState("name");
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const [rows, setRows] = React.useState([]);
  const [rowCount, setRowCount] = React.useState(34);
  const [keywords, setKeywords] = useState("");

  const fetchData = React.useCallback(() => {
    setRows(generateData(page, rowsPerPage, rowCount, orderBy, order, keywords));
  }, [order, orderBy, page, rowsPerPage, rowCount, keywords]);

  React.useEffect(() => {
    fetchData();
  }, [fetchData]);

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleClick = (event, row) => {
    const name = row.name;
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

    navigate(`/admin/landlords/${row.id}`)
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  /* -------------------------------------------------------------------------- */
  /*                              Search landlord                               */
  /* -------------------------------------------------------------------------- */
  const searchLandlords = (keywords) => {
    setKeywords(keywords);
  }

  /* -------------------------------------------------------------------------- */
  /*                               New landlord                                 */
  /* -------------------------------------------------------------------------- */
  const navigate = useNavigate();

  const addLandlord = () => {
    navigate('/admin/landlords/new')
  }

  /* -------------------------------------------------------------------------- */
  /*                                   Actions                                  */
  /* -------------------------------------------------------------------------- */
  const onAction = (action, row) => {
    // TODO: handle action
    console.log(action, row)
  }

  return (
    <Layout title="Landlords">
      <Grid container spacing={1}>
        <Grid item xs={4}>
          <SearchForm onSubmit={searchLandlords} />
        </Grid>

        <Grid item xs={4}>
          <Button variant="contained" onClick={addLandlord}><AddIcon />  New Landlord</Button>
        </Grid>
        
        
        <Grid item xs={12}>
          <DataTable
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

export default Landlords;
