import Property from "../components/Property";
import NavBar from "./../components/Navbar";

function listProperties(props) {
  return (
    <div>
      <div>
        <NavBar />
        <Property head="List of all properties"/>
      </div>
    </div>
  );
}

export default listProperties;
