import faker from "@faker-js/faker";
import {
  Box,
  Divider,
  Grid,
  Paper,
  Typography,
  Modal,
  Button,
  TextField,
} from "@mui/material";
import StatusView, { StatusVariants } from "components/StatusView";
import Layout from "pages/Layout";
import React, { useCallback, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useAxios } from "../../api/userAxios";
import { ToastContainer, toast } from "react-toastify";
import EditIcon from "@mui/icons-material/Edit";
import Loading from "../../components/Loading";
import Header from "../../components/Header";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
};

const Tenant = () => {
  const { id } = useParams();
  const [open, setOpen] = useState(false);
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

  const handleSubmit = (event) => {
    event.preventDefault();
    const form = new FormData(event.currentTarget);
    console.log({
      data: { email: form.get("email"), password: form.get("password") },
    });
  };

  return (
    <Layout title="Tenant">
      <ToastContainer />
      <Paper>
        <div style={{ padding: "20px 20px 0px 20px" }}>
          <Typography component="h1" variant="h5">
            Tenant Infromation
          </Typography>
        </div>
        <Grid
          container
          direction="row"
          spacing={2}
          style={{
            margin: "0px 20px 20px 20px",
            padding: "20px",
          }}
        >
          <Grid item xs={4}>
            <Typography>First Name: {data.data.firstName}</Typography>
          </Grid>
          <Grid item xs={4}>
            <Typography>First Name: {data.data.firstName}</Typography>
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
        <div className="edit-btn">
          <Button onClick={() => setOpen(!open)} variant="contained">
            <EditIcon /> Edit
          </Button>
        </div>
      </Paper>
      <Modal
        open={open}
        onClose={() => setOpen(false)}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style} component="form" onSubmit={handleSubmit} noValidate>
          <Typography component="h1" variant="h5">
            Update
          </Typography>
          <Box sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              autoFocus
              size="small"
            />
            <div className="btn-list">
              <Button type="submit" sx={{ mt: 3, mb: 2 }}>
                Update
              </Button>
              <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>
                Update
              </Button>
            </div>
          </Box>
        </Box>
      </Modal>
    </Layout>
  );
};

export default Tenant;
