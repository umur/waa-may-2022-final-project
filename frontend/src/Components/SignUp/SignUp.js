import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import Service from "../Shared/Service";
import "./SignUp.css";

const SignUp = () => {
  const [email, setEmail] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [password, setPassword] = useState("");
  const [retypedPassword, setRetypedPassword] = useState("");
  const [role, setRole] = useState("");
  const [selectedProperty, setSelectedProperty] = useState("");
  const [roleList, setRoleList] = useState([]);

  useEffect(() => {
    getRoleList();
  }, []);

  const getRoleList = () => {
    setRoleList([
      {
        id: 1,
        description: "admin",
      },
      {
        id: 2,
        description: "tenant",
      },
      {
        id: 3,
        description: "landlord",
      },
    ]);
  };

  // const registeruser = async (user) => {
  //   const requestOptions = {
  //     method: "POST",
  //     headers: { "Content-Type": "application/json" },
  //     body: JSON.stringify(user),
  //   };
  //   fetch(url, requestOptions)
  //     .then((response) => response.json())
  //     .then((data) => {
  //       console.log("Sign Up");
  //     });
  // };

  const onSubmit = (e) => {
    e.preventDefault();

    if (!email || !firstName || !lastName || !password || !retypedPassword) {
      alert("Please ensure all the fields are filled");
      return;
    }

    if (password !== retypedPassword) {
      alert("Passwords do not match!");
      return;
    }

    const selectedRole = roleList.filter((r) => r.description == role);

    // console.log(selectedRole, role);
    console.log("Selected Role : ", selectedRole);
    console.log("Selected Role[0] : ", selectedRole[0]);
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      id: 5,
      email: email,
      firstName: firstName,
      lastname: lastName,
      password: password,
      role: selectedRole[0],
    });

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch(Service.SignUp, requestOptions)
      .then((response) => response.text())
      .then((result) => console.log(result))
      .catch((error) => console.log("error", error));

    // registeruser({ firstName, lastName, password });
    setEmail("");
    setFirstName("");
    setLastName("");
    setPassword("");
    setRetypedPassword("");
  };

  const handleDropDown = (value) => {
    console.log("value : ", value);
    setRole(value);

    // const selectedRole = roleList.filter(
    //   (role) => role.description == selectedRole.description
    // );
    // setRole(roleList.filter(
    //   (role) => role.description == selectedRole.description
    // ));
  };

  return (
    <div className="main-container">
      <div className="center">
        <h2>Sign Up</h2>
      </div>
      <form className="add-form" onSubmit={onSubmit}>
        <div className="form-control">
          <label>Email</label>
          <input
            type="text"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="form-control">
          <label>First Name</label>
          <input
            type="text"
            placeholder="First Name"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />
        </div>
        <div className="form-control">
          <label>Last Name</label>
          <input
            type="text"
            placeholder="Last Name"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>
        <div className="form-control">
          <label>Password</label>
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="form-control">
          <label>Re Type Password</label>
          <input
            type="password"
            placeholder="Password"
            value={retypedPassword}
            onChange={(e) => setRetypedPassword(e.target.value)}
          />
        </div>
        <div>
          <div className="dropdown">
            <select
              className="dropdown"
              name="selectedProperty"
              value={selectedProperty}
              onChange={(e) => handleDropDown(e.currentTarget.value)}
            >
              {roleList.map((role) => (
                <option key={role.id} value={role.description}>
                  {role.description}
                </option>
              ))}
            </select>
          </div>
        </div>

        <div className="center">
          <Link to="/" className="button-margin">
            <input
              type="button"
              value="Back"
              className="btn btn-block button-padding"
            />
          </Link>

          <input
            type="submit"
            value="Sign Up"
            className="btn btn-block button-padding"
          />
        </div>
      </form>
    </div>
  );
};

export default SignUp;
