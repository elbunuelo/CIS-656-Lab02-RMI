#! /bin/bash

rm -rf src/**/*.class
rm -rf src/compute.jar
rm -f public_html/classes/client/*.class
cd src
javac compute/Compute.java compute/Task.java
jar cvf compute.jar compute/*.class
cp compute.jar ../public_html/classes
javac -cp ../public_html/classes/compute.jar engine/ComputeEngine.java
javac -cp ../public_html/classes/compute.jar client/ComputePi.java client/Pi.java client/Primes.java
cp client/Pi.class ../public_html/classes/client
cp client/Primes.class ../public_html/classes/client

cd ..
java -cp src:src/compute.jar -Djava.rmi.server.codebase=http://localhost/classes -Djava.security.policy=client.policy -Djava.rmi.server.useCodebaseOnly=false client.ComputePi localhost
