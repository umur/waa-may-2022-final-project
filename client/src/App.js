import "./App.css";
import Home from "./routes/Home";
import Properties from "./routes/properties";
import { Route, Routes } from "react-router-dom";
import Lease from "./routes/Lease";
import PropertyRoute from "./routes/PropertyRouter";
import Register from "./routes/Register";
import Login from "./routes/Login";
import LandlordPage from "./components/Landlord/LandlordView";
import AdminPage from "./components/Admin/AdminView";
//redux or context(wrap the whole app with auth context)

function App() {
  return (
    <div>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/properties" element={<Properties />} />
          <Route path="/properties/:id/lease" element={<Lease />} />
          <Route path="/properties/:id" element={<PropertyRoute />} />
          <Route path="/landlord/properties" element={<LandlordPage />} />
          <Route path="/admin" element={<AdminPage />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
