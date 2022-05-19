import React from "react";
import AdminView from "../components/Admin/AdminView";
import Navbar from "../components/Navbar";
const Admin = () => {
  return (
    <div>
      <Navbar />
      <AdminView head="Last 10 listed properties" />
    </div>
  );
};

export default Admin;
