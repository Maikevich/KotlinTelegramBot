FROM openjdk
WORKDIR /app
COPY . /app
ARG JAR_FILE=target/kotlinBot-0.0.1-SNAPSHOT.jar
WORKDIR /app/main
COPY ${JAR_FILE} app.jar
COPY ./entrypoint.sh /apps/entrypoint.sh
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]