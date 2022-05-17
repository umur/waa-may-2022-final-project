import { useForm } from "react-hook-form";
import { useDispatch } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { useState } from "react";
import BreadCrumb from "../components/BreadCrumb";

const RentForm = () => {

    const navigate = useNavigate();

    const { register, handleSubmit, formState: { errors } } = useForm({
        defaultValues: {
            amount: null,
            rentEndDate: null,
        }
    });

    const { id } = useParams();


    const onSubmit = async (data) => {
        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const res = await axios.post(`http://localhost:8080/api/v1/properties/${id}/rents`, data,
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`
                    },
                });
            if (res.status == 200) {
                navigate("/dashboard/rent-property");
            }
        } catch (error) {
            alert(error.response?.data?.message)
        }
    }

    return (
        <div className="content-wrapper">
            <BreadCrumb name="Rent Form" />
            <section className="content">
                <div className="container-fluid">
                    <div className="card card-default">
                        <form method="post" onSubmit={handleSubmit(onSubmit)}>
                            <div className="card-body">
                                <div className="row">
                                    <div className="form-group col-md-6">
                                        <label>Amount</label>
                                        <input type="text" {...register("amount", { required: 'Amount is required' })} className="form-control" placeholder="Amount" />
                                        <p className="text-danger">{errors.amount?.message}</p>
                                    </div>
                                    <div className="form-group col-md-6">
                                        <label>Rent End Date</label>
                                        <input type="date" {...register("rentEndDate", { required: 'Rent end date is required' })} className="form-control" placeholder="Rent End Datee" />
                                        <p className="text-danger">{errors.rentEndDate?.message}</p>
                                    </div>
                                </div>
                                <input type="submit" className="btn btn-primary" defaultValue="Submit" />
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default RentForm