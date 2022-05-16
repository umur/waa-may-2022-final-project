import React, { useState, useContext } from "react";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import { DateRange } from "react-date-range";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import moment from "moment";
import { Form, useFormik } from "formik";
import { useAxios } from "../../api/useAxios";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import "./index.css";
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";

const RentForm = ({ amount, security, id }) => {
  const { isSignedIn } = useContext(AuthContext);
  const navigate = useNavigate();
  const [state, setState] = useState([
    {
      startDate: new Date(),
      endDate: new Date(),
      key: "selection",
    },
  ]);
  const [open, setOpen] = useState(false);
  const { data, error, loading, execute } = useAxios(
    "post",
    "/properties/rent/" + id
  );

  if (data) {
    navigate("/payment/" + data?.data?.id);
  }

  let diff = 0;
  let rent = 0;
  let totalSecurity = 0;
  if (state[0].endDate) {
    let start = moment(state[0].startDate);
    let end = moment(state[0].endDate);
    diff = end.diff(start, "days");
    rent = diff * amount;
    totalSecurity = diff * security;
  }
  const onSubmit = () => {
    if (isSignedIn) {
      let data = {
        startDate: state[0].startDate,
        endDate: state[0].endDate,
      };
      execute(data);
    } else {
      navigate("/login");
    }
  };

  return (
    <>
      <Box sx={{ minWidth: 300, maxWidth: 300 }} className="rent-form">
        <Card variant="outlined">
          <CardContent className="content">
            {!open && (
              <>
                <div className="date-input">
                  <TextField
                    label="Start"
                    color="primary"
                    value={moment(state[0].startDate).format("MM/DD/YYYY")}
                    onFocus={() => setOpen(true)}
                  />
                  <TextField
                    label="End"
                    color="primary"
                    value={
                      state[0].endDate
                        ? moment(state[0].endDate).format("MM/DD/YYYY")
                        : ""
                    }
                    onFocus={() => setOpen(true)}
                  />
                </div>
                <div className="price">
                  {state[0].endDate !== null && (
                    <>
                      <div>
                        <span>Rent x {diff} days</span>
                        <span>${rent}</span>
                      </div>
                      <div>
                        <span>Security Deposit</span>
                        <span>${totalSecurity}</span>
                      </div>
                      <hr />
                      <div>
                        <span>Total</span>
                        <span>${rent + totalSecurity}</span>
                      </div>
                    </>
                  )}
                </div>
                <Button
                  variant="contained"
                  className="button"
                  disabled={state[0].endDate === null}
                  onClick={onSubmit}
                >
                  Rent
                </Button>
              </>
            )}
          </CardContent>
        </Card>
      </Box>
      {open && (
        <div className="date-picker">
          <DateRange
            editableDateInputs={true}
            minDate={new Date()}
            // disabledDates={[new Date("22/5/2022")]}
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
