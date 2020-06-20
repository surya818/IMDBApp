# IMDBApp
A Servlet based Web app providing Movie search on top of omdb RESTFul api

Technologies Used:
1) Java Servlet: For Web application development
2) sun-jersey: For RESTful API invocation calls
3) simple-json: For extracting data from JSON responses
</br>

The application works by making GET calls to omdbapi REST API based on the MOVIE SEARCHED on Search jsp, get the JSON response data from omdbapi server and extractig data from the response and print it in the servlet.

***To have this example running , you need to get your own omdbapi API_KEY. You can get it from http://www.omdbapi.com/




URL pattern: http://localhost:5000/ImdbApp-0.0.1-SNAPSHOT/



Steps to run the app in docker
1. Clone this repo. 
*** Please update the ImdbInfo.java file with your API Key. Look for the line below and replace the API_KEY
WebResource resource = cli.resource("http://www.omdbapi.com"+"/?t="+movie+"&apikey=<YOUR_API_KEY>");
2. change directory to project root
3. Here I have the pom file and the Dockerfile
3. If you manually want to build using maven and containerize, 
	a. Use the command "mvn install test". The target directoy is generated and has war file in it </br>
	b. docker build -t <YOUR_REPO>:imdb-1.0 .   (This will build the image with tomcat+ My code) </br>
	c. Verify the docker image created using "docker images" and capture the IMAGE_ID </br>
	d. docker container run --name imdbapp -p 5000:8080 <IMAGE_ID> </br>
	e. Voila, a container's running on port 5000 with my ImdbSearch web application </br>
	
Enjoy!!!

