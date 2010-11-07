package com.ledomatic.server;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

public class DevicesResource extends ServerResource {

//    @Get("xml")
//    public Representation represent() {
//        Context context = new Context();
//        context.set("devices", service.list());
//        //String xml = converter.convert("templates/projects.vm", context);
//        return new StringRepresentation("", MediaType.TEXT_XML);
//    }

    @Get
    public String represent(){
    	return "hello world";
    }
}