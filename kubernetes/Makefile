iam-policy.json:
	@curl -o iam-policy.json https://raw.githubusercontent.com/kubernetes-sigs/aws-load-balancer-controller/main/docs/install/iam_policy.json
	@aws iam create-policy \
		--policy-name AWSLoadBalancerControllerIAMPolicy \
		--policy-document file://iam-policy.json

efs-policy.json:
	@curl -o efs-policy.json https://raw.githubusercontent.com/kubernetes-sigs/aws-efs-csi-driver/v1.3.2/docs/iam-policy-example.json
	@aws iam create-policy \
    	--policy-name AmazonEKS_EFS_CSI_Driver_Policy \
    	--policy-document file://efs-policy.json

create-cluster: iam-policy.json efs-policy.json
	@eksctl create cluster -f undo-eks.yaml
	@eksctl create iamserviceaccount \
		--cluster=undo-eks \
		--namespace=kube-system \
		--name=aws-load-balancer-controller \
		--attach-policy-arn=arn:aws:iam::$(AWS_ACCOUNT_ID):policy/AWSLoadBalancerControllerIAMPolicy \
		--approve \
		--override-existing-serviceaccounts \
    	--region eu-central-1
	@eksctl create iamserviceaccount \
		--cluster=undo-eks \
		--namespace=kube-system \
		--name=efs-csi-controller-sa \
		--attach-policy-arn=arn:aws:iam::$(AWS_ACCOUNT_ID):policy/AmazonEKS_EFS_CSI_Driver_Policy \
		--approve \
		--override-existing-serviceaccounts \
    	--region eu-central-1

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
