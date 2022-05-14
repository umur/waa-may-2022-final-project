import { useState } from "react";
import { Link } from "react-router-dom";
import "../App.css";
import "../components/SignUp.css";

const SignUp = () => {
  const [email, setEmail] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [password, setPassword] = useState("");
  const [retypedPassword, setRetypedPassword] = useState("");
  const [role, setRole] = useState("");

  const url = "http://localhost:8080/api/v1/sign-up";

  const registeruser = async (user) => {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    };
    fetch(url, requestOptions)
      .then((response) => response.json())
      .then((data) => {
        console.log("Sign Up");
      });
  };

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

    registeruser({ firstName, lastName, password });
    setEmail("");
    setFirstName("");
    setLastName("");
    setPassword("");
    setRetypedPassword("");
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
          <div class="dropdown">
            <button class="dropbtn">Select Role</button>
            <div class="dropdown-content">
              <a href="#">Landlord</a>
              <a href="#">Tenant</a>
            </div>
          </div>
        </div>

        <div className="center">
          <Link to="/" className="button-margin">
            <input type="button" value="Back" className="btn btn-block button-padding" />
          </Link>

          <input type="submit" value="Sign Up" className="btn btn-block button-padding" />
        </div>
      </form>
      <div className="center">
        <Link to="/">Go Back</Link>
      </div>
    </div>
  );
};

export default SignUp;
