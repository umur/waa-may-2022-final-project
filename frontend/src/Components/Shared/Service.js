const Service = {
  RentProperty: " http://172.19.141.15:8080/api/properties/tenant",
  TotalIncomePerLocation:
    "http://172.19.141.15:8080/api/properties/admin/totalIncome/Fairfield",
  MostRecentLandlordsUrl:
    "http://172.19.141.15:8080/api/users/admin/byRole/landlord",
  MostRecentTenantsUrl: "http://172.19.141.15:8080/api/users/admin/recentUsers",
  Properties: "http://172.19.141.15:8080/api/properties",
  LeasesInOneMonth:
    "http://172.19.141.15:8080/api/properties/landlord/leases/john@gmail.com",
  GetPropertiesOwnedBy:
    "http://172.19.141.15:8080/api/properties/landlord/ownedBy/john@gmail.com",
  GetPropertyType: "http://172.19.141.15:8080/api/properties/propertyTypes",
  AddProperty: "http://172.19.141.15:8080/api/properties/landlord",
  TenantGetAllProperties: "http://172.19.141.15:8080/api/properties",
  TenantRentProperty: "http://172.19.141.15:8080/api/properties/tenant",
  TenantFilterByCity:
    "http://172.19.141.15:8080/api/properties/tenant/filterByCity/",
  LandlordFilterByCity:
    "http://172.19.141.15:8080/api/properties/landlord/ownedBy/john@gmail.com/",
  SignUp: "http://172.19.141.15:8080/api/users/signup",
  DeleteProperty: "http://172.19.141.15:8080/api/properties/landlord/"
};

export default Service;
