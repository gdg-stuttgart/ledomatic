package com.ledomatic.server;

public interface DeviceService {

	void saveDevice(Device device);

	Device getDevice(String id);

}
