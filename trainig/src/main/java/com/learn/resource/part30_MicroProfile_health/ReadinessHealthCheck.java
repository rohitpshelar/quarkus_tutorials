package com.learn.resource.part30_MicroProfile_health;

import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.sql.DataSource;
import java.sql.SQLException;

@Readiness
public class ReadinessHealthCheck implements HealthCheck {

    @Inject
    DataSource dataSource;

    @Override
    public HealthCheckResponse call() {
        try {
            var connection = dataSource.getConnection();
            if (connection.isValid(1)){
                return HealthCheckResponse.named("Database Health")
                        .up()
                        .status(true)
                        .build();
            }  else {  return HealthCheckResponse.named("Database Health")
                    .down()
                    .status(false)
                    .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
