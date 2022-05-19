import Properties from "../components/Properties";
import PropertyDetail from "../components/PropertyDetail";
import { Route, Routes } from "react-router";
import React from "react";
import AddProperty from "../components/AddProperty";
import RentProcess from "../components/RentProcess";
import ChartsAdmin from "../components/ChartsAdmin";
import ChartsUser from "../components/LandlordCharts";
import { getRole } from "../util/Utility";
import Register from "../components/Login/Register";

export default function MyRoute() {

    const role = getRole();

    return (

        <Routes>
            
            <Route path="properties" element={<Properties/>}>
            </Route>

            <Route path="properties/:id" element={<PropertyDetail/>}>
            </Route>

            <Route path="add-property" element={<AddProperty></AddProperty>}>
            </Route>

            <Route path="rent/:id" element={<RentProcess></RentProcess>}>
            </Route>

            <Route path="admin-charts" element={<ChartsAdmin></ChartsAdmin>}>
            </Route>

            <Route path="landlord-charts" element={<ChartsUser></ChartsUser>}>
            </Route>

            <Route path="register" element={<Register></Register>}>
            </Route>


        </Routes>


    );
}