# selenuim-docker-aws-ec2

### Setup Instructions : 
 ####  Create an AWS EC-2 instance : 
 Pick up a linux machine where docker is already available, Security Groups : Add extra rule for “Custom TCP Rule” : port range : 4444, anywhere
 ####  How to access ec2 instance from terminal :   
1.  Open terminal - Go to the folder where .pem file (private key pair) is placed
2. Give permission to this file :  chmod 400 my-key-pair.pem
3. ssh -i /path/my-key-pair.pem ec2-user@public_dns_name 
(where public_dns_name = Public DNS (IPv4) from description or IPv4 Public IP

#### How to uninstall java (old version)  and install JDK1.8 in Linux ec2 instance : 

1.  sudo yum update -y
2. sudo yum remove java -y
3. sudo yum install java-1.8.0-openjdk -y

#### Create docker-compose file : 

1. Create a file : touch docker-compose.yml
2. Open this file : vi docker-compose.yml
3. Copy paste docker compose file content from 
https://github.com/SeleniumHQ/docker-selen
4. Execute this compose file : docker-compose up -d


#### To access from local browser : 
publicIP:4444/grid/hub 
("publicIP" is available in instance description section from aws instance)

#### How to scale up specific browser instances : 
To scale up browser containers (total 5 chrome browsers) : docker-compose scale chrome=5

#### How to run tests on ec-2 instance : 
1. Run "mvn clean install" from project root directory
2. Tests will run on ec2 instance browsers, browser will be greyed out (in local grid console)

#### How to shutdown the whole infrastructure:
1. Run from terminal : docker-compose down
2. Terminate the instance from aws instances

#### Contact Info: [LinkedIn](https://www.linkedin.com/in/surendra-singh-db/)





