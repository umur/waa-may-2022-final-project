import React from 'react';
import './Header.module.css';
import { useSelector, useDispatch } from 'react-redux';
import { authActions } from '../../store/index';
import { Link, Redirect } from 'react-router-dom';
import 'react-router';
import { useNavigate } from 'react-router';

const Header = () => { 
  const isAuthenticated = useSelector(state => state.auth.isAuthenticated);
  const dispatch = useDispatch();
  const navigate=useNavigate();

  const logoutHandler = () => {
    dispatch(authActions.logout());
    navigate('/login');
  }

  let headerJsx = (
    <nav>
      <header className="header">
        <h1>Redux Auth Demo</h1>
        <ul>
        
        {/* DEAN !!! */}
          <li>
            <Link to="/">My Products</Link>
          </li>
        
          <li>
            <Link to="/counter"> Counter </Link>
          </li>
          <li>
            <Link to="/user"> Profile </Link>
          </li>
          <li>
            <button onClick={logoutHandler}>Logout</button>
          </li>
        </ul>
      </header >
    </nav>
  );
  if (!isAuthenticated) {
    headerJsx = null;
  }

  return headerJsx;
};

export default Header;
