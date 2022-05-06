# Hands-on Undo.io

This repository contains a showcase on using undo.io as a software failure replay mechanism for a Jakarta EE and Quarkus based microservices deployed to AWS EKS.

## Prerequisites

You need to have the following tools installed locally to be able to complete all steps:
- [AWS CLI v2](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html)
- [eksctl](https://eksctl.io/)
- [kubectl](https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html)
- [flux](https://fluxcd.io/docs/get-started/)
- [Helm](https://helm.sh/docs/intro/install/)

## Demo

The demo can be run either locally or remotely in the AWS cloud. Make sure to
follow the below instructions on how to install the demo for you environment.
Then you can start with your reverse debugging session.

```bash
# follow instructions for local or AWS installation
$ cd service/

# (optional) in case of AWS use the following commands
$ export VEHICLE_LR4J_HOST=`kubectl get service vehicle-service -n lr4j -o jsonpath="{.status.loadBalancer.ingress[0].hostname}"`
$ export PARTS_LR4J_HOST=`kubectl get service parts-service -n lr4j -o jsonpath="{.status.loadBalancer.ingress[0].hostname}"`
$ export DEMO_HOST=`kubectl get service krakend-gateway -n default -o jsonpath="{.status.loadBalancer.ingress[0].hostname}"`

# you can also use make demo-full
$ make start-recording 
$ make demo-calls 
$ make save-recordings 
$ make get-recordings 
$ make stop-recordings

# prepare the LR4j replay (or make docker-lr4j-all)
$ make docker-lr4j-base
$ make docker-lr4j-parts
$ make docker-lr4j-vehicle

# start IntelliJ with LR4J plugin
$ docker run -it -p 9000:9000 lreimer/lr4j-replay:parts
$ docker run -it -p 9001:9000 lreimer/lr4j-replay:vehicle
```

## Local Installation

```bash
$ cd services/
$ tilt up
```

## AWS Installation

```bash
$ cd kubernetes/

# define required ENV variables for the next steps to work
$ export AWS_ACCOUNT_ID=`aws sts get-caller-identity --query Account --output text`
$ export GITHUB_USER=<your-username>
$ export GITHUB_TOKEN=<your-token>

# setup demo cluster with Flux2
$ make create-demo-cluster
$ make create-demo-iam
$ make create-demo-flux2
$ git pull

# setup replay cluste with Flux2 in same VPC
$ aws ec2 describe-vpcs --filters "Name=isDefault,Values=false"
# take vpc-id from previous output as filter Value
$ aws ec2 describe-subnets --filters "Name=vpc-id,Values=vpc-0545b3d93b368121b"
# fill in the VPC and subnet IDs into the replay cluster YAML

# setup lr4j cluster with Flux2
$ make create-lr4j-cluster
$ make create-lr4j-iam
$ make create-lr4j-flux2
$ git pull

# modify Flux System kustomization YAML and add (with Git add and push)
# - cluster-sync.yaml
$ flux reconcile source git flux-system
$ flux reconcile kustomization flux-system
$ flux get all

$ make delete-clusters
```

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE` file for details.