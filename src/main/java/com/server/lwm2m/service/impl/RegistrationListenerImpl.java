package com.server.lwm2m.service.impl;

import com.server.lwm2m.service.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.leshan.core.observation.Observation;
import org.eclipse.leshan.server.registration.Registration;
import org.eclipse.leshan.server.registration.RegistrationListener;
import org.eclipse.leshan.server.registration.RegistrationService;
import org.eclipse.leshan.server.registration.RegistrationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Slf4j
@Service
public class RegistrationListenerImpl implements RegistrationListener {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private RegistrationService registrationService;

    @PostConstruct
    public void onInit(){
        registrationService.addListener(this);
    }

    @Override
    public void registered(Registration reg, Registration previousReg, Collection<Observation> previousObsersations) {
        log.info("New device registered. Endpoint: {}", reg.getEndpoint());
        messageSender.sendObserve(reg);
    }

    @Override
    public void updated(RegistrationUpdate update, Registration updatedReg, Registration previousReg) {
        log.info("Device update registration. Endpoint: {}", updatedReg.getEndpoint());
        try {
            messageSender.sendRead(updatedReg);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void unregistered(Registration reg, Collection<Observation> observations, boolean expired, Registration newReg) {
        log.info("Device unregistered. Endpoint: {}", reg.getEndpoint());
    }
}
