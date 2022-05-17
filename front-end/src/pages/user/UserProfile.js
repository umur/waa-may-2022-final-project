import axios from "axios";
import { useEffect, useState } from "react"
import { Link } from "react-router-dom";
import BreadCrumb from "../../components/BreadCrumb";
import { getUsername } from "../../utils/role";

const UserProfile = () => {
    const [userProfile, setUserProfile] = useState();

    const username = getUsername();
    useEffect(() => {
        get();
      }, []);

    const get = async () => {
        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const response = await axios.get(
                `http://localhost:8080/api/v1/users?email=${username}`,
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`,
                    },
                }
            );
            setUserProfile({...response.data});
            console.log(response.data);
        } catch (error){
            console.log(error);
        }
    };

    return (
      <div className="content-wrapper">
      <BreadCrumb name="User Profile" />
      <section className="content">
        <div classname="container-fluid">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col col-lg-9 col-xl-7">
              <div className="card">
                                
                <div className="card-body p-4 text-black">
                  <div className="mb-5">
                    <p className="lead fw-normal mb-1">User Profile</p>
                    <div className="p-4" style={{ backgroundColor: "#f8f9fa" }}>
                    <p className="text-right">
                        <Link to={`update/${userProfile?.id}`}><i className='fas fa-edit text-primary ml-2'></i></Link>
                        </p>
                      <p className="font-italic mb-1">
                        Name: {userProfile?.firstName +" "+userProfile?.lastName}
                      </p>
                      <p className="font-italic mb-1">
                        Role: {userProfile?.role}
                      </p>
                      <p className="font-italic mb-0">
                        Email: {userProfile?.email}
                      </p>
                      
                    </div>
                  </div>

                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
    );

};

export default UserProfile