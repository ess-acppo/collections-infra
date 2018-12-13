## Collections-Infra Repository 

*This repository deals with setup of RoSCo infrastructure on AWS or Vagrant. For AWS it uses a Golden Image and spins up a new server with all the required software and configuration*

**Technologies Used:**

1. [AWS](https://aws.amazon.com/)
1. [Ansible](https://www.ansible.com/)
1. [Vagrant](https://www.vagrantup.com/)
1. [OpenJDK 1.7.55](https://openjdk.java.net/)
1. [MySQL](https://www.mysql.com/)
1. [Apache 2.0](https://httpd.apache.org/)
1. [Collective Access](https://www.collectiveaccess.org/)

**Setup:**

1. Install mySQL database
1. Copy the contents of the CollectiveAccess software distribution to the root of the web server instance, like apache in our case
1. This can be done using git clone as well
    * git clone https://github.com/collectiveaccess/providence.git rosco
1. Copy the setup.php-dist file (in the root directory of the CA distribution) to a file named setup.php. Edit setup.php, changing the various directory paths and database login parameters to reflect your server setup.
1. Setup proper permissions on the app directory
1. Navigate to the URL or IP (If no DNS is setup) and follow on-screen prompts for installation

**Instance Deployment on AWS:**

1. The following ansible command will deploy a default set of war files and corresponding configuration into tomcat ```sed -ie \'s/.*instance_filters = tag:env=.*$/instance_filters = tag:env=$ENVIRONMENT_NAME/g\' aws_utils/ec2.ini && ansible-playbook -i aws_utils/ec2.py -u ubuntu  playbooks/infra.yml

**Running Through Jenkins on Linux Server:**

1. Setup a new Job as a Pipeline job and give it a name
1. Add parameters denoted in all caps in the playbook to the job
1. Pipline code is in SCM -> Git
1. The location of the Jenkins file is `jenkins/aws_infra.gvy`

**Questions:**

*Please contact us via github for any queries or if you like to reuse the code*