import { Box, Button, Grid } from "@mui/material";
import React, { useContext, useEffect, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
import AddIcon from "@mui/icons-material/Add";
import { ColumnTypes, RowActions } from "components/DataTable/RowActions";
import StatusView from "components/StatusView";
import Action from "components/DataTable/Action";
import Layout from "pages/Layout";
import SearchForm from "components/SearchForm";
import DataTable from "components/DataTable";
import { UserStatus } from "common/constant";
import useAxios from "axios-hooks";
import { defaultHeaders } from 'api/defaultHeaders';
import { AuthContext } from 'context/AuthContext';
import { Image } from '@mui/icons-material';

function Properties(props) {
  const columns = useMemo(
    () => [
      { id: "id", label: "ID" },
      { id: "propertyName", label: "Property Name" },
      { id: "city", label: "Address" },
      { id: "propertyType", label: "Type" },
      { id: "rentAmount", label: "Amount" },
      { id: "securityDepositAmount", label: "Secrurity Deposit" },
      { id: "isOccupied", label: "isOccupied" },
      { id: "lastRentedBy", label: "Rented By" },

      {
        id: "active",
        label: "Active",
        align: "left",
        renderCell: (data) => {
          const status = data.value ? UserStatus.active : UserStatus.deactivate;
          return <StatusView title={status} variant={status} />;
        },
      },
      {
        id: "actions",
        label: "Actions",
        type: ColumnTypes.actions,
        renderCell: (data) => {
          const actions = data.value;
          const row = data.row;

          const buttons = actions.map((a) => {
            return (
              <Action
                onClick={() => onAction(a, row)}
                text={a}
                color={a}
                key={`${a}_${row.id}`}
              />
            );
          });

          return (
            <Grid container spacing={1}>
              {buttons}
            </Grid>
          );
        },
      },
    ],
    []
  );

  const { isSignedIn } = useContext(AuthContext)

  const [order, setOrder] = React.useState("desc");
  const [orderBy, setOrderBy] = React.useState("createdAt");
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  // const [rows, setRows] = React.useState([]);
  // const [rowCount, setRowCount] = React.useState(34);
  const [keywords, setKeywords] = useState("");

  const [{ data, loading, error }, refetch] = useAxios(
    {
      url: "/landlord/properties",
      method: "get",
      params: {
        page,
        size: rowsPerPage,
        sort: orderBy ? orderBy + "," + order : undefined,
        search: keywords,
      },
      headers: defaultHeaders(isSignedIn)
    },
    {
      useCache: false,
    }
  );

  const rows = data?.data.map((i) => {
    return { ...i, actions: [RowActions.activate, RowActions.deactivate] };
  });

  const rowCount = data?.total;

  console.log(rows);

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleClick = (event, row) => {
    console.log("click ", row);

    // NOTE: Do not need to do edit property because it will cause the mismatch with the rental history
    // navigate(`/properties/detail/${row.id}`);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  /* -------------------------------------------------------------------------- */
  /*                            Search properties                               */
  /* -------------------------------------------------------------------------- */
  const search = (keywords) => {
    setKeywords(keywords);
  };

  /* -------------------------------------------------------------------------- */
  /*                               New property                                 */
  /* -------------------------------------------------------------------------- */
  const navigate = useNavigate();

  const newProperty = () => {
    // New property
    navigate("/properties/new");
  };

  /* -------------------------------------------------------------------------- */
  /*                                   Actions                                  */
  /* -------------------------------------------------------------------------- */
  const [
    { data: userUpdated, loading: putLoading, error: putError },
    executePut,
  ] = useAxios(
    {
      url: "/landlord/properties/{{user_id}}/activate",
      method: "PUT",
      headers: defaultHeaders(isSignedIn),
    },
    { manual: true }
  );

  useEffect(() => {
    refetch();
  }, [refetch, userUpdated]);

  const onAction = (action, row) => {
    executePut({
      url: `/landlord/properties/${row.id}/${action}`,
      method: "PUT",
      headers: defaultHeaders(isSignedIn),
    });
  };

  return (
    <Layout title="Properties">
      <Grid container spacing={1}>
        <Grid item xs={4}>
          <SearchForm onSubmit={search} />
        </Grid>

        <Grid item xs={4}>
          <Button variant="contained" onClick={newProperty}>
            <AddIcon /> New Property
          </Button>
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

export default Properties;
