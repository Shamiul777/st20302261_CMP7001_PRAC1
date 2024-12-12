package baymotorscom.baymotors;

import org.junit.jupiter.api.*;

import com.baymotors.Customer;
import com.baymotors.CustomerVehicleManager;
import com.baymotors.Vehicle;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomerVehicleManagerTest {
    private static final String CUSTOMER_TEST_FILE = "test_customers.tsv";
    private static final String VEHICLE_TEST_FILE = "test_vehicles.tsv";
    private CustomerVehicleManager manager;

    @BeforeEach
    void setUp() throws IOException {
        // Replace the file paths with test files
        Files.write(Paths.get(CUSTOMER_TEST_FILE), "".getBytes()); // Create empty test files
        Files.write(Paths.get(VEHICLE_TEST_FILE), "".getBytes());

        manager = new CustomerVehicleManager() {
            @Override
            public List<Customer> getCustomers() throws IOException {
                return Files.lines(Paths.get(CUSTOMER_TEST_FILE))
                        .map(Customer::fromString)
                        .collect(Collectors.toList());
            }

            @Override
            public List<Vehicle> getVehicles() throws IOException {
                return Files.lines(Paths.get(VEHICLE_TEST_FILE))
                        .map(Vehicle::fromString)
                        .collect(Collectors.toList());
            }

            @Override
            public void addCustomer(Customer customer) throws IOException {
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CUSTOMER_TEST_FILE), StandardOpenOption.APPEND)) {
                    writer.write(customer.toString());
                    writer.newLine();
                }
            }

            @Override
            public void addVehicle(Vehicle vehicle) throws IOException {
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(VEHICLE_TEST_FILE), StandardOpenOption.APPEND)) {
                    writer.write(vehicle.toString());
                    writer.newLine();
                }
            }
        };
    }

    @Test
    void testAddAndGetCustomers() throws IOException {
        Customer customer1 = new Customer(1, "Alice", "alice@example.com", "1234567890", false);
        Customer customer2 = new Customer(2, "Bob", "bob@example.com", "0987654321", false);

        manager.addCustomer(customer1);
        manager.addCustomer(customer2);

        List<Customer> customers = manager.getCustomers();
        assertEquals(2, customers.size(), "Should have two customers");
        assertEquals(customer1.getName(), customers.get(0).getName(), "First customer should match");
        assertEquals(customer2.getName(), customers.get(1).getName(), "Second customer should match");
    }

    @Test
    void testAddAndGetVehicles() throws IOException {
        Vehicle vehicle1 = new Vehicle(1, "ABC123", "Toyota", "Corolla", 1);
        Vehicle vehicle2 = new Vehicle(2, "DEF456", "Honda", "Civic", 2);

        manager.addVehicle(vehicle1);
        manager.addVehicle(vehicle2);

        List<Vehicle> vehicles = manager.getVehicles();
        assertEquals(2, vehicles.size(), "Should have two vehicles");
        assertEquals(vehicle1.getRegistrationNumber(), vehicles.get(0).getRegistrationNumber(), "First vehicle should match");
        assertEquals(vehicle2.getRegistrationNumber(), vehicles.get(1).getRegistrationNumber(), "Second vehicle should match");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(CUSTOMER_TEST_FILE));
        Files.deleteIfExists(Paths.get(VEHICLE_TEST_FILE));
    }
}
