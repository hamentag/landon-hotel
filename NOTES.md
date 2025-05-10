## Part B - Application Modifications

1.a. Resource bundles

	src/main/resources/translation_en.properties	line 1
	src/main/resources/translation_fr.properties	line 1

1.b. Display the message in both English and French by applying the resource bundles using a different thread for each language.
	
	src/main/java/edu/wgu/d387_sample_code/D387SampleCodeApplication.java  lines [19-45]
	src/main/java/edu/wgu/d387_sample_code/rest/WelcomeController.java
	src/main/java/edu/wgu/d387_sample_code/convertor/WelcomeService.java
	src/main/UI/src/app/app.component.ts    Lines [30, 52, 56-68]
	src/main/UI/src/app/app.component.html  Lines [18-24]
	src/main/UI/src/app/app.component.css   Lines [939-946]

2\. Display the price for a reservation in currency rates for U.S. dollars (\$), Canadian dollars (C\$), and euros (€)

	src/main/UI/src/app/app.component.html  Lines [86-88]

3.a. Convert times between ET, MT, and UTC zones

	src/main/java/edu/wgu/d387_sample_code/rest/TimeConversionController.java
	src/main/java/edu/wgu/d387_sample_code/convertor/TimeConversionService.java

3.b. Display a message stating the time in all three times zones

	src/main/UI/src/app/app.component.ts    Lines [31-32, 53, 70-104]
	src/main/UI/src/app/app.component.html  Lines [38-40]
	src/main/UI/src/app/app.component.css   Lines [948-952]

## Part C: Deployment Plan Using Docker and Cloud Services


To deploy the Spring Boot application with an Angular front end, I chose to use Docker for containerization and Amazon Web Services (AWS) for cloud deployment. My solution ensures that the entire application stack can be consistently packaged, tested, and deployed across different environments.


C1. Dockerfile Creation

I created a Dockerfile that packages the entire application into a single container image. This includes the Java back end and the Angular front end with all modifications made in parts B1 to B3.

For the back end, I copied the pre-built Spring Boot JAR file into the Docker image. The Angular front end is integrated directly into the Spring Boot application. Instead of serving the front end through a separate server or container, I used Angular's ng build command to generate static files, and output them into the src/main/resources/static directory of the Spring Boot project by running:

	ng build --output-path=../resources/static

This command was executed from inside the Angular front end directory: d387-advanced-java/src/main/UI

This allows Spring Boot to automatically serve the front end assets.

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

