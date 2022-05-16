import React from 'react'
import { useSelector } from 'react-redux'


const DashboardAdmin = () => {

  const user = useSelector((state) => state.user.value); //token, role, email, id

  const config = {
    headers: { Authorization: `Bearer ${user.token}` }
  };

  
  console.log(user);

  return (
    <div>dashboard-admin</div>
  )
}

export default DashboardAdmin