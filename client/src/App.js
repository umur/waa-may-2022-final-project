import "./App.css";
import Home from "./routes/Home";
import About from "./routes/About";
import Contact from "./routes/Contact";
import Properties from "./routes/properties";
import { Route, Routes } from "react-router-dom";
import Lease from "./routes/Lease";
import PropertyRoute from "./routes/PropertyRouter";
//redux or context(wrap the whole app with auth context)

function App() {
  return (
    <div>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/properties" element={<Properties />} />
          <Route path="/properties/:id/lease" element={<Lease />} />
          <Route path="/properties/:id" element={<PropertyRoute />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
