import React, { useContext } from "react";
import Header from "../components/Header";
import Default from "../assets/img/profile.png";
import { AuthContext } from "context/AuthContext";
import PropertyItem from "components/PropertyItem";

const Profile = () => {
  const { user } = useContext(AuthContext);
  return (
    <div>
      <Header />
      <div className="profile-container">
        <div className="image-container">
          <img src={Default} alt="default profile" className="default image" />
        </div>
        <div>
          <h3 className="name">
            {user.firstName} {user.lastName}
          </h3>
          <h3 className="email">{user.email}</h3>
        </div>
        <hr />
        <h3 className="title">Rent History</h3>
        <div className="rent-list">
          <div className="item">
            <PropertyItem
              key={123123}
              property={{
                id: "asdfasdf",
                numberOfBedrooms: "asdfasdf",
                numberOfBathrooms: "asdfasdf",
                photos: "asdfasdf",
                propertyName: "asdfasdf",
                rentAmount: "asdfasdf",
                city: "asdfasdf",
                state: "asdfasdf",
                propertyType: "asdfasdf",
              }}
            />
          </div>
          <div className="description">
            <div>Rented period: 2/22/2022 - 2/30/2022</div>
            <div> Total price: $100</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
