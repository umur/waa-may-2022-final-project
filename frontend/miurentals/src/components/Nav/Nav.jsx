import { ContainerNav, ItemNav } from "./Nav.styled";
import logoImg from "../../assets/images/logo.PNG";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import {
  tenantOption,
  adminOption,
  landLordOption,
  newUserOption,
} from "../../utils/navOption";
const Nav = ({ role = "newUser" }) => {
  const [options, setOptions] = useState([]);
  useEffect(() => {
    if (role == "landlord") {
      setOptions(landLordOption);
    } else if (role == "admin") {
      setOptions(adminOption);
    } else if (role == "tenant") {
      setOptions(tenantOption);
    } else if (role == "newUser") {
      setOptions(newUserOption);
    }
  }, []);

  return (
    <ContainerNav>
      <ItemNav src={logoImg} />
      {options.map((option) => (
        <Link
          to={option.path}
          key={option.id}
          style={{ textDecoration: "none" }}
        >
          <ItemNav>{option.title}</ItemNav>
        </Link>
      ))}

      {/* <ItemNav src={logoImg} />
            <Link to="/" style={{textDecoration: "none"}}>
                <ItemNav >
                     Home          
                 </ItemNav>
            </Link>
            <Link to="/aboutus" style={{textDecoration: "none"}}>
                <ItemNav>
                    About Us
                </ItemNav>
            </Link>
            <Link to="/aboutus" style={{textDecoration: "none"}}>
                <ItemNav to="/aboutus">
                    Offers
                </ItemNav>
            </Link>
            <Link to="/aboutus" style={{textDecoration: "none"}}>
                <ItemNav to="/aboutus">
                    Sign Up
                </ItemNav>
            </Link>
            <Link to="/login" style={{textDecoration: "none"}}>
                 <ItemNav to="/login">
                    Login
                </ItemNav>
            </Link> */}
    </ContainerNav>
  );
};

export default Nav;
