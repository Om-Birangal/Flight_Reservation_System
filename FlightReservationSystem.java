import java.util.*;
class Flight {
    int flightNo;
    String source, destination;
    double price;
    boolean isBooked;
    Flight(int flightNo, String source, String destination, double price) {
        this.flightNo = flightNo;
        this.source = source;
        this.destination = destination;
        this.price = price;
        this.isBooked = false;
    }
    void display() {
        System.out.printf("%-10d %-15s %-15s %-10.2f %-10s\n",
                flightNo, source, destination, price,
                (isBooked ? "Booked" : "Available"));
    }
}

public class FlightReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Flight> flights = new ArrayList<>();
    // Initialize flight data
    static void initializeFlights() {
        flights.add(new Flight(101, "Pune", "Delhi", 4500));
        flights.add(new Flight(102, "Mumbai", "Chennai", 5500));
        flights.add(new Flight(103, "Delhi", "Bangalore", 6000));
        flights.add(new Flight(104, "Pune", "Kolkata", 7500));
        flights.add(new Flight(105, "Goa", "Hyderabad", 4000));
    }
    static void displayFlights() {
        System.out.printf("%-10s %-15s %-15s %-10s %-10s\n",
                "FlightNo", "Source", "Destination", "Price", "Status");
        for (Flight f : flights)
            f.display();
    }
    static void sortByPrice() {
        flights.sort(Comparator.comparingDouble(f -> f.price));
        System.out.println("Flights sorted by price.");
    }
    static void searchByDestination(String dest) {
        boolean found = false;
        for (Flight f : flights) {
            if (f.destination.equalsIgnoreCase(dest)) {
                f.display();
                found = true;
            }
        }
        if (!found)
            System.out.println("No flights found to " + dest);
    }
    static void searchByFlightNo(int no) {
        for (Flight f : flights) {
            if (f.flightNo == no) {
                System.out.println("Flight Found:");
                f.display();
                return;
            }
        }
        System.out.println("Flight not found.");
    }
    static void bookFlight(int no) {
        for (Flight f : flights) {
            if (f.flightNo == no) {
                if (!f.isBooked) {
                    f.isBooked = true;
                    System.out.println("Booking Confirmed: "+ f.source + " → " + f.destination);
                } else {
                    System.out.println("Flight already booked.");
                }
                return;
            }
        }
        System.out.println("Invalid Flight Number.");
    }
    public static void main(String[] args) {
        initializeFlights(); // 🔴 Important call
        int ch;
        do {
            System.out.println("\n=== Flight Reservation System ===");
            System.out.print("1. View Flights\n2. Sort by Price\n3. Search by Destination\n4. Search by Flight No\n5. Book Flight\n6. Exit\nEnter choice: ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    displayFlights();
                    break;
                case 2:
                    sortByPrice();
                    displayFlights();
                    break;
                case 3:
                    System.out.print("Enter Destination: ");
                    searchByDestination(sc.next());
                    break;
                case 4:
                    System.out.print("Enter Flight No: ");
                    searchByFlightNo(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Flight No to Book: ");
                    bookFlight(sc.nextInt());
                    break;
                case 6:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (ch != 6);
        sc.close();
    }
}