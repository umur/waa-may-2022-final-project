import logo from './logo.svg';
// import './App.css';
import Home from './pages/home/Home';
import React from 'react';
import 'antd/dist/antd.css';
import './index.css';
import Nav from './components/nav/Nav';
import SideBar from './components/leftsidebar/sidebar';
import Breadcrumb from './components/breadcrumb/breadcrumb';
import User from './pages/users/index';
import { Routes, Route } from "react-router-dom";
import { Layout, Menu } from 'antd';
import Properties from './pages/properties/index';
const { Header, Content, Sider } = Layout;

function App() {
  return (
    <div className="App">

      <Layout>
          {/* <Nav role="ADMIN"></Nav> */}
          <Nav role=""></Nav>
          <Layout>
              <SideBar></SideBar>
              <Layout style={{ padding: '0 24px 24px',}}>
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
                          <Route path="/login" element={<User></User>} />
                          <Route path="/register" element={<User></User>} />
                      </Routes>

                      
                  </Content>
              </Layout>
          </Layout>
      </Layout>
      
    </div>
  );
}

export default App;

