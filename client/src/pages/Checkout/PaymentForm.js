import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import { Button, Grid } from "@mui/material";
import { CardElement, useElements, useStripe } from "@stripe/react-stripe-js";
import StatusMessages, { useMessages } from "./StatusMessages";
import { useAxios } from "api/useAxios";
import "./CardSectionStyles.css";

const CARD_ELEMENT_OPTIONS = {
  style: {
    base: {
      color: "#32325d",
      fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
      fontSmoothing: "antialiased",
      fontSize: "16px",
      "::placeholder": {
        color: "#aab7c4",
      },
    },
    invalid: {
      color: "#fa755a",
      iconColor: "#fa755a",
    },
  },
};

function PaymentForm({ order }) {
  const { amount, name, email } = order;

  const stripe = useStripe();
  const elements = useElements();

  const [messages, addMessage] = useMessages();
  const [isLoading, setLoading] = useState(false);

  const {
    data,
    error: backendError,
    loading,
    execute,
  } = useAxios("post", "/payment/create-payment-intent");

  if (backendError) {
    addMessage(backendError.message);
    return;
  }

  const handleSubmit = async (e) => {
    // We don't want to let default form submission happen here,
    // which would refresh the page.
    e.preventDefault();

    if (!stripe || !elements) {
      // Stripe.js has not yet loaded.
      // Make sure to disable form submission until Stripe.js has loaded.
      addMessage("Stripe.js has not yet loaded.");
      return;
    }

    await execute({
      paymentMethodType: "card",
      currency: "usd",
      amount: amount,
      email: email,
    });
  };

  const confirmPayment = async (data) => {
    addMessage("Client secret returned " + data);

    const clientSecret = data.data.clientSecret;

    const { error: stripeError, paymentIntent } =
      await stripe.confirmCardPayment(clientSecret, {
        payment_method: {
          card: elements.getElement(CardElement),
          billing_details: {
            name: name,
            email: email,
          },
        },
      });

    if (stripeError) {
      // Show error to your customer (e.g., insufficient funds)
      addMessage(stripeError.message);
      return;
    }

    if (paymentIntent.status === "succeeded") {
      // Show a success message to your customer
      // There's a risk of the customer closing the window before callback
      // execution. Set up a webhook or plugin to listen for the
      // payment_intent.succeeded event that handles any business critical
      // post-payment actions.
    }
    addMessage(`Payment ${paymentIntent.status}: ${paymentIntent.id}`);
  };

  // eslint-disable-next-line react-hooks/rules-of-hooks
  useEffect(() => {
    if (data) {
      confirmPayment(data);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [data]);

  return (
    <Container component="main" maxWidth="lg" sx={{ mb: 4 }}>
      <Paper variant="outlined" sx={{ my: { xs: 3, md: 6 }, p: 4 }}>
        <Grid item xs={6}>
          <Typography component="h1" variant="h4" align="center">
            Confirm and pay
          </Typography>

          <form id="payment-form" onSubmit={handleSubmit}>
            <CardElement options={CARD_ELEMENT_OPTIONS} id="card" />

            <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
              <Button variant="contained" sx={{ mt: 3, ml: 1 }} type="submit">
                Pay
              </Button>
            </Box>
          </form>
          <StatusMessages messages={messages} />
        </Grid>
      </Paper>
    </Container>
  );
}

export default PaymentForm;
