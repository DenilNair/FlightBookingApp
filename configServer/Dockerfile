FROM openjdk:8
EXPOSE 9085
ADD target/config-service.jar config-service.jar 
ENTRYPOINT ["java","-jar","/config-service.jar"]
