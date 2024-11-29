FROM tomcat:jre21-temurin-noble

ENV CATALINA_OPTS="-Xmx512m"

EXPOSE 8080

COPY ./target/nequi-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/nequi.war

CMD ["catalina.sh", "run"]
