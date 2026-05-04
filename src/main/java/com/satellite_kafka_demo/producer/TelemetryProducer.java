// Componente 1: Telemetry Producer
//Este servicio simula el hardware del satélite.
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

    @Scheduled(fixedRate = 2000) // Envía cada 2 segundos
    public void sendTelemetry() {
        Telemetry data = new Telemetry(
                "SAT-1",
                System.currentTimeMillis() / 1000,
                Math.random() * 100, // Temperatura aleatoria
                (int) (Math.random() * 100) // Batería aleatoria
        );
        kafkaTemplate.send("satellite-raw", data);
        System.out.println("Sent: " + data);
    }
}