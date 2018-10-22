/*
Perquisites before this can be run in a machine ( irrespective of whether it run using Jenkins):
1. Install boto
2. Configure AWS KEYS
3. Configure ssh to enable shh thorugh bastion
*/


stage("Creating environment") {
node{
checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'collections-infra']], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/ess-acppo/collections-infra.git']]])
 dir('collections-infra'){    
    def extra_vars = /'{"env_name":"$ENVIRONMENT_NAME","ami_id": "$AMI_ID", "VPC_ID": "$VPC_ID","public_subnet_cidr" : "$public_subnet_cidr", "public_subnet2_cidr" : "$public_subnet2_cidr", "private_subnet_cidr": "$private_subnet_cidr", "BAS_HOST_SG": "$BAS_HOST_SG"}'/
    sh "ansible-playbook -vvv playbooks/infra.yml  -e $extra_vars"
 }
}
}
