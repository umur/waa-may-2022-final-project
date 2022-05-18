import { useState } from "react";
import axios from "axios";
import "./ForgetPassword.css"
import { useNavigate } from "react-router-dom";
import { useEffect } from 'react';
function ForgetPassword() {
    const navigate = useNavigate();
    const [userState, setUserState] = useState({
        id: 2,
        email: "test",
        firstName: "hassan",
        lastname: "hassan",
        role: {
            id: 2,
            description: "tenant"
        },
        active: false
    });
    const [messageState, setMessageState] = useState(
        {

            subject: "rset password",
            messageBody: "http://localhost:3002/reset-password",
            receiver: {
                id: 2,
                email: "test",
                firstName: "hassan",
                lastname: "hassan",
                role: {
                    id: 2,
                    description: "tenant"
                },
                active: false
            },
            sendDate: "2022-05-17",
            status: true
        },

    )

    const fetchProperties = async (url) => {
        const res = await fetch(url);
        const data = await res.json();
    
        return data;
      };
    const fetchData = async () => {

      
      //  try {
        const url2 = "http://localhost:8080/api/users/byEmail/" + emailState;
      //  const result=    await fetchProperties(url2);
        const result = await axios.get(url2);
        setUserState(result.data);
        console.log("2222" + userState.email);
        // setUserState(result.data);
        // setUserState(result.data);
       // console.log("0000", result);
        //  console.log("111" + userState);
      // 
       // console.log("11111" + result.data);
        const copy = { ...messageState };
        //    console.log("userStatrrrre"+url2);
        copy.receiver = userState;
        // console.log("userStateeee" + userState.email);

    //    setMessageState(copy);
    //   //  console.log("resultssss" + messageState.receiver.email);
    //     } catch (error) {
    //         alert("sorry try again later");
    //     }

    }

 
    const sendEmail = async () => {
        await fetchData().then(() => {
            console.log("messageState" + messageState.receiver.email);
        })
        // console.log("email" + emailState);
        // console.log("messageState" + emailState);
        // console.log("userState" + userState.email);
        messageState.receiver = { ...userState };
        // messageState.receiver = { ...userState };
        // messageState.receiver = { ...userState };
       
        if (userState.email == 'test')
            alert("Sorry you don't have ann account please gor for signup");
        else {
            if (emailState == "")
                alert("Please enter your email");
            else {

                const url = 'http://localhost:8080/api/users/sendEmail';

                const response = await axios.post(url, messageState);
                if (response.status == 200) {
                    alert("mail succefully sent please go to your mail ");
                    navigate('/email-sent');
                }

                else {
                    alert("sorry try again later");
                }

            }
        }
    };

    const cancelButton = async () => {
        navigate('/sign-in');


    };


    const [emailState, setEmailState] = useState('');
    return (

        <div className="main-container">
            <div className="center">
                <h2>Forget Password</h2>
            </div>
            <form className="add-form" >
                <div className="form-control">
                    <label>Email</label>
                    <input
                        type="text"
                        placeholder="email"
                        value={emailState}
                        onChange={(e) => setEmailState(e.target.value)}
                    />
                </div>

                <div className="center">
                    <input type="button" value="Send Email" className="btn btn-block button-padding" onClick={sendEmail} />
                    <input type="button" value="Cancel" className="btn btn-block button-padding" onClick={cancelButton} />


                </div>
            </form>

        </div>
    );
};

export default ForgetPassword;
