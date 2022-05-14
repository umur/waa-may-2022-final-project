import { useState, useEffect, useCallback } from "react";
import axios from "axios";

export const useAxios = (method, url, postData = null) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(method === "get" ? true : false);

  const executeRequest = useCallback(
    async (postData = null) => {
      if (method !== "get") {
        setLoading(true);
      }
      try {
        let response = await axios[method](url, { ...postData });
        setData(response.data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    },
    [method, url]
  );

  useEffect(() => {
    if (data === null && method === "get") {
      executeRequest();
    }
  }, [data, executeRequest, method]);

  return { data, error, loading, execute: executeRequest };
};
