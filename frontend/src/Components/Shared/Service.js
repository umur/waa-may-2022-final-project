const Service = {
  RentProperty: " http://172.19.141.27:8080/api/properties/tenant",
  TotalIncomePerLocation:
    "http://172.19.141.27:8080/api/properties/admin/totalIncome/Fairfield",
    MostRecentLandlordsUrl: "http://172.19.141.27:8080/api/users/admin/byRole/landlord",
    MostRecentTenantsUrl: "http://172.19.141.27:8080/api/users/admin/recentUsers",
    Properties: "http://172.19.141.27:8080/api/properties",
    LeasesInOneMonth: "http://172.19.141.27:8080/api/properties/landlord/leases/john@gmail.com",
    GetPropertiesOwnedBy: "http://172.19.141.27:8080/api/properties/landlord/ownedBy/john@gmail.com",
    // Get"http://172.19.141.27:8080/api/properties/landlord/leases/john@gmail.com"

//   getAPIUrl: function () {
//     return APIS;
//   },
};

export default Service;
