  ###  Launch and Configure EC2 instance
  
  * Open your AWS console and search EC2.
* After that go to instances section and launch an EC2 instance.
* Your EC2 instance should be launched with following specifications. 
    * 1. Ubuntu 18 
    * 2. Auto-Assign Public IP: enable 
    * 3. TCP connection on Port 3000
* You will be asked to create a key pair, after creating store it somewhere safe on your device as it will be needed later.


### Connecting to EC2 instance from your device 
* For this you need a powershell/cmd with ssh support or you can download git bash which already comes with ssh support.

* Open your chosen CLI in the folder where you saved the key pair.

* Now open AWS instances and click on connect after selecting your running EC2 instance.

* Now click on ssh client, follow the instructions and copy the command given, it must be something like this.

      ssh -i <key-pair-filename> username@<aws-dns-link>

* Paste the command in your CLI and you will enter your EC2 instance.


### Setup Nodejs Environment

* https://docs.aws.amazon.com/sdk-for-javascript/v2/developer-guide/setting-up-node-on-ec2-instance.html

* This will update upto now in ubuntu server 

       sudo apt-get update  
     
* After update Close console
* Once again connect to instance
* Get a message in terminal All the update are getting successfully
* This will helps to setup node js environment
        
        sudo apt-get install curl
        
        curl -sL https://deb.nodesource.com/setup_14.x | sudo -E bash -
        
* This is the command install node js inside ubuntu server

        sudo apt-get install -y nodejs 
       
* To get nodejs and npm with that
* Check installed node and npm using 
       
       node -v 
       npm -v
       
       
 ### Running React Project
 
* Clone your react project from github using following command.

      git clone <github project link>

* Navigate inside your project folder and type following commands one by one.

      npm install

      npm start

* Your project must be running on port 3000, you need to copy public IP DNS address of your EC2 instance and type following in browser.

      <Public IP DNS address>:3000

