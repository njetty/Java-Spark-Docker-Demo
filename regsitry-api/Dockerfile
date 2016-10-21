FROM maven:3.3.9-jdk-8-alpine

# Update the cache
#RUN apt-get update

# Set Work Directory
WORKDIR /code

# Prepare by downloading dependencies
ADD pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /code/src

# Exposing before package because of UTCs
EXPOSE 4567
RUN ["mvn", "package", "-Dmaven.test.skip=true"]

#EXPOSE 4567
CMD ["mvn", "exec:java"]
