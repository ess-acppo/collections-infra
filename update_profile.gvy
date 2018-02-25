node {
  
   stage("Deploy") {
      dir('nsl-infra'){
              #def extra_vars = /'{"nxl_env_name":"$ENVIRONMENT_NAME","apps":[{"app": "services"}], "war_names": [{"war_name": "nxl#services##1.0123"}   ],   "war_source_dir": "$warDir"}'/
              sh "sed -ie 's/.*instance_filters = tag:env=.*\$/instance_filters = tag:env=$ENVIRONMENT_NAME/g' aws_utils/ec2.ini && ansible-playbook  -i aws_utils/ec2.py -u ubuntu playbooks/deploy.yml -e $extra_vars --extra-vars $shard_vars"
     }


   }
}
