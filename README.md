# FitHabAndroid

To change the url where to ping the server, go to this file : 
fithabandroid\app\src\main\res\xml\network_security_config.xml and 
fithabandroid\app\src\main\java\com\example\fitnessandroid\api\restrequest\APIWrapper.kt

You need to change the BASE_URL and accepted subdomains. Can't use 127.0.0.1 as it ping the android phone instead of you computer. You can find your ip address using ipconfig in the cmd. 

VM of uquam server is : http://s-fit.info.uqam.ca/api/
