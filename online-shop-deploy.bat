cd ear
cd .. && mvn clean install && cd ear && mvn package glassfish:deploy
#http://localhost:8080/servlet/faces/shop.xhtml