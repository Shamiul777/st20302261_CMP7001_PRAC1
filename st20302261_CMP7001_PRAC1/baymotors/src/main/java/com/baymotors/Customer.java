package com.baymotors;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{

    private boolean isRegistered;
    private List<Vehicle> vehicles;

    public Customer(int customerId, String name, String email, String phoneNumber, boolean isRegistered) {
    	super(customerId, name, email, phoneNumber);
        this.isRegistered = isRegistered;
        this.vehicles = new ArrayList<>();
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void register() {
        this.isRegistered = true;
        System.out.println(this.getName() + " is now a registered customer.");
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle added for " + this.getName() + ": " + vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    
    @Override
    public void displayRole() {
        System.out.println("Role: Customer");
    }

    @Override
    public String toString() {
        return getId() + "\t" + getName() + "\t" + getEmail() + "\t" + getPhoneNumber();
    }

    
    public static Customer fromString(String line) {
        String[] parts = line.split("\t");
        return new Customer(
                Integer.parseInt(parts[0]), 
                parts[1], 
                parts[2], 
                parts[3],
                false
        );
    }
}
