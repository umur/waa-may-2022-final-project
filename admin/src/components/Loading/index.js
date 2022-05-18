import { Backdrop, CircularProgress } from '@mui/material';
import React from "react";

function Loading({ loading, onClose }) {
  return (
    <Backdrop
      sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
      open={loading}
      onClick={onClose}
    >
      <CircularProgress color="inherit" />
    </Backdrop>
  );
}

export default Loading;
