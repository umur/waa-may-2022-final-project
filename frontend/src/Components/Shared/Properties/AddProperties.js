import { useState } from "react";
import { Link } from "react-router-dom";
import "./SignIn.css"

const AddProperties = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const url = "http://localhost:8080/api/v1/sign-in";
  
  const onSubmit = (e) => {
    e.preventDefault();

    if (!username || !password) {
      alert("Please add username and password");
      return;
    }

    loginUser({ username, password });

    setUsername("");
    setPassword("");
  };

  const loginUser = async (user) => {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    };
    fetch(url, requestOptions)
      .then((response) => response.json())
      .then((data) => {
        console.log("Sign in response : ", data);
      });
  };


  return (
    <div className="main-container">
      <div className="center">
        <h2>Sign In</h2>
      </div>
      <form className="add-form" onSubmit={onSubmit}>
        <div className="form-control">
          <label>Username</label>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
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
        <div className="center">
        <input type="submit" value="Sign In" className="btn btn-block button-padding" />
        
          <Link to="/" className="button-margin">
            <input type="button" value="Cancel" className="btn btn-block button-padding" />
          </Link>

          </div>
      </form>
      <div className="center">   
        <Link to='/forgot-password'>Forgot Password ?</Link>
      </div>
    </div>
  );
};

export default AddProperties;
