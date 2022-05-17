import jwt_decode from "jwt-decode";

export const isAdmin = () => {
    return getRole()==='ADMIN'
}

export const isLandLord = () => {
    return getRole()==='LANDLORD';
}

export const isTenant = () => {
    return getRole()==='TENANT';
}

export const getRole = () => {
    return jwt_decode(JSON.parse(localStorage.getItem("token")).accessToken).authorities;;
}
