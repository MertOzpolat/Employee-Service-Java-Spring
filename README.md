# employee-service

## Prerequisites

You will need the following things properly installed on your computer.

* [Git](https://git-scm.com/)
* [JDK/JRE 1.8](https://www.oracle.com/tr/java/technologies/javase/javase8-archive-downloads.html)
* [MySQL](https://dev.mysql.com/downloads/mysql/)
* [Postman](https://www.postman.com/downloads/)

### Prerequisites for MySQL

You will need to create a MySQL database with the default parameters below ;

Database name : `ebftestdb`<br />
Database user : `root`<br />
Database password : `test`<br />

or you can change parameters from <br />
`Employee-Service/src/main/resources/application.properties`

### Testing the backend with Postman --Optional
* Install postman
* File/import or Ctrl-O `Employee-Service/EBF-Demo.postman_collection.json`
* If you see EBF-Demo collection under the collection section it's done.

## Installation

* `git clone https://github.com/MertOzpolat/employee-service` this repository

## Running / Development

* `./gradlew clean test jar`
* `cd build/libs`
* `java -jar Employee-0.0.1-SNAPSHOT.jar`

## Using Employee-Service

You can use prepared Postman options or you can simply add yours.

### Add Company
To add a new company, send PUT request to companies endpoint with json body.<br /> 
[PUT] localhost:8080/companies/ <br />JSON body should be: <br /> `{"name":"EBF"}`

### Update Company
To update a company, send POST request to companies endpoint with json body.<br />
[POST] localhost:8080/companies/ <br />JSON body should be: <br />`{"id":1,"name":"EBFUpdatedName"}`

### Get Company List
To get the list of all companies, send GET request to companies endpoint <br/>
[GET] localhost:8080/companies <br />
Optionally to get the list of companies via paging, send GET request to companies endpoint below example can be used<br/>
[GET] localhost:8080/companies?page=0&size=10

### Add Employee
To add a new employee, send PUT request to employees endpoint with json body.<br />
[PUT] localhost:8080/employees <br />JSON body should be: <br /> `{"name" :"EmployeeName","surname":"EmployeeSurname","email" : "mertozpolatt@gmail.com","salary" : 1000,"company" : {"id":"1","name":"EBFUpdatedName"}}`

### Update Employee
To update an employee, send POST request to employees endpoint with json body.<br />
[POST] localhost:8080/employees <br />JSON body should be: <br /> `{"id":1,"name":"NameUpdated","surname":"EmployeeSurname","email":"mertozpolatt@gmail.com","salary":1000,"company":{"id":"1","name":"EBFUpdatedName"}}`

### Get Employees Of Company
To get the list of employees of a company, send GET request to employees endpoint with PathVariable companyId.<br />
[GET] localhost:8080/employees/1 <br /> `PathVariable 1 is companyId`

### Get Average Salary
To get the average salary of a company, send GET request to companies/averageSalary endpoint with PathVariable companyId. <br />
[GET] localhost:8080/companies/averageSalary/1 <br /> `PathVariable 1 is companyId`

### Delete Employee
To delete an employee, send DELETE request to employees endpoint with PathVariable employeeId. <br />
[DELETE] localhost:8080/employees/1 <br /> `PathVariable 1 is employeeId`

### Delete Company
To delete** a company, send DELETE request to companies endpoint with PathVariable companyId. <br />
[DELETE] localhost:8080/companies/1 <br /> `PathVariable 1 is companyId` <br />
** Deleting a company also deletes all employees of the related company