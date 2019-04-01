package com.server.lwm2m.service.util;

import org.eclipse.leshan.core.node.*;
import org.eclipse.leshan.core.response.ObserveResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ResponseHandler {

    private LwM2mObject lwM2mObject;
    private LwM2mObjectInstance lwM2mObjectInstance;
    private LwM2mResource lwM2mResource;

    public LwM2mObject getLwm2mObject(ObserveResponse response, LwM2mPath path) {
        parseContent(response);
        return getObject(path);
    }

    private LwM2mObject getObject(LwM2mPath path) {

        if (lwM2mObjectInstance == null) {
            lwM2mObjectInstance = new LwM2mObjectInstance(path.getObjectId(), Collections.singletonList(lwM2mResource));
        }

        if (lwM2mObject == null) {
            lwM2mObject = new LwM2mObject(path.getObjectInstanceId(), lwM2mObjectInstance);
        }
        return lwM2mObject;
    }

    private void parseContent(ObserveResponse response) {
        response.getContent().accept(new LwM2mNodeVisitor() {
            @Override
            public void visit(LwM2mObject object) {
                lwM2mObject = object;
            }

            @Override
            public void visit(LwM2mObjectInstance instance) {
                lwM2mObjectInstance = instance;
            }

            @Override
            public void visit(LwM2mResource resource) {
                lwM2mResource = resource;
            }
        });
    }
}
