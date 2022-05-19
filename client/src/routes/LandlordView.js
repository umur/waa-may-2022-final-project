import React from "react";
import LandlordPage from "../components/Landlord/LandlordView";
import Navbar from "../components/Navbar";
const Lease = () => {
  return (
    <div>
      <Navbar />
      <LandlordPage head="Your listed properties" />
    </div>
  );
};

export default Lease;
