# Pull base image for cucumber/selenium
FROM  ubuntu:latest

MAINTAINER Tauqir Sarwar

RUN apt-get update -qqy \
	&& apt-get -qqy install xvfb \
	&& rm -rf /var/lib/apt/lists/* /var/cache/apt/*
	
	
ARG MAVEN_VERSION=3.5.2
ARG USER_HOME_DIR="/root"
ARG SHA=707b1f6e390a65bde4af4cdaf2a24d45fc19a6ded00fff02e91626e3e42ceaff

# Install Java
RUN \
  echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  apt-get update && \
  apt-get install -y software-properties-common python-software-properties && \
  add-apt-repository ppa:webupd8team/java && \
  apt-get update && \
  apt-get install oracle-java7-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk7-installer

# Google Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
	&& echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
	&& apt-get update -qqy \
	&& apt-get -qqy install google-chrome-stable \
	&& rm /etc/apt/sources.list.d/google-chrome.list \
	&& rm -rf /var/lib/apt/lists/* /var/cache/apt/* \
	&& sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome

# Define working directory.
WORKDIR /data

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Define commonly used JAVA_HOME variable
RUN java -version
RUN which java
ENV JAVA_HOME /usr/lib/jvm/java-7-oracle

#Git install

RUN apt-get update
RUN apt-get install -y git

# copy from github
RUN rm -rf /mnt/docker/
RUN git clone https://github.com/crossoverQA/crossover-tests.git /mnt/docker/

# command to execute tests
# CMD ["/mnt/docker/mvn -P local clean install -Dtest=TestSuiteAll"]





