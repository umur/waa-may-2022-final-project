import React from "react";
import Header from "../components/Header";
import Carousel from "../components/Carousel";
import Facility from "../components/Facility";

const PropertyDetail = () => {
  return (
    <>
      <Header />
      <Carousel />
      <div className="detail-description">
        <div>
          <h4 className="title">$800</h4>
          <p className="sub-title">Fairfield, Iowa</p>
        </div>
        <hr />
        <div>
          <h4 className="title">Facilities</h4>
          <div className="facility-list">
            <Facility />
          </div>
        </div>
      </div>
    </>
  );
};

export default PropertyDetail;
