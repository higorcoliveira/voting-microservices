### About this project
This project is a demo that contains three small microservices with fault tolerance 
provided by Resillience4j and service discovery provided by Consul.

### Consul
After install Consul, execute the following command (on a Linux machine):

* nohup consul agent -server=true -bootstrap=true -ui -client=0.0.0.0 -bind=192.168.0.23 -data-dir=/tmp/consul &

It's necessary to change the IP address to your local IP.

### Spring Boot
To start Spring Boot, you gonna need:

* Java 11
* IntelliJ Community Edition 2020 (recommended)
* Follow this to enable lombok https://stackoverflow.com/questions/53866929/unable-to-use-lombok-with-java-11

### Microservices
## Vote
* Start using the runner or your IDE

## Red
* Start using the runner or your IDE

## Blue
* Start using the runner or your IDE