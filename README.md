# BPDTS-TEST

BPDTS-TEST is a Rest Assured based API Testing module which will tests the APIs found here : http://bpdts-test-app-v2.herokuapp.com
Typical Requirements
  - Verify each APIs for valid and Invalid input options.
  - Verify Schema of the returned JSON Data.

Future Versions: If access to the Backend DBs are available, a more robust framework can be developed with valid dynamic test data.

BPDTS-TEST has been developed using the following tools/apps:

* [Java] - Core Java 
* [JUnit] - Java Unit Testing framework v4.13
* [IntelliJ] - IDE
* [Rest Assured] - Rest Assured Libraries v 4.3.1

And of course BPDTS-TEST itself is open source on on GitHub.

### Installation

Use Maven to build BPDTS-TEST.  the pom file can be found along with the source.
Typical Maven commands:
-- mvn test -PBpdtsAll
