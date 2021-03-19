# Hands-on Undo.io

This repository contains a showcase on using undo.io as a software failure replay mechanism for a Jakarta EE based
microservice. The service allows to turn the failure recording on and off via a REST API and also to save and retrieve
the recording. For demo purposes it also provides an endpoint to trigger business logic errors.

## Usage

```bash
# NOTE: make sure to have the lr4j record ZIP content in the libs/ directory
# build without or with tests
$ ./gradlew clean ass
$ ./gradlew clean build -x test

# create docker image and run it
$ docker-compose up --build

# start the LR4J recording
$ http post localhost:8080/api/lr4j

# do some calls
$ http get localhost:8080/api/demo name==Test
$ http get localhost:8080/api/demo

# save recording to file
$ http put localhost:8080/api/lr4j/test.undo

# get recording file
$ curl localhost:8080/api/lr4j/test.undo -o replay/test.undo
$ http get localhost:8080/api/lr4j/test.undo -d -o replay/test.undo 

# delete the LR4J recording
$ http delete localhost:8080/api/lr4j 

# start the software replay
# NOTE: make sure to have the lr4j replay ZIP in the replay/ directory
$ cd replay
$ docker build -t hands-on-undo:replay .
$ docker run --rm -it -p 9000:9000 hands-on-undo:replay
```

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE` file for details.