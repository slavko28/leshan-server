package com.server.lwm2m.config;

import org.eclipse.leshan.server.californium.CaliforniumRegistrationStore;
import org.eclipse.leshan.server.californium.LeshanServerBuilder;
import org.eclipse.leshan.server.californium.impl.InMemoryRegistrationStore;
import org.eclipse.leshan.server.californium.impl.LeshanServer;
import org.eclipse.leshan.server.observation.ObservationService;
import org.eclipse.leshan.server.registration.RegistrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

    @Bean
    public LeshanServer leshanServer() {
        LeshanServerBuilder serverBuilder = new LeshanServerBuilder();
        serverBuilder.setRegistrationStore(getRegistrationStore());
        return serverBuilder.build();
    }

    @Bean
    public RegistrationService registrationService(LeshanServer leshanServer) {
        return leshanServer.getRegistrationService();
    }

    @Bean
    public ObservationService observationService(LeshanServer leshanServer) {
        return leshanServer.getObservationService();
    }

    private CaliforniumRegistrationStore getRegistrationStore() {
        return new InMemoryRegistrationStore();
    }
}
