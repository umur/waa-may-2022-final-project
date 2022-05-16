import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import reportWebVitals from "./reportWebVitals";
import axios from "axios";

const root = ReactDOM.createRoot(document.getElementById("root"));

axios.defaults.baseURL = process.env.REACT_APP_API_URL;

// if (localStorage.getItem("token")) {
//   axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("token");
// }

root.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();