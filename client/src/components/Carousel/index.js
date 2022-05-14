import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "./index.css";
import DefaultImage from "../../assets/img/default-house.jpeg";

const Carousel = ({ list }) => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 300,
    slidesToShow: 1,
    centerMode: true,
    variableWidth: true,
  };
  let items =
    list.length > 0 ? (
      <div className="carousel-item">
        <img
          src="https://images.unsplash.com/photo-1554995207-c18c203602cb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"
          className="carousel-img"
          alt="house"
        />
      </div>
    ) : (
      <div className="carousel-item">
        <img
          src="https://images.unsplash.com/photo-1554995207-c18c203602cb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"
          className="carousel-img"
          alt="house"
        />
      </div>
    );
  let defaultList = [];
  defaultList.push(
    <div className="carousel-item">
      <img src={DefaultImage} className="carousel-img" alt="house" />
    </div>
  );
  defaultList.push(
    <div className="carousel-item">
      <img src={DefaultImage} className="carousel-img" alt="house" />
    </div>
  );
  return (
    <>
      <Slider {...settings}>{list.length > 0 ? items : defaultList}</Slider>
    </>
  );
};

export default Carousel;
