package com.learn.resource.part30_MicroProfile_health;

import com.learn.resource.part9.TvSeriesIdProxy;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Liveness
public class LivenessHealthCheck implements HealthCheck {

    @RestClient
    TvSeriesIdProxy tvSeriesIdProxy;

    @Override
    public HealthCheckResponse call() {
        tvSeriesIdProxy.getTvSeriesById(1);
        return HealthCheckResponse.named("Proxy/Client APIs Health").up().status(true).withData("","UP").build();
    }
}
