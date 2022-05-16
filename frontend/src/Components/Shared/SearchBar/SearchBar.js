const SearchBar = ({ keyword, setKeyword }) => {
  const BarStyling = {
    width: "20rem",
    background: "#F2F1F9",
    border: "1px solid",
    padding: "0.5rem",
  };
  const mainStyle = {
    padding: "10px",
    margin: "10px"
  };
  return (
    <div style={mainStyle}>
        <label>Search for Property &nbsp;</label>
      <input
        style={BarStyling}
        key="random1"
        value={keyword}
        placeholder={"state / city / zip code"}
        onChange={(e) => setKeyword(e.target.value)}
      />
    </div>
  );
};

export default SearchBar;
