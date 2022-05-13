import React from "react";
import Header from "../components/Header";
import Carousel from "../components/Carousel";
import Facility from "../components/Facility";
import RentForm from "../components/RentForm";
import { useAxios } from "../api/useAxios";
import { useParams } from "react-router-dom";

const PropertyDetail = () => {
  console.log("property");
  const { id } = useParams();
  const { data, error, loading } = useAxios("get", "/properties/" + id);

  if (loading) {
    return <h1>loading</h1>;
  }
  console.log(data);
  return (
    <>
      <Header />
      <Carousel />
      <div className="detail-description">
        <div style={{ marginBottom: "20px" }}>
          <h4 className="title">
            {data?.propertyType} {data?.propertyName} hosted by Puru
          </h4>
        </div>
        <div>
          <h4 className="title">${data?.rentAmount}</h4>
          <p className="sub-title">
            {data?.city}, {data?.state}
          </p>
        </div>
        <hr />
        <div style={{ marginBottom: "100px", marginTop: "50px" }}>
          <h4 className="title">Facilities</h4>
          <div className="facility-list">
            <Facility count={data?.numberOfBedrooms} type="bed" />
            <Facility count={data?.numberOfBathrooms} type="bath" />
          </div>
        </div>
        <hr />
        <div>
          <h4 className="title">Description</h4>
          <div className="description">
            <p>{data?.description}</p>
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
