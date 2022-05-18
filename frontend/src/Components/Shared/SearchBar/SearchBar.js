import Button from "react-bootstrap/Button";

const SearchBar = ({ keyword, setKeyword }) => {
  const BarStyling = {
    width: "20rem",
    background: "#F2F1F9",
    border: "1px solid",
    padding: "0.5rem",
  };
  const mainStyle = {
    padding: "10px",
    margin: "10px",
  };
  return (
    <div className="mainStyle">
      <label>Search for Property &nbsp;</label>
      <input
        style={BarStyling}
        key="random1"
        value={keyword}
        placeholder={"City"}
        onChange={(e) => setKeyword(e.target.value)}
      />

      {/* <Button variant="primary" onClick={filterProperties}>
        Start Search
      </Button> */}
    </div>
  );
};

export default SearchBar;
