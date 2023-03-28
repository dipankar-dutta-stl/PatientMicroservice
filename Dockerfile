FROM openjdk:17
EXPOSE 8002
ADD target/PatientMicroservice.jar PatientMicroserviceImg
ENTRYPOINT ["java","-jar","/PatientMicroservice.jar"]