import "./LandlordProperties.css";

import LandlordPropertyDetails from "./LandlordPropertyDetails";

const LandlordProperties = ({ properties }) => {
  return (
    <>
      <div className="flex-container wrap">
        {properties.map((property, index) => (
          <LandlordPropertyDetails property={property} key={index} />
        ))}
      </div>
    </>
  );
};

export default LandlordProperties;
