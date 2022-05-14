import React from "react";
import Styled from "styled-components";
import { Link } from "react-router-dom";

export const ContainerNav = Styled.nav`
// background-color: #D0B49F;
min-height: 15vh;
width:100%;
color: white;
text-align: justify;
display:flex;
margin:auto;
align-items:center;
`;

export const ItemNav = Styled.p`
padding: 10px 30px;
color:white;
font-weight:bold;
font-size:25px;
text-decoration:none;
`;

export const ImgNav = Styled.img`
width: 400px;
height: 400px;
`;
