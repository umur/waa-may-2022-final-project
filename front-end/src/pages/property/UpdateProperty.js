import axios from "axios";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";
import { isLandLord } from '../../utils/role';

import BreadCrumb from "../../components/BreadCrumb";

const UpdateProperty = () => {
  const { id } = useParams();
  const [properties, setProperties] = useState({
    id: "",
    propertyName: "",
    propertyType: "",
    noOfBedRoom: "",
    noOfBathRoom: "",
    rentAmount: "",
    city: "",
    state: "",
    street: "",
    zipcode: "",
    securityDepositAmount: "",
  });

  const navigate = useNavigate();

  const [addressId, setAddressId] = useState();

  useEffect(() => {
    if(!isLandLord()){
      navigate('/not-found');
    }
    fetchProperty();
  }, []);

  useEffect(() => {
    reset({
      ...properties,
    });
  }, [properties]);

  const fetchProperty = async () => {
    let token = JSON.parse(localStorage.getItem("token"));
    try {
      const response = await axios.get(
        `http://localhost:8080/api/v1/properties/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token.accessToken}`,
          },
        }
      );
      const {address} = response.data;
      setAddressId(address.id);
      setProperties({ ...response.data,...address });
    } catch (error) {
      console.log(error);
    }
  };

  const {
    register,
    handleSubmit,
    watch,
    reset,
    formState: { errors },
  } = useForm({
    defaultValues: {
      ...properties,
    },
  });

  const onSubmit = async (data) => {
    let token = JSON.parse(localStorage.getItem("token"));
    const { state, zipcode, street, city } = data;
    const {propertyName,propertyType,noOfBedRoom,noOfBathRoom,rentAmount,securityDepositAmount} = data;
    const updatedData = {propertyName,propertyType,noOfBedRoom,noOfBathRoom,rentAmount,securityDepositAmount,address:{id:addressId,state, zipcode, street, city}}
    const response = await axios.put(
      `http://localhost:8080/api/v1/properties/${id}`,
      updatedData,
      {
        headers: {
          Authorization: `Bearer ${token.accessToken}`,
        }
      }
    );
    if (response) {
      alert("Successfully property updated");
      navigate("/dashboard/property");
    }
  };

  return (
    <div className="content-wrapper">
      <BreadCrumb name="Update Property" />

      <section class="content">
        <div class="container-fluid">
          <div class="card card-default">
            <form method="post" onSubmit={handleSubmit(onSubmit)}>
              <div class="card-body">
                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label>Property Name</label>
                      <input
                        type="text"
                        {...register("propertyName", {
                          required: "Property Name is required",
                        })}
                        class="form-control"
                        placeholder="Property Name"
                      />
                      <p className="text-danger">
                        {errors.propertyName?.message}
                      </p>
                    </div>

                    <div class="form-group">
                      <label>Property Type</label>
                      <input
                        type="text"
                        {...register("propertyType", {
                          required: "Property Type is required",
                        })}
                        class="form-control"
                        placeholder="Property Type"
                      />
                      <p className="text-danger">
                        {errors.propertyType?.message}
                      </p>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label>No of Bed Room</label>
                      <input
                        type="text"
                        {...register("noOfBedRoom", {
                          required: "Bed room number is required",
                        })}
                        class="form-control"
                        placeholder="No of bed room"
                      />
                      <p className="text-danger">
                        {errors.noOfBedRoom?.message}
                      </p>
                    </div>

                    <div class="form-group">
                      <label>No of Bath Room</label>
                      <input
                        type="text"
                        {...register("noOfBathRoom", {
                          required: "Bath room number is required",
                        })}
                        class="form-control"
                        placeholder="No of bath room"
                      />
                      <p className="text-danger">
                        {errors.noOfBathRoom?.message}
                      </p>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label>State</label>
                      <input
                        type="text"
                        {...register("state", {
                          required: "State Name is required",
                        })}
                        class="form-control"
                        placeholder="State Name"
                      />
                      <p className="text-danger">{errors.state?.message}</p>
                    </div>

                    <div class="form-group">
                      <label>City</label>
                      <input
                        type="text"
                        {...register("city", { required: "City is required" })}
                        class="form-control"
                        placeholder="Provide City"
                      />
                      <p className="text-danger">{errors.city?.message}</p>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label>Street</label>
                      <input
                        type="text"
                        {...register("street", {
                          required: "Street is required",
                        })}
                        class="form-control"
                        placeholder="Street"
                      />
                      <p className="text-danger">{errors.street?.message}</p>
                    </div>

                    <div class="form-group">
                      <label>Zip</label>
                      <input
                        type="text"
                        {...register("zipcode", {
                          required: "Zip is required",
                        })}
                        class="form-control"
                        placeholder="Zip code"
                      />
                      <p className="text-danger">{errors.zip?.message}</p>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label>Rent Amount</label>
                      <input
                        type="number"
                        {...register("rentAmount", {
                          required: "Rent amount is required",
                        })}
                        class="form-control"
                        placeholder="Rent Amount"
                      />
                      <p className="text-danger">
                        {errors.rentAmount?.message}
                      </p>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label>Security Deposit Amount</label>
                      <input
                        type="text"
                        {...register("securityDepositAmount", {
                          required: "Security deposit amount is required",
                        })}
                        class="form-control"
                        placeholder="Security Deposit Amount"
                      />
                      <p className="text-danger">
                        {errors.securityDepositAmount?.message}
                      </p>
                    </div>
                  </div>
                </div>
                <input type="submit" class="btn btn-primary" value="Submit" />
              </div>
            </form>
          </div>
        </div>
      </section>
    </div>
  );
};

export default UpdateProperty;
