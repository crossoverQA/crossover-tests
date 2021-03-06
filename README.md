**# This file contains all project details: (setup and configuration)**

I use following technology stack for development:
Java, JUnit, Maven, Cucumber (versions are mentioned in POM.xml)
 
 **prerequisites**
 IDE, JDK1.7, Maven, Browser (Chrome, Firefox, IE)
 
**Dependencies :**

selenium-java (2.53.0)
cucumber-java (1.2.5)
cucumber-spring (1.2.5)
cucumber-junit (1.2.5)
commons-lang3 (3.5)
spring-context (4.2.4.RELEASE)
logback-classic (1.0.0)

**plugins:**
maven-compiler-plugin (3.3)
properties-maven-plugin (1.0.2)
maven-surefire-plugin (2.18.1)

**Profiles:**
local (local setting)
production (live setting)


**Instructions:**
Clone the repo:  (public)
1- $ git clone https://github.com/crossoverQA/crossover-tests.git
(Or download a ZIP of master manually and expand the contents someplace on your system)
2- Project source zip is also attached with this assignment


(TODO after testing on Windows)
Use Maven
Open a command window and run:

**Overriding options**
The Cucumber runtime parses command line options to know what features to run, where the glue code lives, what plugins to use etc. When you use the JUnit runner, these options are generated from the @CucumberOptions annotation on your test.

Sometimes it can be useful to override these options without changing or recompiling the JUnit class. This can be done with the cucumber.options system property. The general form is:

**Using Maven (to run tests):**

mvn -P local clean install -Dheadless.execution=yes -Dtest=TestSuiteAll
or
mvn  test -Dcucumber.options="--tags @Sanity"

-Dcucumber.options="--help"
That should list all the available options.

**IMPORTANT**
When you override options with -Dcucumber.options, you will completely override whatever options are hard-coded in your @CucumberOptions or in the script calling cucumber.api.cli.Main. There is one exception to this rule, and that is the --plugin option. This will not override, but add a plugin. The reason for this is to make it easier for 3rd party tools (such as Cucumber Pro) to automatically configure additional plugins by appending arguments to a cucumber.properties file.


**Docker File:**
I installed docker setup on window 10 with all configurationsand prepared a Dockerfile (on project root which have all env readiness)to execute container.


**Docker imageBuild commands:**
Image location : (https://drive.google.com/file/d/18iosObigpsATKZAaZU8416azYYpuHOv1/view)

1- docker pull ubuntu:latest
2- docker build -f Dockerfile -t ubuntu:latest .
**Test Execution command:**
mvn -P local clean install -Dheadless.execution=yes -Dtest=TestSuiteAll


# if copy from local system to start container
docker run -it --privileged -v "D:/<path>/crossover-tests:/mnt" --name cucumber sha256:cc24dc689408e8aad795c2643bbb887ad90ddd21533d51af988171f8af7f342e  bash
