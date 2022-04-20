iam-policy.json:
	@curl -o iam-policy.json https://raw.githubusercontent.com/kubernetes-sigs/aws-load-balancer-controller/main/docs/install/iam_policy.json
	@aws iam create-policy \
		--policy-name AWSLoadBalancerControllerIAMPolicy \
		--policy-document file://iam-policy.json

create-cluster: iam-policy.json
	@eksctl create cluster -f undo-eks.yaml
	@eksctl create iamserviceaccount \
		--cluster=undo-eks \
		--namespace=kube-system \
		--name=aws-load-balancer-controller \
		--attach-policy-arn=arn:aws:iam::$(AWS_ACCOUNT_ID):policy/AWSLoadBalancerControllerIAMPolicy \
		--approve

bootstrap-flux2:
	@flux bootstrap github \
		--owner=$(GITHUB_USER) \
  		--repository=hands-on-undo \
  		--branch=main \
  		--path=./kubernetes \
		--components-extra=image-reflector-controller,image-automation-controller \
		--read-write-key \
  		--personal

delete-cluster:
	@eksctl delete cluster -f undo-eks.yaml
