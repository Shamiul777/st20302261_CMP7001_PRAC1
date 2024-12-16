package com.baymotors;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerVehicleManager {
    private static final String CUSTOMER_FILE = "customers.tsv";
    private static final String VEHICLE_FILE = "vehicles.tsv";

    public List<Customer> getCustomers() throws IOException {
        return Files.lines(Paths.get(CUSTOMER_FILE))
                .map(Customer::fromString)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehicles() throws IOException {
        return Files.lines(Paths.get(VEHICLE_FILE))
                .map(Vehicle::fromString)
                .collect(Collectors.toList());
    }

    public Customer getCustomerById(int id) throws IOException {
        return getCustomers().stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Vehicle getVehicleById(int id) throws IOException {
        return getVehicles().stream()
                .filter(vehicle -> vehicle.getVehicleId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addCustomer(Customer customer) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CUSTOMER_FILE), StandardOpenOption.APPEND)) {
            writer.write(customer.toString());
            writer.newLine();
        }
    }

    public void addVehicle(Vehicle vehicle) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(VEHICLE_FILE), StandardOpenOption.APPEND)) {
            writer.write(vehicle.toString());
            writer.newLine();
        }
    }
}
