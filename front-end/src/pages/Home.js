import React from 'react'
import { Outlet, Route, Routes } from 'react-router-dom'
import Dashboard from '../components/Dashboard'
import Footer from '../components/Footer'
import Header from '../components/Header'
import Sidebar from '../components/Sidebar'
import LandLordList from './landlord/LandLordList'

const Home = () => {

  return (
    <>
    <Header/>
    <Outlet />
    <Sidebar/>
    <Footer/>
    </>
  )
}

export default Home