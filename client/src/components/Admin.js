import Axios from 'axios';
import React from 'react'
import { useState } from 'react'
import { Link } from 'react-router-dom';

//check
const Admin = () => {
  const deleteUser = (id) => {
    Axios.delete(`http://localhost:8080/api/users/${id}`).then((response) => {
      setUserList(userList.filter((val) => {
        return val.id != id
      }))
    })
  }
  const [userList, setUserList] = useState([]);
  const getUserList = () => {
    Axios.get("http://localhost:8080/api/users/").then((response) => {
      setUserList(response.data);
    })
  }
  return (
    <div><Link to="/login" className="header btn" >Signout</Link>
      <br /><br />
      <button className='btn' style={{ backgroundColor: "green" }} onClick={getUserList} >Display Users</button>
      <div>
        {userList.map((val, key) => {
          return <div className='userList'>
            <div>
              <h3>Id: {val.id}</h3>
              <h3>First Name: {val.firstName}</h3>
              <h3>Last Name: {val.lastName}</h3>
              <h3>Email: {val.email}</h3>
              <h3>Role: {val.role}</h3>
            </div>
            <div>
              <button className='btn' style={{color:"red"}} onClick={(val) =>{
                deleteUser(val.id)
              }}>Delete</button>
            </div>

          </div>


        })}
      </div>
    </div>
  )
}

export default Admin