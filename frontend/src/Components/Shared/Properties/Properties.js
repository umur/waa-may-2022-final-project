import PropertyDetails from "../PropertyDetails/PropertyDetails";
import "./Properties.css";

const Properties = ({ properties }) => {
  return (
    <>
      <div className="flex-container wrap">
        {properties.map((property, index) => (
          <PropertyDetails key={property.id} property={property} />
        ))}
      </div>
    </>
  );
};

export default Properties;
