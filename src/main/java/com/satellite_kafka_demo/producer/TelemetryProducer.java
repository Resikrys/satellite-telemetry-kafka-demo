// Componente 1: Telemetry Producer
//This service simulates the satellite hardware.
package com.satellite_kafka_demo.producer;

import com.satellite_kafka_demo.model.Telemetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TelemetryProducer {
    @Autowired
    private KafkaTemplate<String, Telemetry> kafkaTemplate;

    @Scheduled(fixedRate = 2000) // It sends every 2 seconds
    public void sendTelemetry() {
        Telemetry data = new Telemetry(
                "SAT-1",
                System.currentTimeMillis() / 1000,
                Math.random() * 100, // Random temperature
                (int) (Math.random() * 100) // Random battery
        );
        kafkaTemplate.send("satellite-raw", data);
        System.out.println("Sent: " + data);
    }
}