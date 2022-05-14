import faker from '@faker-js/faker';
import { Box, Divider, Grid, Paper, Typography } from '@mui/material';
import StatusView, { StatusVariants } from 'components/StatusView';
import Layout from 'pages/Layout';
import React, { useCallback, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function Tenant({ }) {
  const params = useParams();

  const [tenant, setTenant] = useState({
    id: 0,
    name: "",
    status: StatusVariants.pending,
  });

  const fetchData = useCallback(async () => {
    // TODO: fetch data
    const id = params.id;
    setTenant({
      id,
      name: faker.name.findName(),
      status: StatusVariants.active,
    })
  }, [params.id])

  useEffect(() => {
    fetchData()
  }, [fetchData]);

  return (
    <Layout title="Tenant">
      <Paper>
        <Grid container direction="row" mt={2} p={2} spacing={2}>
          <Grid item xs={4}>
            <Typography>
              Name: {tenant.name}
            </Typography>
            
          </Grid>
          <Grid item xs={4}>
            <StatusView title={tenant.status.toUpperCase()} variant={tenant.status} />
          </Grid>
          <Grid item xs={4}>
            <StatusView title={tenant.status.toUpperCase()} variant={tenant.status} />
          </Grid>
          <Divider />
          <Grid item xs={4}>
            <StatusView title={tenant.status.toUpperCase()} variant={tenant.status} />
          </Grid>
        </Grid>
      </Paper>
    </Layout>
  );
}

export default Tenant;