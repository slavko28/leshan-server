package com.server.lwm2m.service.impl;

import com.server.lwm2m.service.util.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.leshan.core.node.*;
import org.eclipse.leshan.core.observation.Observation;
import org.eclipse.leshan.core.response.ObserveResponse;
import org.eclipse.leshan.server.observation.ObservationListener;
import org.eclipse.leshan.server.observation.ObservationService;
import org.eclipse.leshan.server.registration.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class ObservationListenerImpl implements ObservationListener {

    @Autowired
    private ResponseHandler handler;

    @Autowired
    private ObservationService observationService;

    @PostConstruct
    public void onInit() {
        observationService.addListener(this);
    }

    @Override
    public void newObservation(Observation observation, Registration registration) {
        log.info("new observation");
    }

    @Override
    public void cancelled(Observation observation) {
        log.info("cancel observation");
    }

    @Override
    public void onResponse(Observation observation, Registration registration, ObserveResponse response) {
        log.info("receive observation response from endpoint: {}", registration.getEndpoint());

        LwM2mObject object = handler.getLwm2mObject(response, observation.getPath());
        log.info(object.toString());
    }

    @Override
    public void onError(Observation observation, Registration registration, Exception error) {
        log.info("observation error");
    }
}
