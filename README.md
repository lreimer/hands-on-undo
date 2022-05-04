# Hands-on Undo.io

This repository contains a showcase on using undo.io as a software failure replay mechanism for a Jakarta EE and Quarkus based microservices deployed to AWS EKS.

## Prerequisites

You need to have the following tools installed locally to be able to complete all steps:
- [AWS CLI v2](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html)
- [eksctl](https://eksctl.io/)
- [kubectl](https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html)
- [flux](https://fluxcd.io/docs/get-started/)
- [Helm](https://helm.sh/docs/intro/install/)

## Local Installation

```bash
cd services/
tilt up
```

## Bootstrapping

```bash
# define required ENV variables for the next steps to work
$ export AWS_ACCOUNT_ID=`aws sts get-caller-identity --query Account --output text`
$ export GITHUB_USER=<your-username>
$ export GITHUB_TOKEN=<your-token>

# setup demo cluster with Flux2
$ make create-demo-cluster
$ make bootstrap-demo-flux2
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