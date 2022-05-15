import { Grid, useMediaQuery } from '@mui/material';
import { useAxios } from 'api/useAxios';
import AlertDialog from 'components/AlertDialog';
import Header from 'components/Header';
import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from 'react-router-dom';
import useQuery from 'useQuery';
import "./index.css"
import Item from './Item';

const Payment = () => {
  const { id: propertyRentalHistoryId } = useParams();

  const navigate = useNavigate();
  const query = useQuery();
  const [openDialog, setOpenDialog] = useState(false);

  useEffect(() => {
    if (query.get("success") === 'true') {
      setOpenDialog(true)
    }
  }, [query]);

  const {
    data: propertyRentalHistory,
    // execute: getRentalHistory,
  } = useAxios("get", `/property-rental-histories/${propertyRentalHistoryId}`);

  const {
    data,
    error,
    loading,
    execute,
  } = useAxios("post", "/payment/create-checkout-session");

  const handleSubmit = (event) => {
    event.preventDefault()

    execute({
      propertyId: propertyRentalHistory.property.id,
      numberOfDays: 2,
      propertyRentalHistoryId: propertyRentalHistory.id,
    })
  }

  /* -------------------------------------------------------------------------- */
  /*                          Open stripe checkout link                         */
  /* -------------------------------------------------------------------------- */
  useEffect(() => {
    if (data) {
      window.location.replace(data?.url)
    }
  }, [data]);

  return (
    <Grid container spacing={2}>
      <Header />
      <Grid item xs={6}>
        <Item propertyRentalHistory={propertyRentalHistory} />
        <section>
          <form onSubmit={handleSubmit} method="POST">
            <button type="submit">
              Checkout
            </button>
          </form>
        </section>
      </Grid>

      <AlertDialog open={openDialog} handleClose={() => {
        setOpenDialog(false)
        navigate("/")
      }} 
        title={"Your order success"}
        content={""}
      />
    </Grid>
  );
};

export default Payment;
