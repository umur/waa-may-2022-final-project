import React from "react";
import Header from "../components/Header";
import Carousel from "../components/Carousel";
import Facility from "../components/Facility";
import RentForm from "../components/RentForm";

const PropertyDetail = () => {
  return (
    <>
      <Header />
      <Carousel />
      <div className="detail-description">
        <div style={{ marginBottom: "20px" }}>
          <h4 className="title">Apartments hosted by Puru</h4>
        </div>
        <div>
          <h4 className="title">$800</h4>
          <p className="sub-title">Fairfield, Iowa</p>
        </div>
        <hr />
        <div>
          <h4 className="title">Facilities</h4>
          <div className="facility-list">
            <Facility />
            <Facility />
            <Facility />
          </div>
        </div>
        <div className="rent-form-container">
          <RentForm />
        </div>
      </div>
    </>
  );
};

export default PropertyDetail;
