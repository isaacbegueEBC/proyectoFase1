FROM openjdk:11-jdk-slim
COPY . /app
WORKDIR /app
RUN javac SimulacionLanzamientoDados.java
CMD ["java", "SimulacionLanzamientoDados"]
