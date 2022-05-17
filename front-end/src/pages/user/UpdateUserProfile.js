import axios from "axios";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom"
import BreadCrumb from "../../components/BreadCrumb";


const UpdateUserProfile = () => {
    const { id } = useParams();

    const [userProfileData, setUserProfileData] = useState({
        firstName: "",
        lastName: "",

    });

    const navigate = useNavigate();

    useEffect(() => {
        fetchUserDtls();
    }, []);

    useEffect(() => {
        reset({
            ...userProfileData,
        });
    }, [userProfileData]);

    const fetchUserDtls = async () => {
        let token = JSON.parse(localStorage.getItem("token"));
        try {
            const response = await axios.get(
                `http://localhost:8080/api/v1/users/${id}`,
                {
                    headers: {
                        Authorization: `Bearer ${token.accessToken}`,
                    },
                }
            );
            setUserProfileData({ ...response.data });
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
        defaultValues: { ...userProfileData },
    });

    const onSubmit = async (data) => {
        let token = JSON.parse(localStorage.getItem("token"));
        const response = await axios.put(
            `http://localhost:8080/api/v1/users/${id}`,
            data,
            {
                headers: {
                    Authorization: `Bearer ${token.accessToken}`,
                }
            }
        );
        if (response) {
            alert("User Profile updated Successfully!");
            navigate("/dashboard/profile");
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
                                            <label>User First Name</label>
                                            <input
                                                type="text"
                                                {...register("firstName", {
                                                    required: "First Name is required",
                                                })}
                                                class="form-control"
                                                placeholder="First Name"
                                            />
                                            <p className="text-danger">
                                                {errors.firstName?.message}
                                            </p>
                                        </div>


                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>User Last Name</label>
                                            <input
                                                type="text"
                                                {...register("lastName", {
                                                    required: "Last Name is required",
                                                })}
                                                class="form-control"
                                                placeholder="Last Name"
                                            />
                                            <p className="text-danger">
                                                {errors.lastName?.message}
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

export default UpdateUserProfile;