import faker from '@faker-js/faker';
import { Box, Divider, Grid, Paper, Typography } from '@mui/material';
import StatusView, { StatusVariants } from 'components/StatusView';
import Layout from 'pages/Layout';
import React, { useCallback, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useAxios } from '../../api/userAxios';
import { ToastContainer, toast } from 'react-toastify';

import Loading from '../../components/Loading';
import Header from '../../components/Header';


const Tenant = () => {
  const { id } = useParams();
  const notify = (msg) => toast.error(msg);

  const { data, error, loading, execute, queryParam } = useAxios(
    "get",
    "/users/" + id
  );

  if (loading) {
    return (
      <>
        <Header />
        <Loading loading={loading} />
      </>
    );
  }


  return (
    <Layout title="Tenant">
      <ToastContainer />
      <Paper>
        <Grid container direction="row" mt={2} p={2} spacing={2}>
          <Grid item xs={4}>
            <Typography>
              First Name: {data.data.firstName}
            </Typography>

          </Grid>
          {/* <Grid item xs={4}>
            <StatusView title={tenant.status.toUpperCase()} variant={tenant.status} />
          </Grid>
          <Grid item xs={4}>
            <StatusView title={tenant.status.toUpperCase()} variant={tenant.status} />
          </Grid>
          <Divider />
          <Grid item xs={4}>
            <StatusView title={tenant.status.toUpperCase()} variant={tenant.status} />
          </Grid> */}
        </Grid>
      </Paper>
    </Layout>
  );
}

export default Tenant;