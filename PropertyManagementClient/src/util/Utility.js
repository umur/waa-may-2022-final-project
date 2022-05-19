import jwtDecode from "jwt-decode";

export const isAdmin = () => {
    return getRole()=='ADMIN'
}

export const isLandLord = () => {
    return getRole()=='LANDLORD';
}

export const isTenant = () => {
    return getRole()=='TENANT';
}

export const getRole = () => {
    const token = localStorage.getItem('urole');
    return token;
}

export const getUsername = () => {
    return jwtDecode(JSON.parse(localStorage.getItem('fullToken')).accessToken).username;
}

export const getBearer = () => {
    return localStorage.getItem('token')
}