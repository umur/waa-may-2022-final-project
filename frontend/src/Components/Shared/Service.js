const Service = {
  RentProperty: " http://localhost:8080/api/properties/tenant",
  TotalIncomePerLocation:
    "http://localhost:8080/api/properties/admin/totalIncome/Fairfield",
  MostRecentLandlordsUrl:
    "http://localhost:8080/api/users/admin/byRole/landlord",
  MostRecentTenantsUrl: "http://localhost:8080/api/users/admin/recentUsers",
  Properties: "http://localhost:8080/api/properties",
  LeasesInOneMonth:
    "http://localhost:8080/api/properties/landlord/leases/john@gmail.com",
  GetPropertiesOwnedBy:
    "http://localhost:8080/api/properties/landlord/ownedBy/john@gmail.com",
  GetPropertyType: "http://localhost:8080/api/properties/propertyTypes",
  AddProperty: "http://localhost:8080/api/properties/landlord",
  TenantGetAllProperties: "http://localhost:8080/api/properties",
  TenantRentProperty: "http://localhost:8080/api/properties/tenant",
  TenantFilterByCity:
    "http://localhost:8080/api/properties/tenant/filterByCity/",
  LandlordFilterByCity:
    "http://localhost:8080/api/properties/landlord/ownedBy/john@gmail.com/Fairfield",
  SignUp: "http://localhost:8080/api/users/signup"
};

export default Service;
