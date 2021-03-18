# Hands-on Undo.io

This repository contains a showcase on using undo.io as a software failure replay mechanism for a Jakarta EE based microservice.

## Usage

```bash
$ ./gradlew clean ass
$ docker-compose up --build

# start the LR4J recording
$ http post localhost:8080/api/lr4

# save recording to file
$ http put localhost:8080/api/lr4/test.undo

## get recording file
$ curl localhost:8080/api/lr4/test.undo -o test.undo
$ http get localhost:8080/api/lr4/test.undo -d -o test.undo 

# delete the LR4J recording
$ http delete localhost:8080/api/lr4 
```

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE` file for details.