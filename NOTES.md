## Part B - Application Modifications

1.a. Resource bundles

	src/main/resources/translation_en.properties	line 1
	src/main/resources/translation_fr.properties	line 1
---
1.b. Display the message in both English and French by applying the resource bundles using a different thread for each language.
	
	src/main/java/edu/wgu/d387_sample_code/D387SampleCodeApplication.java  lines [19-45]
	src/main/java/edu/wgu/d387_sample_code/rest/WelcomeController.java
	src/main/java/edu/wgu/d387_sample_code/convertor/WelcomeService.java
	src/main/UI/src/app/app.component.ts    Lines [30, 52, 56-68]
	src/main/UI/src/app/app.component.html  Lines [18-24]
	src/main/UI/src/app/app.component.css   Lines [939-946]
---
2\. Display the price for a reservation in currency rates for U.S. dollars (\$), Canadian dollars 
(CA\$), and euros (€)

	src/main/UI/src/app/app.component.html  Lines [86-88]
---
3.a. Convert times between ET, MT, and UTC zones

	src/main/java/edu/wgu/d387_sample_code/rest/TimeConversionController.java
	src/main/java/edu/wgu/d387_sample_code/convertor/TimeConversionService.java
---
3.b. Display a message stating the time in all three times zones

	src/main/UI/src/app/app.component.ts    Lines [31-32, 53, 70-104]
	src/main/UI/src/app/app.component.html  Lines [38-40]
	src/main/UI/src/app/app.component.css   Lines [948-952]

---

## Part C: Deployment Plan Using Docker and Cloud Services


To deploy the Spring Boot application with an Angular front end, I chose to use Docker for 
containerization and Amazon Web Services (AWS) for cloud deployment. My solution ensures that 
the entire application stack can be consistently packaged, tested, and deployed across 
different environments.

---

C1. Dockerfile Creation

I created a Dockerfile that packages the entire application into a single container image. This includes the Java back end and the Angular front end with all modifications made in parts B1 to B3.

For the back end, I copied the pre-built Spring Boot JAR file into the Docker image. The Angular front end is integrated directly into the Spring Boot application. Instead of serving the front end through a separate server or container, I used Angular's ng build command to generate static files, and output them into the src/main/resources/static directory of the Spring Boot project by running:

	ng build --output-path=../resources/static

This command was executed from inside the Angular front end directory: d387-advanced-java/src/main/UI

This allows Spring Boot to automatically serve the front end assets.

---

C2. Docker Image Testing

After creating the Dockerfile, I tested the containerization process locally:
I built the Docker image using the command:

	docker build -f Dockerfile -t d387_011800245_img .

I then ran the image in a container with:

	docker run -d -p 8080:8080 --name d387_011800245_container d387_011800245_img

I verified the container was running by executing:

	docker ps

I tested the application by accessing the endpoints via a web browser and using curl:

	curl -s http://localhost:8080/api/welcome | jq
	curl -s http://localhost:8080/api/converted-times | jq

This confirmed that:

* The /api/welcome endpoint returns translated messages in English and French.
* The /api/converted-times endpoint correctly displays time zone conversions.

Angular UI was served properly, and welcome messages, presentation times, and currency values were shown.

---

C3. Cloud Deployment

To deploy the current multithreaded Spring application to the cloud, I chose to use Amazon Web 
Services (AWS) as the cloud service provider. For this project, I deployed the Docker container 
to an Amazon EC2 instance, which gives me full control over the environment and is ideal for 
flexible deployments.

After completing all modifications to the Spring application as required in B1 through B3, 
I built a Docker image locally, tagged it for my Docker Hub account, and pushed it to the 
remote repository using the following commands:

    docker tag d387_011800245_app hamentag/d387_0hg11800245_app:latest
    docker push hamentag/d387_011800245_app:latest

This uploaded the Docker image to my public Docker Hub repository, making it accessible for 
cloud deployment.

I configured the security group for my EC2 instance to allow inbound traffic on port 8081 (TCP) 
from the appropriate IP range to make the application accessible externally.

After connecting to the EC2 instance via SSH, I pulled the Docker image from my Docker Hub repository:

    docker pull hamentag/d387_011800245_app:latest

I created and started a Docker container from the pulled image using the following command:

    docker run --name d387_011800245_cont -d -p 8081:8080 hamentag/d387_011800245_app

This command maps port 8081 of the EC2 instance (host machine) to port 8080 inside the Docker 
container where the Spring Boot application is running. Incoming traffic to port 8081 on the EC2 
instance is routed to the backend application inside the container listening on port 8080.

While the container was running, I verified that the backend services were functioning correctly 
using:

    curl -s http://localhost:8081/api/welcome | jq  
    curl -s http://localhost:8081/api/converted-times | jq

In addition, from another operating system outside the EC2 environment, I tested the same 
endpoints using the public IP of my EC2 instance:

    curl -s http://3.131.153.93:8081/api/welcome | jq  
    curl -s http://3.131.153.93:8081/api/converted-times | jq

I also accessed the application in a web browser via:

http://3.131.153.93:8081

The application loaded successfully and reflected all the changes implemented in parts B1 to B3.
All tests returned the expected results, confirming that the application is accessible 
externally and functioning as intended.
