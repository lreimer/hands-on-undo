# Hands-on Undo.io

This repository contains a showcase on using undo.io as a software failure replay mechanism for a Jakarta EE based
microservice. The service allows to turn the failure recording on and off via a REST API and also to save and retrieve
the recording. For demo purposes it also provides an endpoint to trigger business logic errors.

## Usage

```bash
$ ./gradlew clean ass
$ docker-compose up --build

# start the LR4J recording
$ http post localhost:8080/api/lr4j

$ http get localhost:8080/api/demo name==Test
$ http get localhost:8080/api/demo

# save recording to file
$ http put localhost:8080/api/lr4j/test.undo

# get recording file
$ curl localhost:8080/api/lr4j/test.undo -o test.undo
$ http get localhost:8080/api/lr4j/test.undo -d -o test.undo 

# delete the LR4J recording
$ http delete localhost:8080/api/lr4j 
```

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE` file for details.