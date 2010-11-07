package com.ledomatic.server;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

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
    	String deviceid = (String) getRequest().getAttributes().get("deviceid");
    	String inputtype = (String) getRequest().getAttributes().get("inputtype");
    	String id = "";
    	if (getRequest().getAttributes().containsKey("id")) {
    		id = (String) getRequest().getAttributes().get("id");
    	}
    	return deviceid + " " + inputtype + " " + id;
    }
}