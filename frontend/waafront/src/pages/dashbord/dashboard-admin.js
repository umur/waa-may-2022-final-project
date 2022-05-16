import React from 'react'
import { useSelector } from 'react-redux'


const DashboardAdmin = () => {

  const userToken = useSelector((state) => state.user.value);



  return (
    <div>dashboard-admin</div>
  )
}

export default DashboardAdmin