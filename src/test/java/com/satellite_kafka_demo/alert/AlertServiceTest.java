package com.satellite_kafka_demo.alert;

import com.satellite_kafka_demo.model.Telemetry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlertServiceTest {

    private final AlertService alertService = new AlertService();

    @Test
    void testCheckAlerts_HighTemperature() {
        // Case: Temperature above the limit (85 > 80)
        Telemetry highTemp = new Telemetry("SAT-TEST", 1710000000L, 85.0, 50);
        alertService.checkAlerts(highTemp);
        // You should see the error message in the JUnit console.
    }

    @Test
    void testCheckAlerts_LowBattery() {
        // Case: Battery below the limit (15 < 20)
        Telemetry lowBattery = new Telemetry("SAT-TEST", 1710000000L, 25.0, 15);
        alertService.checkAlerts(lowBattery);
    }

    @Test
    void testCheckAlerts_NormalStatus() {
        // Case: Normal values
        Telemetry normal = new Telemetry("SAT-TEST", 1710000000L, 25.0, 90);
        alertService.checkAlerts(normal);
        // It shouldn't print any alerts to the console.
    }
}