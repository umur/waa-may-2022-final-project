import { useAxios } from 'api/useAxios';
import React, { useState, useEffect } from "react";
import { useParams } from 'react-router-dom';
import "./index.css"

const Payment = () => {
  const { id: propertyRentalHistoryId } = useParams();

  const {
    data: propertyRentalHistory,
    execute: getRentalHistory,
  } = useAxios("get", `/property-rental-histories/${propertyRentalHistoryId}`);

  // useEffect(() => {
  //   getRentalHistory()
  // }, [getRentalHistory, propertyRentalHistoryId]);

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
    <section>
      <div className="product">
        <img
          src="https://i.imgur.com/EHyR2nP.png"
          alt="The cover of Stubborn Attachments"
        />
        <div className="description">
        <h3>Stubborn Attachments</h3>
        <h5>$20.00</h5>
        </div>
      </div>
      <form onSubmit={handleSubmit} method="POST">
        <button type="submit">
          Checkout
        </button>
      </form>
    </section>
  );
};

export default Payment;

const Message = ({ message }) => (
  <section>
    <p>{message}</p>
  </section>
);
