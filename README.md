## Installation Steps:

Prerequisites:
* JDK 1.8 or later
* Java 11
* Maven 3.2+  [brew install maven] (Mac)
* MySQL database [brew install mysql] (Mac)

1. Launch Terminal
2. Clone Repository from GitHub
> git clone https://github.com/vinisha99/EventAnalytics.git

3. Go inside the Project folder and copy contents of `src/main/resources/schema-mysql.sql` file.

4. Connect to mysql cli on the terminal using command:   
> mysql.server start  
> mysql -u root -p
(Enter your default password of root user)  

5. Paste contents from `src/main/resources/schema-mysql.sql` file and hit enter.
6. `Exit` mysql cli

7. Make sure you are in the `EventAnalytics` folder.
8. Launch application using command:
> ./mvnw spring-boot:run  

APIs should now be working !!!

## Sample API URLs

POST : http://localhost:8080/analytics?user=kv90&event=click&timestamp=1613685486860

GET : http://localhost:8080/analytics?timestamp=1613685486860
