
## Property Management Portal
 
Property management portal is a web-based java project where house owners, and customers can exchange information effectively and inexpensively. This system provides a user-friendly interface, satisfying the needs of the consumers. 

There are three roles in this system- Landlord, Admin, and Tenant.  
 - The landlord is the user who owns the house and wants it to give it for rent. The landlord will upload all the details of the house, including the number of rooms, locality, rent. 
 - Admin manages all the users of the system. 
 - Tenant is the one who is looking for a rental house. She/He can search the house according to the requirements and get the results accordingly.

Dashboard Page for admin:
- Display the last 10 properties rented.
- Display 10 most recent tenants.
- Display total income per location.

Dashboard Page for landlords:
- Display properties.
- Display total income per location.
- Display 10 properties whose leases end in a month.

#### Functional  Requirements
--- 
* Landlords/Tenants can register to the system.
* Landlords can add properties.
* Landlords can only see their own properties and manage them.
	* Can unlist the property.
* Landlords and Tenants can filter properties:
	* by occupation.
	* by number of rooms.
	* by location.
* Tenants can filter properties:
	* by number of rooms.
	*  by location.
* Tenants can rent a property for a specific period of time by paying the amount.
	* You do not need to implement or use any payments method.
	* Refer to Extra Features. 
* Admin can manage tenants and landlords.
*  Admin can Activate/Deactivate landlords and tenants.
* Admin can reset passwords.
* Create charts for followings:
	* Total income per location. (Pie chart)
	* Number of properties rented in a week per each day. (Line Chart)
*   All delete operations should be a soft deletion.

#### Extra Features
You can pass with a Honor Grade by completing all extra features.

---
| Feature | Points |
|---------|--------|
|<font size="3"> Use Stripe for payment processing</font> | 1 point |
|<font size="3">Use OAuth 2.0 and OpenId for authorization and authentication with KeyCloak.</font> |2 points|
|<font size="3">Send notifications. Refer to notifications.</font> |1 point|
|<font size="3">Create a deployment-ready docker image.</font> |1 point|
|<font size="3">Allow users to reset thier passwords. Users should follow a password reset link. </font> |1 point|

#### Notifications
---
* PropertyRented: To the landloard when his/her property is rented.
* NewProperty: To all tenants when a new property is added to the system.

#### Sample Domain Models
---
Property: 
``` 
propertyName: String
streetAddress: String
city: City
state: State
zipCode: int
propertyType: PropertyType
numberOfBedrooms: int
numberOfBathrooms: int
rentAmount: int
securityDepositAmount: int
photos: List<String>
ownedBy : User
lastRentedBy: User
isOccupied: Boolean
```

User:
```
email: String
firstName: String
lastname: String
password: String
role: Role
LastLoggedInAt: Datetime
active: Boolean
```

Tenant:
```
rentedProperties: List<Property>
```

Landlord:
```
ownedProperties: List<Property>
```


#### Technical Details
---
* Use n-tier software architecture model.
* Use DTOs.
* PostgreSQL is recommended as a Relational Database system.
* Populate your database with dummy data using `data.sql`.


#### Submission

* Fork this repository and push your changes.
* Once you finished your project, send a Pull Request. (Send only one Pull Request once you finish the assignment.)

#### Important Notes
---

 * You are not allowed to share codes with your classmates. If detected, you will get NC.
 * **For pairs:**
	 * Individual's work will be checked from the commits.
	 *  Share tasks evenly and fairly.
	 *  To have a clearer understanding of pair programming:
		 *  > **Pair programming** is an agile software development technique in which two programmers work together at one workstation. One, the _driver_, writes code while the other, the _observer_ or _navigator_ reviews each line of code as it is typed in. The two programmers switch roles frequently. 
		 * [Wikipedia](https://en.wikipedia.org/wiki/Pair_programming#:~:text=Pair%20programming%20is%20an%20agile,two%20programmers%20switch%20roles%20frequently.)

-   Remember to respect the code honor submission policy. All written code must be original. Presenting something as one’s own work when it came from another source is plagiarism and is forbidden.
    
-   Plagiarism is a very serious thing in all American academic institutions and is guarded against vigilantly by every professor.
 

