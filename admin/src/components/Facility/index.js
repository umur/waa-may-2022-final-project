import React from "react";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import BedIcon from "@mui/icons-material/Bed";

const Facility = () => {
  return (
    <React.Fragment>
      <Box sx={{ minWidth: 200, maxWidth: 200 }}>
        <Card variant="outlined">
          <CardContent>
            <BedIcon />
            <Typography sx={{ mb: 1.5 }} color="text.secondary">
              Bedroom
            </Typography>
            <Typography variant="body2">1 bedroom</Typography>
          </CardContent>
        </Card>
      </Box>
    </React.Fragment>
  );
};
export default Facility;
