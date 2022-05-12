import React, { useState } from "react";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import { DateRange } from "react-date-range";
import TextField from "@mui/material/TextField";
import moment from "moment";
import "./index.css";
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";

const RentForm = () => {
  const [state, setState] = useState([
    {
      startDate: new Date(),
      endDate: null,
      key: "selection",
    },
  ]);
  const [open, setOpen] = useState(false);

  return (
    <>
      <Box sx={{ minWidth: 300, maxWidth: 300 }} className="rent-form">
        <Card variant="outlined">
          <CardContent className="content">
            {!open && (
              <>
                <TextField
                  label="Outlined"
                  color="primary"
                  onFocus={() => setOpen(true)}
                />
                <TextField
                  label="Outlined"
                  color="primary"
                  onFocus={() => setOpen(true)}
                />
              </>
            )}
          </CardContent>
        </Card>
      </Box>
      {open && (
        <div className="date-picker">
          <DateRange
            editableDateInputs={true}
            onChange={(item) => {
              if (
                moment(item.selection.startDate).format("MM-DD-YYYY") !==
                moment(item.selection.endDate).format("MM-DD-YYYY")
              ) {
                setOpen(false);
              } else if (
                item.selection.startDate === "" &&
                item.selection.endDate === ""
              ) {
                setOpen(false);
              }
              setState([item.selection]);
            }}
            moveRangeOnFirstSelection={false}
            ranges={state}
          />
        </div>
      )}
    </>
  );
};

export default RentForm;
