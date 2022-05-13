import './App.css';
import { Login } from './pages/Login';
import Signup from './pages/Signup';
import { Navigate, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import PrivateRoute from './components/PrivateRoute';

function App() {
  return (
   <>
    <Routes>
      <Route path="/" element={<Navigate to="/dashboard" />}></Route>

      <Route
       path='/dashboard'
       element={
        <PrivateRoute path = '/login'><Home /></PrivateRoute>
        }>
      </Route>

      <Route path='/login' element={<Login/>}>
      </Route>

      <Route path='/signup' element={<Signup/>}>
      </Route>
    </Routes>
   </>
  );
}

export default App;
