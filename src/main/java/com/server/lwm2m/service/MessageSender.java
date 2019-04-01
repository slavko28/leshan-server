package com.server.lwm2m.service;

import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.server.registration.Registration;

public interface MessageSender {

    ReadResponse sendRead(Registration registration) throws InterruptedException;

    void sendObserve(Registration registration);


}
