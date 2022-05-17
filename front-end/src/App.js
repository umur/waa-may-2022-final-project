import './App.css';
import { Login } from './pages/Login';
import Signup from './pages/Signup';
import { Navigate, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import PrivateRoute from './components/PrivateRoute';
import Dashboard from './components/Dashboard';
import PropertyList from './pages/property/PropertyList';
import AddProperty from './pages/property/AddProperty';

import ForgotPassword from './pages/ForgotPassword';
import ResetPassword from './pages/ResetPassword';
import Report from './pages/Report';
import UpdateProperty from './pages/property/UpdateProperty';
import ProductDetail from './pages/property/ProductDetail';
import RentProperty from './pages/RentProperty';
import RentForm from './pages/RentForm';
import LineChart from './pages/LineChart';
import NotFound from './pages/404';
import User from './pages/Users';
import ChangePassword from './pages/ChangePassword';
import UserProfile from './pages/user/UserProfile';
import UpdateUserProfile from './pages/user/UpdateUserProfile';


function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to="/dashboard" />}>
        </Route>

        <Route
          path='/dashboard'
          element={
            <PrivateRoute>
              <Home></Home>
            </PrivateRoute>
          }>
          <Route path='' element={<Dashboard />}>
          </Route>
          

          <Route path='property' element={<PropertyList />}>
     
          </Route>

          <Route path='profile' element={<UserProfile />} ></Route>
          <Route path="profile/update/:id" element={<UpdateUserProfile />} />

          <Route path="property/updateProperty/:id" element={<UpdateProperty />} />

          <Route path="property/propertyDetail/:id" element={<ProductDetail />} />
          
          <Route path="change-password/:id" element={<ChangePassword />} />

          <Route path='add' element={<AddProperty />}></Route>

          

          <Route path="report" element={<Report />}>
          </Route>

          <Route path="line-report" element={<LineChart />}>
          </Route>


          <Route path="users" element={<User />}>
          </Route>

          <Route path="rent-property" element={<RentProperty />}>
          </Route>
          
          <Route path="rent-property/rent-form/:id" element={<RentForm />}>
          </Route>

        </Route>

        <Route path='/login' element={<Login />}>
        </Route>

        <Route path='/signup' element={<Signup />}>
        </Route>
        <Route path='/forgot-password' element={<ForgotPassword />}>
        </Route>
        <Route path='/reset-password' element={<ResetPassword />}>
        </Route>
        <Route path='*' element={<NotFound />} />
        <Route path='/not-found' element={<NotFound />} />
      </Routes>
    </>
  );
}

export default App;
