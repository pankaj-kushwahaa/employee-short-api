----------------------------------------- How to Set up Database :----------------------------------------
1. Create a new Database: employees
2. Open application.properties file which is present in src/main/resource/application.properties.
3. Change the username:
4. Change password:

----------------------------------------- API Routes ------------------------------------------------------------
POST :--- http://localhost:8080/employees
PUT :--- http://localhost:8080/employees/empId
DELETE :---http://localhost:8080/employees/empId
GET :--- http://localhost:8080/employees
GET :-- http://localhost:8080/employees/employees/{empId}/manager/{level}

------------------------------------------ How to use ----------------------------------------------------------
Open POSTMAN and test API 
Access origin is set to same-origin.
we can change origins when we use this api with frontend project.

----------------------
Add Employees: POST :--- http://localhost:8080/employees
Examples:--
First Employee so, reportsTo = null
{
  "employeeName": "admin",
  "phoneNumber": "7777777777",
  "email": "admin@gmail.com",
  "reportsTo": null,
  "profileImage": "profile.jpg"
}

Second employee: 
{
  "employeeName": "raju",
  "phoneNumber": "88888888888",
  "email": "raju@gmail.com",
  "reportsTo": "",
  "profileImage": "profile.jpg"
}

---------------------------
Update Employees: PUT :--- http://localhost:8080/employees/b1535d03-724f-44bd-82e5-97e60577d273
{
  "employeeName": "raju",
  "phoneNumber": "88888888888", // Change values
  "email": "raju@gmail.com",
  "reportsTo": "d1555d03-724f-44bd-82e5-97e60577dfid",
  "profileImage": "profile.jpg"
}

-----------------------
DELETE :---http://localhost:8080/employees/b1535d03-724f-44bd-82e5-97e60577d273
and send DELETE request

-----------------------
GET All Employees
GET :--- http://localhost:8080/employees
we get list of employees

------------------------------------------ Intermediate -------------------------------
GET Employee manager levels

GET :---  For first level Manager or simple employee manager.
http://localhost:8080/employees/employees/b1535d03-724f-44bd-82e5-97e60577d273/manager/1

Second level Manager or Employee manager's Manager :--
http://localhost:8080/employees/employees/b1535d03-724f-44bd-82e5-97e60577d273/manager/2

Third level Manager or Employee manager's manager's Manager 
http://localhost:8080/employees/employees/b1535d03-724f-44bd-82e5-97e60577d273/manager/3



Manager Highest level -- user which has reportsTo property set to null.



