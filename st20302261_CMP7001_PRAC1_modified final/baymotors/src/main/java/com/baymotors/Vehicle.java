package com.baymotors;

public class Vehicle {
    private int vehicleId;
    private String registrationNumber;
    private String make;
    private String model;
    private int owner;

    public Vehicle(int vehicleId, String registrationNumber, String make, String model, int owner) {
        this.vehicleId = vehicleId;
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.owner = owner;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return vehicleId + "\t" + registrationNumber + "\t" + make + "\t" + model + "\t" + owner;
    }
    
    public static Vehicle fromString(String line) {
        String[] parts = line.split("\t");
        return new Vehicle(
                Integer.parseInt(parts[0]), 
                parts[1], 
                parts[2], 
                parts[3], 
                Integer.parseInt(parts[4])
        );
    }
    
}
