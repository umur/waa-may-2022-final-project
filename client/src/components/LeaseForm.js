import React from "react";
import "./LeaseFormStyle.css";
import { useParams } from "react-router-dom";

const LeaseForm = () => {
  const { id } = useParams();
  return (
    <div>
      <h1> This is the rental form for peoperty with id {id}</h1>
      <div className="form">
        <form>
          <label>Property location</label>
          <label>Number of rooms</label>
          <label>Rent per month</label>
          <label>Owner name</label>
          <label> Owner Phone number: </label>
          <label>Start date</label>
          <input type="date"></input>
          <label>End date</label>
          <input type="date"></input>
          <label>Total rent</label>
          <label className="total-rent">Calculated total rent</label>
          <button className="rent-btn">Submit</button>
        </form>
      </div>
    </div>
  );
};

export default LeaseForm;
