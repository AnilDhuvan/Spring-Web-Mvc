package com.p1.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Custom logic for checking health (e.g., database, services)
        int errorCode = checkSomething();
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Error Code", errorCode)
                    .build();
        }
        return Health.up().build();
    }

    private int checkSomething() {
        // Custom logic, return non-zero if there's an issue
        return 0;
    }
}

