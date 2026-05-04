//Componente 2: Telemetry Processor
//This is where data "cleaning" or enrichment takes place.
package com.satellite_kafka_demo.processor;

import com.satellite_kafka_demo.model.Telemetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TelemetryProcessor {
    @Autowired
    private KafkaTemplate<String, Telemetry> kafkaTemplate;

    // Topics set as constants to avoid typos
    private static final String RAW_TOPIC = "satellite-raw";
    private static final String PROCESSED_TOPIC = "satellite-processed";

    @KafkaListener(topics = RAW_TOPIC, groupId = "processor-group")
    public void process(Telemetry rawData) {
        try {
            //1. Validation
            validateTelemetry(rawData);

            // 2. Transformation
            rawData.setSatelliteId(rawData.getSatelliteId().trim().toUpperCase());

            // 3. Forwarding if everything is correct
            kafkaTemplate.send(PROCESSED_TOPIC, rawData);
            System.out.println("✅ Data Validated & Sent: " + rawData.getSatelliteId());

        } catch (IllegalArgumentException e) {
            // Handling invalid data (we discard it)
            System.err.println("❌ Dropping invalid telemetry from " + rawData.getSatelliteId() + ": " + e.getMessage());
        } catch (Exception e) {
            // Handling unexpected technical errors
            System.err.println("⚠️ Unexpected error processing message: " + e.getMessage());
        }
    }

    private void validateTelemetry(Telemetry data) {
        if (data.getBattery() < 0 || data.getBattery() > 100) {
            throw new IllegalArgumentException("Battery level out of range: " + data.getBattery());
        }
        if (data.getTemperature() < -273.15) {
            throw new IllegalArgumentException("Temperature below absolute zero: " + data.getTemperature());
        }
        if (data.getSatelliteId() == null || data.getSatelliteId().isEmpty()) {
            throw new IllegalArgumentException("Missing Satellite ID");
        }
    }
}
