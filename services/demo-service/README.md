# Demo Service

In order to use this demo service, you must first obtain the official LR4J recorder and replay component archives from Undo. Extract the recorder archive files into the `libs/` directory. Put the replay archive into the `replay/` directory.

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

# stop the LR4J recording
$ http delete localhost:8080/api/lr4j 

# prepare for replay
# Load the project in IntelliJ, add a LiveRecorder->Replay Run/Debug configuration,
# adjust port to 9001, add a breakpoint on DemoService.java:8

# start the software replay
$ docker exec -it `docker ps -q -f ancestor="demo-service:latest"` /opt/payara/replay/lr4j/lr4j_replay -p 9001 -i /opt/payara/replay/test.undo
```
