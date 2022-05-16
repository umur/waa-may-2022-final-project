import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import { Nav, Navbar } from "react-bootstrap";
import "./Header.css";

const Header = () => {
  return (
    <>
      <Navbar bg="dark" variant="dark" sticky="top">
        <Navbar.Brand>Property App</Navbar.Brand>

        <Nav className="padding">
          <Nav.Link as={Link} to="/sign-in">
            Sign In
          </Nav.Link>
          <Nav.Link as={Link} to="/sign-up">
            Sign Up
          </Nav.Link>
          <Nav.Link as={Link} to="/admin">
            Admin
          </Nav.Link>
          <Nav.Link as={Link} to="/tenant">
            Tenant
          </Nav.Link>
          <Nav.Link as={Link} to="/landlord">
            Landlord
          </Nav.Link>
        </Nav>
      </Navbar>
    </>

    // <div>
    //   <div className="App-header">
    //     <Link className="header-button" to="/sign-in">
    //       Sign IN
    //     </Link>
    //     <Link className="header-button" to="/sign-up">
    //       Sign Up
    //     </Link>
    //     <Link className="header-button" to="/admin">
    //       Admin Dashboard
    //     </Link>
    //     <Link className="header-button" to="/tenant">
    //       Tenant Dashboard
    //     </Link>
    //     <Link className="header-button" to="/landlord">
    //       Landlord Dashboard
    //     </Link>
    //   </div>
    // </div>
  );
};

export default Header;