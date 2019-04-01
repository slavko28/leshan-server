package com.server.lwm2m.service.impl;

import com.server.lwm2m.service.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.leshan.core.request.ObserveRequest;
import org.eclipse.leshan.core.request.ReadRequest;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.server.LwM2mServer;
import org.eclipse.leshan.server.registration.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageSenderImpl implements MessageSender {

    @Autowired
    private LwM2mServer lwM2mServer;

    @Override
    public ReadResponse sendRead(Registration registration) throws InterruptedException {
        log.info("send read request");
//        return lwM2mServer.send(registration, new ReadRequest(3, 0));
        return null;
    }

    @Override
    public void sendObserve(Registration registration) {
        log.info("send observe request");
        try {
            lwM2mServer.send(registration, new ObserveRequest(3, 0));
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

    }
}
