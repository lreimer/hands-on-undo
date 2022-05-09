# Demo Instructions

```bash
# prepare the environment
# only required if you demo on a remote K8s cluster
$ export DEMO_HOST=`kubectl get service krakend-gateway -n default -o jsonpath="{.status.loadBalancer.ingress[0].hostname}"`
$ export DEMO_HOST=`kubectl get service parts-service -n demo -o jsonpath="{.status.loadBalancer.ingress[0].hostname}"`

$ export PARTS_LR4J_HOST=`kubectl get service parts-service -n lr4j -o jsonpath="{.status.loadBalancer.ingress[0].hostname}"`
$ export VEHICLE_LR4J_HOST=`kubectl get service vehicle-service -n lr4j -o jsonpath="{.status.loadBalancer.ingress[0].hostname}"`

# start the recording on LR4J parts and vehicle services
$ make start-recordings
$ make demo-calls
$ make save-recordings
$ make stop-recordings
$ make get-recordings

# prepare and run the replay images
$ make docker-lr4j-all
$ make replay-lr4j-parts
$ make replay-lr4j-vehicle

# download log files from replay containers
$ docker ps
$ docker cp <CONTAINER_ID>:/opt/undo/bridge.log bridge.log
```