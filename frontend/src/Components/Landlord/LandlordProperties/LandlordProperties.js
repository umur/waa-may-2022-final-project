import "./LandlordProperties.css";

import LandlordPropertyDetails from "./LandlordPropertyDetails";

const LandlordProperties = ({ properties }) => {
  return (
    <>
      <div className="flex-container wrap">
        {properties.map((property, index) => (
          <LandlordPropertyDetails p={property} key={index} />
        ))}
      </div>
    </>
  );
};

export default LandlordProperties;
