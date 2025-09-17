# Basic commands

### if change in Pom.xml file 
use mvn clean install

### To run use-
mvn test


### To run with Suite, use - 
mvn test -DsuiteXmlFile=<your/xml/suite/file/path>
like -
mvn test -DsuiteXmlFile=src/test/resources/suiteFile/debug2.xml


### To run with report generation
mvn verify 
or
mvn verify -DsuiteXmlFile=src/test/resources/suiteFile/debug2.xml
#### --then find html file in /target/site/sure-fire.html
