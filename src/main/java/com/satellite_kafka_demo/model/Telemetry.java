//POJO model
//Shared between services to maintain consistency.
package com.satellite_kafka_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Telemetry {
    private String satelliteId;
    private long timestamp;
    private double temperature;
    private int battery;
}
