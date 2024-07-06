FROM openjdk:11-jre-slim
COPY . /app
WORKDIR /app
RUN javac SimulacionLanzamientoDados.java
CMD ["java", "SimulacionLanzamientoDados"]
