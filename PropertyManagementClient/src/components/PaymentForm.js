import { CardElement, useElements, useStripe } from "@stripe/react-stripe-js"
import axios from "axios"
import React, { useState } from 'react'
import { useParams } from "react-router"
import { getBearer } from "../util/Utility"



const CARD_OPTIONS = {
    iconStyle: "solid",
    style: {
        base: {
            iconColor: "#c4f0ff",
            color: "#000",
            border: "1px solid #000",
            borderRadius: "8px",
            fontWeight: 500,
            fontFamily: "Roboto, Open Sans, Segoe UI, sans-serif",
            fontSize: "16px",
            fontSmoothing: "antialiased",
            ":-webkit-autofill": { color: "#fce883" },
            "::placeholder": { color: "#87bbfd" }
        },
        invalid: {
            iconColor: "#ffc7ee",
            color: "#ffc7ee"
        }
    }
}

const PaymentForm = (props) => {
    const bearer = getBearer();
    const [success, setSuccess] = useState(false)
    const stripe = useStripe()
    const elements = useElements()

    const params = useParams();
    const uid = localStorage.getItem('uid');

    console.log("PROPS: " , props);

    const rentProperty = async () => {
        let result = await axios.post('http://localhost:8080/api/v1/properties/rent/', {
        id: uid,
        pid: params.id,
        endDate: props.date
        }, {headers: {Authorization: bearer}})
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        const {paymentMethod} = await stripe.createPaymentMethod({
            type: "card",
            card: elements.getElement(CardElement)
        })

        console.log(paymentMethod);

        console.log(elements.getElement(CardElement));
        const token = await stripe.createToken(elements.getElement(CardElement))

        const amount = props.amount;
        console.log(amount.amount);
        console.log(token.token.id);
        try {
            const response = await axios.post("http://localhost:8080/payment", {
                token: token.token.id,
                amount: props.amount

            }, {headers: {Authorization: bearer}}
            )

            rentProperty();

            console.log(response);
            console.log(response.status);

            if (response.status == "succeeded") {
                console.log("Successful payment")
                setSuccess(true)
            }

        } catch (error) {
            console.log("Error", error)
        }

        /*    
        if(paymentMethod) {
            
        } else {
            console.log("HATA")
        }*/
    }

    return (
        <div>
            {!success ?
                <form onSubmit={handleSubmit}>
                    <fieldset className="FormGroup">
                        <div className="FormRow">
                            <CardElement options={CARD_OPTIONS} />
                        </div>
                    </fieldset>
                    <button>Pay</button>
                </form>
                :
                <div>
                    <h2>Payment Success</h2>
                </div>
            }

        </div>
    )
}

export default PaymentForm;