import React from "react";
import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { useAxios } from "api/useAxios";
import PaymentForm from './PaymentForm';
import Header from 'components/Header';
import Loading from 'components/Loading';
import { useParams } from 'react-router-dom';

const Checkout = ({ order }) => {

  const { data, error, loading } = useAxios("get", "/payment/config");
  
  const { id } = useParams();
  const { data: propertyInfo } = useAxios("get", "/properties/" + id);

  if (loading) {
    return (
      <>
        <Header />
        <Loading />
      </>
    );
  }

  const stripePromise = loadStripe(data.data.publishableKey);

  

  return (
    <Elements stripe={stripePromise}>
      <Header />
      <PaymentForm order={order} />
    </Elements>
  );
};

export default Checkout;
