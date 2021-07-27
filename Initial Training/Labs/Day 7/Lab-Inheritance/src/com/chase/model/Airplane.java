package com.chase.model;

public class Airplane extends FlyingVehicle {
	private short wheels;
	private short propellers;
	
	public Airplane(short wheels, short propellers) {
		this.wheels = wheels;
		this.propellers = propellers;
	}
	
	//getters and setters
	public short getWheels() {
		return wheels;
	}
	public void setWheels(short wheels) {
		this.wheels = wheels;
		return;
	}
	public short getPropellers() {
		return propellers;
	}
	public void setPropellers(short propellers) {
		this.propellers = propellers;
		return;
	}
	
	public void turn() {
		System.out.println("Turning...");
	}
}
