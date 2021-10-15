FROM openjdk:8
EXPOSE 9085
ADD target/order-service.jar order-service.jar 
ENTRYPOINT ["java","-jar","/config-service.jar"]
