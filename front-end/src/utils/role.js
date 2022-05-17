import jwt_decode from "jwt-decode";

export const isAdmin = () => {
    return getRole()==='ADMIN'
}

export const isLandLord = () => {
    return getRole()==='ADMIN';
}

export const getRole = () => {
    return jwt_decode(JSON.parse(localStorage.getItem("token")).accessToken).authorities;;
}
