import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import Dashboard from './containers/Dashboard/Dashboard';
import axios from 'axios'; 

function App() {
  return (
    <BrowserRouter>
      <Dashboard />
    </BrowserRouter>
  );
}

export default App;
