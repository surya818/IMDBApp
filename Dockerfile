FROM tomcat:8.0
VOLUME /tmp
COPY target/*.war /usr/local/tomcat/webapps/
