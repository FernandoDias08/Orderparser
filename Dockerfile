FROM gradle:7.5.1-jdk17

WORKDIR /app

COPY lib/build.gradle .
COPY gradle/ ./gradle/
COPY gradlew .
COPY gradlew.bat .

COPY lib/src/ ./src/

RUN gradle clean shadowJar

EXPOSE 8080

CMD ["java", "-jar", "build/libs/app-all.jar"]
