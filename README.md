# SATELLITE TELEMETRY KAFKA DEMO

This project simulates a ground segment system using event-driven 
microservices to process satellite telemetry in a scalable and 
resilient way.

## Arquitectura del FlujoProducer: 

Genera datos aleatorios del satélite y los envía al tópico satellite-raw.Processor: Escucha satellite-raw, valida los datos y los envía a satellite-processed.Alert Service: Escucha satellite-processed y dispara una alerta si la temperatura es $> 80$ o la batería es $< 20$.