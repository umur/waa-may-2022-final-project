import styled from "styled-components";

export const ContainerFormRegister = styled.div`
  background-color: #f7e9d0;
  width: 400px;
  max-width: 100%;
  box-sizing: border-box;
  padding: 25px;
  margin: 8% auto 0;
  position: relative;
  z-index: 1;
  border-top: 5px solid $yellow;
  -webkit-box-shadow: 0 0 3px rgba(0, 0, 0, 0.1);
  -moz-box-shadow: 0 0 3px rgba(0, 0, 0, 0.1);
  box-shadow: 0 0 3px rgba(0, 0, 0, 0.1);
  -webkit-transform-origin: 50% 0%;
  transform-origin: 50% 0%;
  -webkit-transform: scale3d(1, 1, 1);
  transform: scale3d(1, 1, 1);
  -webkit-transition: none;
  transition: none;
  -webkit-animation: expand 0.8s 0.6s ease-out;
  border-radius: 30px;
`;

export const ContainerFormRegisterBox = styled.div`
  border-left: 1px solid $grey;
`;

export const ContainerTitle = styled.div`
  text-align: center;
  padding-bottom: 15px;
`;

export const ContainerInput = styled.input`
  width: 100%;
  padding: 8px 10px 9px 35px;
  height: 35px;
  border: 5px solid $grey;
  box-sizing: border-box;
  outline: none;
  transition: all 0.3s ease-in-out;
  margin-top: 10px;
  border-radius: 30px;
`;
export const ContainerSubmit = styled.button`
  cursor: pointer;
  border-radius: 5em;
  color: #fff;
  background: linear-gradient(to right, #9c27b0, #e040fb);
  border: 0;
  padding-left: 40px;
  padding-right: 40px;
  padding-bottom: 10px;
  padding-top: 10px;
  font-family: "Ubuntu", sans-serif;
  margin-top: 20px;
  margin-left: 27%;
  font-size: 13px;
  box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
`;
