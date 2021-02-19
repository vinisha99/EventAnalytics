# EventAnalytics for Narrative

Requirements:  
As a part of integrating with our partners, Narrative supports collecting data on website visitors and returning some basic analytics on those visitors. The goal of this task is to implement a basic endpoint for this use case. It should accept the following over HTTP:
POST /analytics?timestamp={millis_since_epoch}&user={user_id}&event={click|impression}
GET /analytics?timestamp={millis_since_epoch}

When the POST request is made, a 204 is returned to the client with an empty response. We simply side-effect and track the event in our data store.
When the GET request is made, we return information in the following format to the client, for the hour (assuming GMT time zone) of the requested timestamp:
unique_users,{number_of_unique_usernames}
clicks,{number_of_clicks}
impressions,{number_of_impressions}

It is worth noting that the traffic pattern is typical of time series data. The service will receive many more GET requests (~95%) for the current hour than for past hours (~5%). The same applies for POST requests.
Please ensure that the code in the submission is fully functional on a local machine, and include instructions for building and running it. Although it should still pass muster in code review, it is fine for the code to not be completely production ready in the submission. For example, using local storage like in-memory H2 instead of dedicated MySQL is OK. As a guide for design decisions, treat this exercise as the initial prototype of an MVP that will need to be productionalized and scaled out in the future, and be prepared for follow-up discussion on how that would look.

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
