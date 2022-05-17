import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import BreadCrumb from "../../components/BreadCrumb";

const ProductDetail = () => {
  const { id } = useParams();

  const [product, setProduct] = useState();

  

  useEffect(() => {
    get();
  }, []);

  const get = async () => {
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
      setProduct({ ...response.data });
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="content-wrapper">
      <BreadCrumb name="Properties" />
      <section className="content">
        <div classname="container-fluid">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col col-lg-9 col-xl-7">
              <div className="card">
                <div
                  className="rounded-top text-white d-flex flex-row"
                  style={{ backgroundColor: "#000", height: 200 }}
                >
                  <div
                    className="ms-4 mt-5 d-flex flex-column"
                    style={{ width: 150 }}
                  >
                   {product?.photos ?  <img
                      src={`http://localhost:8080/api/v1/properties/${product.id}/images/${product?.photos[0]?.id}`}
                      alt="Generic placeholder image"
                      className="img-fluid img-thumbnail mt-4 mb-2"
                      style={{ width: 150, zIndex: 1 }}
                    /> : ""
}
                    <button
                      type="button"
                      className="btn btn-outline-dark"
                      data-mdb-ripple-color="dark"
                      style={{ zIndex: 1 }}
                    >
                      Change Image
                    </button>
                  </div>
                  <div className="ms-3" style={{ marginTop: 130 }}>
                    <h5>{product?.propertyName}</h5>
                    <p>{product?.propertyType}</p>
                  </div>
                </div>
                <div
                  className="p-4 text-black"
                  style={{ backgroundColor: "#f8f9fa" }}
                >
                  <div className="d-flex justify-content-end text-center py-1">
                    <div>
                      <p className="mb-1 h5">{product?.noOfBedRoom}</p>
                      <p className="small text-muted mb-0">Bed Room</p>
                    </div>
                    <div className="px-3">
                      <p className="mb-1 h5">{product?.noOfBathRoom}</p>
                      <p className="small text-muted mb-0">Bath Room</p>
                    </div>
                    <div>
                      <p className="mb-1 h5">{product?.rentAmount}</p>
                      <p className="small text-muted mb-0">Rent Amount</p>
                    </div>
                  </div>
                </div>
                <div className="card-body p-4 text-black">
                  <div className="mb-5">
                    <p className="lead fw-normal mb-1">ADDRESS</p>
                    <div className="p-4" style={{ backgroundColor: "#f8f9fa" }}>
                      <p className="font-italic mb-1">
                        STATE: {product?.address?.state}
                      </p>
                      <p className="font-italic mb-1">
                        CITY: {product?.address?.city}
                      </p>
                      <p className="font-italic mb-0">
                        STREET: {product?.address?.street}
                      </p>
                      <p className="font-italic mb-0">
                        ZIP CODE: {product?.address?.zipcode}
                      </p>
                    </div>
                  </div>
                  <div className="d-flex justify-content-between align-items-center mb-4">
                    <p className="lead fw-normal mb-0">Recent photos</p>
                    <p className="mb-0">
                      <a href="#!" className="text-muted">
                        Show all
                      </a>
                    </p>
                  </div>

                  <div className="row g-2">
                    {product?.photos.map((photo) => (
                      <div className="col mb-2">
                         <img
                      src={`http://localhost:8080/api/v1/properties/${product.id}/images/${product?.photos[0]?.id}`}
                          alt="image 1"
                          className="w-100 rounded-3"
                        />
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default ProductDetail;
