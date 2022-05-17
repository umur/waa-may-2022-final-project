import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Link, NavLink } from 'react-router-dom'

import { getRole } from '../utils/role';

const Sidebar = () => {


    let role = getRole();


    
    return (
        <aside className="main-sidebar sidebar-dark-primary elevation-4">
            {/* Brand Logo */}
            <a href="index3.html" className="brand-link">
                <img src="/fav.png" alt="AdminLTE Logo" className="brand-image img-circle elevation-3" style={{ opacity: '.8' }} />
                <span className="brand-text font-weight-light">Property Management</span>
            </a>
            {/* Sidebar */}
            <div className="sidebar">
                {/* Sidebar user panel (optional) */}
                <div className="user-panel mt-3 pb-3 mb-3 d-flex">
                    <div className="info">
                        <a href="#" className="d-block">Alexander Pierce</a>
                    </div>
                </div>
                {/* SidebarSearch Form */}
                {/* Sidebar Menu */}
                <nav className="mt-2">
                    <ul className="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                        {/* Add icons to the links using the .nav-icon class
         with font-awesome or any other icon font library */}
                        {role==='ADMIN' || role==='LANDLORD' ? <>
                        <li className="nav-item menu-open">
                            <NavLink to='' className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}>
                                <i className="nav-icon fas fa-tachometer-alt" />
                                <p>
                                    Dashboard
                                </p>
                            </NavLink>
                        </li>
                        </>:""}
                        {role==='ADMIN' ? <><li className="nav-item menu-open">
                            <NavLink className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")} to='report'>
                                <i className="nav-icon fas fa-chart-pie" />
                                <p>
                                    Total Income Report
                                </p>
                            </NavLink>
                        </li>

                        <li className="nav-item menu-open">
                            <NavLink to='line-report' className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}>
                                <i className="nav-icon fas fa-chart-line" />
                                <p>
                                    Weekly Report
                                </p>
                            </NavLink>
                        </li></> :""}                        

                        { role==='TENANT' ? <>
                        <li className="nav-item menu-open">
                            <NavLink to='rent-property' className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}>
                                <i className="nav-icon fas fa-shopping-cart" />
                                <p>
                                    Rent Property
                                </p>
                            </NavLink>
                        </li></>:""
                        }

{ role==='LANDLORD' ? <>
                        <li className="nav-item menu-open">
                            <Link to='users' className="nav-link">
                                <i className="nav-icon far fa-circle nav-icon" />
                                <p>
                                   Users List
                                </p>
                            </Link>
                        </li>

                        <li className="nav-header">Property</li>
                        <li className="nav-item">
                            <NavLink to='property' className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}>
                                <i className="nav-icon fas fa-list" />
                                <p>
                                    List
                                </p>
                            </NavLink>
                            <NavLink to='add' className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}>
                                <i className="nav-icon fas fa-plus" />
                                <p>
                                    Add
                                </p>
                            </NavLink>
                        </li>
                        </> :""}
                    </ul>
                </nav>
                {/* /.sidebar-menu */}
            </div>
            {/* /.sidebar */}
        </aside>

    )
}

export default Sidebar