import { Layout, Menu } from "antd";
import "./nav.css";
import { Link } from "react-router-dom";
import { Navbar, Nav, Container } from "react-bootstrap";

const { Header } = Layout;

const navWithoutLogin = [
  { key: 1, label: `Login` },
  { key: 2, label: `Register` },
  { key: 0, label: `Register` },
];
function getMenu(role) {
  if (role == "") {
    return (
      <>
        <Nav className="me-auto">
          <Link to="#" className="margin-r nav-text">
            About
          </Link>
          <Link to="#" className="nav-text">
            Help
          </Link>
        </Nav>
        <Nav>
          <Link to="/login" className="margin-r nav-text">
            Login
          </Link>
          <Link to="/signup" className="nav-text">
            Register
          </Link>
        </Nav>
      </>
    );
  } else if (role.toLowerCase() === "landlord") {
    return (
      <>
        <Nav className="me-auto">
          <Link to="/properties" className="margin-r nav-text">
            Property
          </Link>
          <Link to="/dashboard-landlord" className="nav-text">
            Dashboard
          </Link>
        </Nav>
        <Nav>
          <Link
            to="/"
            className="nav-text"
            onClick={() => {
              window.sessionStorage.clear();
            }}
          >
            Logout
          </Link>
        </Nav>
      </>
    );
  } else if (role.toLowerCase() === "tenant") {
    return (
      <>
        {/* <Nav className="me-auto">
          <Link to="#" className="margin-r nav-text">
            LatestOrder
          </Link>
        </Nav> */}
        <Nav>
          <Link
            to="/"
            className="nav-text"
            onClick={() => window.sessionStorage.clear()}
          >
            Logout
          </Link>
        </Nav>
      </>
    );
  } else if (role.toLowerCase() === "admin") {
    return (
      <>
        <Nav className="me-auto">
          <Link to="/user" className="margin-r nav-text">
            Users
          </Link>
          <Link to="/dashboard-admin" className="nav-text">
            Dashboard
          </Link>
        </Nav>
        <Nav>
          <Link to="/" className="nav-text">
            Logout
          </Link>
        </Nav>
      </>
    );
  }
}

function Navv(props) {
  const rightStyle = { position: "absolute", top: 0, right: 30 };

  return (
    <Navbar bg="dark" variant="dark">
      <Container>
        <Navbar.Brand>
          <Link to="/" className="nav-text">
            E Property
          </Link>
        </Navbar.Brand>
        {getMenu(props.role)}
      </Container>
    </Navbar>
  );
}

export default Navv;
