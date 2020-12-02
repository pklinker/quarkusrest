# Quarkus sample

## Compile and run
To build the application using Maven run:  
```./mvnw clean install```  
```./mvn quarkus:dev```   
Install a C compiler and build the native executable:

```./mvnw package -Pnative```  
  
The application has to bootstrap the database by loading the data. Copy the files from src/main/resources/data into /opt/data. If you are unable to write to /opt/data, you will need to modify the code in CountryInfoApplication. After copying the data files into /opt/data you may run the program, a self-executing jar, as follows:

```target/capitolinfo-0.0.4-SNAPSHOT-runner```  
  
Navigate to http://localhost:5500/getCapitol/{name}, replacing {name} with a country name to get the capitol of that country. To add a new capital, e.g. a state or province, call http://localhost:5500/addCapitol?name={name}&capitol={capitol}&type={type} where name is the name of the country/state/province, capitol is the capitol name, and type is the capitol type, e.g. state.

# Docker
This project contains a Dockerfile to build a containerized version of this application.

## Build
To build the container image and tag it as latest use:  
```docker build -t capinfo-quarkus:latest .```

## Run the container
Create a network:  
```docker network create mybridge```  

To run the container in Docker, expose port 5500:  
```docker run --network=mybridge -p 5500:5500 --name capinfo-quarkus capinfo-quarkus:latest```

## Publish image
Push the image to Docker Hub or another repository accessible by Kubernetes. For example:  
```docker tag capinfo-quarkus:latest <docker-account>/capinfo-quarkus:latest```  
```docker push <docker_account>/capinfo-quarkus:latest```
  
# Kubernetes and Istio
This project contains configurations to enable it to run in an Istio service mesh on to of Kubernetes. It has been tested with Docker Desktop bundled with Kubernetes.
## Running in K8s
To run the example in Kubernetes, apply the configuration in the capinfo-quarkus-k8s.yaml configuration file:  
```kubectl apply -f capinfo-quarkus-k8s.yaml```  

## Troubleshooting
If you get the infamous  ImagePullBackOff status when running "kubectl get pods", you can troubleshoot by running the describe command:   
```kubectl describe pod <NAME>```  

## Visualizing
You can use the Kiali dashboard to visualize Istio. Run it as follows:  
```istioctl dashboard kiali``` 

## Log into K8s container:
kubectl exec -it $(kubectl get pod -l app=capinfo-quarkus -o jsonpath='{.items[0].metadata.name}' -n default) /bin/bash

## Cleanup
To cleanup the K8s deployment run:  
```kubectl delete -f capinfo-quarkus-k8s.yaml```   

# Quarkus
Set up path:  
export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.1.0/Contents/Home
export PATH=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.1.0/Contents/Home/bin:"$PATH"
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.1.0/Contents/Home

${GRAALVM_HOME}/bin/gu install native-image

## Forward for K8s
kubectl port-forward pods/capinfo-quarkus-765d459796-258hz 7000:6379