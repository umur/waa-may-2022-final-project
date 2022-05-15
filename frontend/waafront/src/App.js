import logo from './logo.svg';
// import './App.css';
import Home from './pages/home/Home';
import React, {useState} from 'react';
import 'antd/dist/antd.css';
import './index.css';
import Nav from './components/nav/Nav';
import SideBar from './components/leftsidebar/sidebar';
import Breadcrumb from './components/breadcrumb/breadcrumb';
import User from './pages/users/index';
import { Routes, Route } from "react-router-dom";
import { Layout, Menu } from 'antd';
import Properties from './pages/properties/index';
import Login from './pages/login/login';
import 'bootstrap/dist/css/bootstrap.min.css';
import DashboarAdmin from './pages/dashbord/dashboard-admin';
const { Header, Content, Sider } = Layout;

function App() {
    
    const sessionVal = JSON.parse(window.sessionStorage.getItem('token')).jwtToken ;
    let userRole
    if(sessionVal){
        const decodedString = atob(sessionVal.split('.')[1]);
        userRole = JSON.parse(decodedString).role[0].authority;
        const userName = JSON.parse(decodedString).sub;
    }else{
        userRole='';
    }
    

    const [isAdmin, setIsAdmin] = useState(false);
    const [isTenant, setIsTenant] = useState(false);
    const [isLandLord, setIsLandLord] = useState(false);

    return (
        <div className="App">

            <Layout>
                {/* <Nav role="ADMIN"></Nav> */}
                <Nav role={userRole}></Nav>
                <Layout style={{ padding: '0 24px 24px', }}>
                    {/* <Breadcrumb></Breadcrumb> */}
                    <Content
                        className="site-layout-background"
                        style={{
                            padding: 24,
                            margin: 0,
                            marginTop: 20,
                            minHeight: 700,
                        }}
                    >

                        <Routes>
                            <Route path="/" element={<Home></Home>} />
                            <Route path="/users" element={<User></User>} />
                            <Route path="/properties" element={<Properties></Properties>} />
                            <Route path="/rents" element={<User></User>} />
                            <Route path="/login" element={<Login></Login>} />
                            <Route path="/register" element={<User></User>} />
                            <Route path="/dashboard-admin" element={<DashboarAdmin></DashboarAdmin>} />
                        </Routes>


                    </Content>
                </Layout>

            </Layout>

        </div>
    );
}

export default App;

