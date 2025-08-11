Steps to Run:
1. Open command prompt and navigate to
…/Half_Foods/SpringBootApp/angular-8-crud-app-masterUpd
2. Open Spring Tools Suite and navigate to the Spring Boot project
a. If not imported, go to top right -> import from folder/archive ->
…/Half_Foods/SpringBootApp/datajpaUpd
b. After importing, open pom.xml before running
3. Right click on datajpaUpd and select -> run as -> Spring Boot Application
4. Navigate to ../SpringBootApp/angular-8-crud-app-masterUpd
5. Run `npm install --legacy-peer-deps`
6. Run the Spring Boot project
7. Run `ng serve --open`
8. Navigate to `localhost:8080/h2-ui` (in-memory h2 database), change URL and drive to this:
<img width="1912" height="525" alt="image" src="https://github.com/user-attachments/assets/50317939-2b85-465e-a6d1-c89919bda6c3" />
9. Ensure that your angular is running on port 4200, if it is not, change CrossOrigin in FoodController.java and UserController.java to your angular port


- Angular version
- 
  ![image](https://github.com/Raxizh/Half-Foods/assets/89762945/98eff12e-f7d1-4c2b-98a2-da779507d52d)


- NodeJS version: 16.18.0
