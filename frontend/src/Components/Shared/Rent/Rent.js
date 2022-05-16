import Button from 'react-bootstrap/Button'
import { useState } from "react";

const Rent = () => {
  const [startDate, setStartDate] = useState("");
  const [endtDate, setEndDate] = useState("");
  const [propertyName, setPropertyName] = useState("");

  const rentProperty = () => {};
  return (
    <>
      <div className="form-control">
        <label>Start Date</label>
        <input
          type="text"
          placeholder="Start Date"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
        />
      </div>
      <div className="form-control">
        <label>End Date</label>
        <input
          type="text"
          placeholder="End Date"
          value={endtDate}
          onChange={(e) => setEndDate(e.target.value)}
        />
      </div>
      <div className="form-control">
        <label>Property Name</label>
        <input
          type="text"
          placeholder="Property Name"
          value={propertyName}
          onChange={(e) => setPropertyName(e.target.value)}
        />
      </div>
      <div>
        <Button variant="primary" onClick={rentProperty()}>
          Rent
        </Button>
      </div>
    </>
  );
};

export default Rent;
