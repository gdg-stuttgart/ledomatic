package com.ledomatic.server;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class DevicesResource extends ServerResource {

	private RestRequest restRequest;

    @Put
    public String login() {
    	restRequest = parseParams();
    	return restRequest.getDeviceid() + " " + restRequest.getInputtype() + " " + restRequest.getId();
    }

    @Delete
    public String logout() {
    	restRequest = parseParams();
    	return "";
    }

    @Get
    public String status() {
    	restRequest = parseParams();
    	return "";
    }

    private RestRequest parseParams() {
    	RestRequest restRequest = new RestRequest();
    	restRequest.setDeviceid((String) getRequest().getAttributes().get("deviceid"));
    	restRequest.setInputtype((String) getRequest().getAttributes().get("inputtype"));
    	if (getRequest().getAttributes().containsKey("id")) {
    		restRequest.setId((String) getRequest().getAttributes().get("id"));
    	}
    	return restRequest;
    }

	private class RestRequest {
		private String deviceid;
		private String inputtype;
		private String id;

		public String getDeviceid() {
			return deviceid;
		}

		public void setDeviceid(String deviceid) {
			this.deviceid = deviceid;
		}

		public String getInputtype() {
			return inputtype;
		}

		public void setInputtype(String inputtype) {
			this.inputtype = inputtype;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

}