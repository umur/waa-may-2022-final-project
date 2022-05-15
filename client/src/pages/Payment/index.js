import { useAxios } from 'api/useAxios';
import React, { useState, useEffect } from "react";
import { useParams } from 'react-router-dom';
import "./index.css"

const Payment = () => {
  const { id: propertyId } = useParams();

  const {
    data: property,
  } = useAxios("get", `/property-rental-histories/${propertyId}`);

  const {
    data,
    error,
    loading,
    execute,
  } = useAxios("post", "/payment/create-checkout-session");

  const handleSubmit = (event) => {
    event.preventDefault()

    execute({
      propertyId: property.id,
      numberOfDays: 2,
    })
  }

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

// export default function App() {
//   const [message, setMessage] = useState("");

//   useEffect(() => {
//     // Check to see if this is a redirect back from Checkout
//     const query = new URLSearchParams(window.location.search);

//     if (query.get("success")) {
//       setMessage("Order placed! You will receive an email confirmation.");
//     }

//     if (query.get("canceled")) {
//       setMessage(
//         "Order canceled -- continue to shop around and checkout when you're ready."
//       );
//     }
//   }, []);

//   return message ? (
//     <Message message={message} />
//   ) : (
//     <ProductDisplay />
//   );
// }