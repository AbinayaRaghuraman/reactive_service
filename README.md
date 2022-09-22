# Reactive Service

## Steps to Run locally :

1. In IntelliJ, navigate through the below-mentioned path to identify Application.java class

    **src > main > java > org.example.github > Application.java**

2. Right click on Application class and select the option _**Run 'Application.main()'**_


## Swagger :

1. Once the Application is started and running, access the swagger UI by launching the URL in a new browser window http://localhost:8081/swagger-ui.html

## Steps to run as docker container :

1. From Terminal, navigate to the project path where Dockerfile is present
2. Use the command `sudo docker build -t spring-flux:1.0 .`
   
   The above command will create a new docker image with name spring-flux & tag 1.0. If you have docker desktop installed, you can verify the image creation under images.
3. Once the image is created, Use the command `sudo docker run -d -p 8082:8081 -t spring-flux:1.0`

   The above command will create a new docker container with the previously created image. The port 8082 is mapped to access outside the container.
4. Once the container is running, access the swagger UI by launching the URL in a new browser window http://localhost:8082/swagger-ui.html