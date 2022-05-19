
import React, { useState } from 'react';
import { BrowserRouter } from 'react-router-dom';
import './App.css';
import Dashboard from './containers/Dashboard';
import { config } from './Constants'
import Login from './components/Login/LoginScreen';

function App() {
  
  const [token, setToken] = useState();

  const key = localStorage.getItem('token');

  const stateNull = () => {
    setToken(null);
  }

  if(!token && !key) {
    return <Login setToken={setToken}></Login>
  }


  return (
    <div className="App">
      <BrowserRouter>
      <Dashboard setToken={stateNull}></Dashboard>
      </BrowserRouter>
    </div>
  );
}

export default App;
