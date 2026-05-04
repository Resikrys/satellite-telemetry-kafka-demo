//Componente 3: Alert Service
//El consumidor final que decide si hay una emergencia.
package com.satellite_kafka_demo.alert;

import com.satellite_kafka_demo.model.Telemetry;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @KafkaListener(topics = "satellite-processed", groupId = "alert-group")
    public void checkAlerts(Telemetry data) {
        if (data.getTemperature() > 80) {
            System.err.println("⚠️ ALERT: High Temperature on " + data.getSatelliteId() + ": " + data.getTemperature() + "°C");
        }
        if (data.getBattery() < 20) {
            System.err.println("🪫 ALERT: Low Battery on " + data.getSatelliteId() + ": " + data.getBattery() + "%");
        }
    }
}
