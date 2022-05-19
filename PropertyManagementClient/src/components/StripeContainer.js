import { Elements } from "@stripe/react-stripe-js"
import { loadStripe } from "@stripe/stripe-js"
import React from "react"
import PaymentForm from "./PaymentForm"

const PUBLIC_KEY = "pk_test_51L0IFfLni6USJ4Eno3RofGbEBYWBPups7yaZ9K0zDEV36mBw2FALfjvMHkGQQgqZG2BYQCwH65Z7xS178LGFxTqx00KaLYLpD3"

const stripeTestPromise = loadStripe(PUBLIC_KEY)



const StripeContainer = (props)  => {


	return (
		<Elements stripe={stripeTestPromise} amount={props}>
			<PaymentForm amount={props.amount} date={props.date}></PaymentForm>
		</Elements>
	)
}

export default StripeContainer;