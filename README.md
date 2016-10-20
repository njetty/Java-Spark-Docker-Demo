# Java-Spark-Docker-Demo
A sample test for Java Spark Web API Demo building a Docker file

Stopping the container running the service before running the latest one
docker rm $(docker stop $(docker ps -a -q --filter ancestor=njetty/registry --format="{{.ID}}"))
