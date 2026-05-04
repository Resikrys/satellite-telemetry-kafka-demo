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

    @KafkaListener(topics = "satellite-raw", groupId = "processor-group")
    public void process(Telemetry rawData) {
        // Processing simulation: Data normalization
        rawData.setSatelliteId(rawData.getSatelliteId().toUpperCase());

        // Forward to the next pipeline topic
        kafkaTemplate.send("satellite-processed", rawData);
        System.out.println("Processed and Forwarded: " + rawData.getSatelliteId());
    }
}
