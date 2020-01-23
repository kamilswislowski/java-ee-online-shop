cd C:\Users\Kamil\Workspace\IntelJ\java-ee-online-shop\ear
cd .. && mvn clean install && cd ear && mvn package glassfish:redeploy
#http://localhost:8080/servlet/faces/shop.xhtml