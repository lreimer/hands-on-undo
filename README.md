# Hands-on Undo.io

This repository contains a showcase on using undo.io as a software failure replay mechanism for a Jakarta EE based
microservice. The service allows to turn the failure recording on and off via a REST API and also to save and retrieve
the recording. For demo purposes it also provides an endpoint to trigger business logic errors.

## Usage

In order to use this demo, you must first obtain the official LR4J recorder and replay component archives from Undo.
Extract the recorder archive files into the `libs/` directory. Put the replay archive into the `replay/` directory.

```bash
# NOTE: make sure to have the lr4j record ZIP content in the libs/ directory and the replay ZIP
# content in the replay/ directory
# build without or with tests
$ ./gradlew clean ass
$ ./gradlew clean build -x test

# create docker image and run it
$ docker-compose up --build

# start the LR4J recording
$ http post localhost:8080/api/lr4j

# do some REST calls
$ http get localhost:8080/api/demo name==Test1
$ http get localhost:8080/api/demo name==Test2

# this call will produce a NullPointerException
$ http get localhost:8080/api/demo

# save recording to file
$ http put localhost:8080/api/lr4j/test.undo

# get recording file
$ curl localhost:8080/api/lr4j/test.undo -o replay/test.undo
$ http get localhost:8080/api/lr4j/test.undo -d -o replay/test.undo 

# delete the LR4J recording
$ http delete localhost:8080/api/lr4j 

# prepare for replay
# Load the project in IntelliJ, add a LiveRecorder->Replay Run/Debug configuration,
# adjust port to 9001, add a breakpoint on DemoService.java:8

# start the software replay
$ docker exec -it `docker ps -q -f ancestor="hands-on-undo:1.0"` /opt/payara/replay/lr4j/lr4j_replay -p 9001 -i /opt/payara/lr4j/test.undo
```

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE` file for details.