import { useContext } from "react";
import axios from "axios";
import "firebase/database";
import { AuthContext } from "../context/AuthContext";

const useAxios = async (method, url, data = null) => {
  const { isSignedIn } = useContext(AuthContext);
  let response = await axios[method](process.env.API_URL + url, { data });
  let result = await response.json();
  return result;
};

export default useAxios;
