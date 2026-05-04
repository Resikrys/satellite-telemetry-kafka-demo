# 🛰️ Satellite Telemetry Pipeline

This project is a simulation of a satellite telemetry data pipeline 
using Java Spring Boot and Apache Kafka.

## System Architecture:
The data flow follows an event-driven architecture (EDA) model:
- **Telemetry Producer**: Generates random data (ID, timestamp, 
temperature, battery level) and sends it to the satellite-raw topic.
- **Telemetry Processor**: Consumes data from satellite-raw, 
transforms/cleans the data, and publishes it to satellite-processed.
- **Alert Service**: Analyzes the processed data and issues alerts to 
- the console if it detects critical conditions.

## Prerequisites
- Java 17 or higher
- Docker and Docker Compose
- Maven

## Quick Start Guide
- **1**. Setting up the infrastructure
   Run Kafka and Zookeeper using Docker:
    ````
    docker-compose up -d
    ````
- **2**. Configure the application
   Make sure the src/main/resources/application.yml file has the Kafka configuration pointing to localhost:9092.

- **3**. Run the application
   You can start the project from your IDE or via the terminal:
  ````
    mvn spring-boot:run
  ````
## Alert Thresholds
The system is configured to detect:

- High Temperature: > 80°C
- Critical Battery: < 20%

## Monitoring (Kafka CLI)
To view the raw messages circulating through the processor, you can use:
  ````
    docker exec -it <id_contenedor_kafka> kafka-console-consumer --bootstrap-server localhost:9092 --topic satellite-processed --from-beginning
  ````
### JSON Message Structure
  ````
   {
      "satelliteId": "SAT-1",
      "timestamp": 1710000000,
      "temperature": 85.5,
      "battery": 40
   }
  ````

### To be implemented:
**Database**: Persist processed data in a time-series database 
(InfluxDB or TimescaleDB).

**Dashboard**: Connect a frontend or Grafana to visualize telemetry 
in real time with D3.js

**KSQL**: Use Kafka Streams or KSQL for processing instead of a simple consumer.