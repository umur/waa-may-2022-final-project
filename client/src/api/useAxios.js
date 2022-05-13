import { useContext, useState, useEffect, useCallback } from "react";
import axios from "axios";
import { AuthContext } from "../context/AuthContext";

export const useAxios = (method, url, postData = null) => {
  const { isSignedIn } = useContext(AuthContext);
  const [data, setData] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);

  const executeRequest = useCallback(async () => {
    try {
      let response = await axios[method](url);
      setData(response.data);
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  }, [method, url]);

  useEffect(() => {
    if (data === null && method === "get") {
      executeRequest();
    }
  }, [data, executeRequest, method]);

  return { data, error, loading, execute: executeRequest };
};
