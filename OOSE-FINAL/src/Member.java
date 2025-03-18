import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Member extends Account {
    private double balance;
    private List<Booking> bookings;

    public Member(String name, String password) {
        super(name, password);
        this.balance = 0.0;
        this.bookings = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void viewMoviesAndBookTickets(List<Movie> movieList,Scanner scanner) {
        // View available movies
        System.out.println("Available Movies:");
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + movieList.get(i).getTitle());
        }
        
        // Select movie
        Movie selectedMovie = selectMovie(movieList, scanner);
        if (selectedMovie == null) return;
    
        // Select showtime
        Showtime selectedShowtime = selectShowtime(selectedMovie, scanner);
        if (selectedShowtime == null) return;
    
        // Select seats
        List<Seat> selectedSeats = selectSeats(selectedShowtime, scanner);
        if (selectedSeats.isEmpty()) return;
    
        // Confirm booking
        confirmBooking(selectedMovie, selectedSeats, selectedShowtime, scanner);
    }
    
    private Movie selectMovie(List<Movie> movieList, Scanner scanner) {
        System.out.print("Enter the number of the movie you want to watch: ");
        int movieIndex = scanner.nextInt();

        if (movieIndex < 1 || movieIndex > movieList.size()) {
            System.out.println("[ERROR] Invalid selection.");
            return null;
        }

        return movieList.get(movieIndex - 1);
    }

    private Showtime selectShowtime(Movie movie, Scanner scanner) {
        List<Showtime> showtimes = movie.getShowtimes();

        if (showtimes.isEmpty()) {
            System.out.println("[INFO] No showtimes available for this movie.");
            return null;
        }

        System.out.println("Available Showtimes:");
        for (int i = 0; i < showtimes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + showtimes.get(i).getDate() + " - " + showtimes.get(i).getTime());
        }

        System.out.print("Select a showtime: ");
        int showtimeIndex = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        if (showtimeIndex < 1 || showtimeIndex > showtimes.size()) {
            System.out.println("[ERROR] Invalid selection.");
            return null;
        }

        return showtimes.get(showtimeIndex - 1);
    }

    private List<Seat> selectSeats(Showtime showtime, Scanner scanner) {
        List<Seat> selectedSeats = new ArrayList<>();

        // Display available seats
        showtime.displaySeats();

        System.out.print("Enter seat numbers separated by space (e.g., A1 B2 C3): ");
        String[] seatNumbers = scanner.nextLine().split(" ");
        Seat[][] seats = showtime.getSeats();

        for (String seatNumber : seatNumbers) {
            if (seatNumber.length() < 2) {
                System.out.println("[ERROR] Invalid seat format: " + seatNumber);
                continue;
            }

            char rowChar = seatNumber.charAt(0);
            int row = rowChar - 'A';  // Convert letter to row index

            int col;
            try {
                col = Integer.parseInt(seatNumber.substring(1)) - 1;  // Convert number to col index
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid seat number: " + seatNumber);
                continue;
            }

            // Check if seat is within range and unbooked
            if (row >= 0 && row < seats.length && col >= 0 && col < seats[row].length) {
                Seat seat = seats[row][col];
                if (!seat.isBooked()) {
                    seat.bookSeat();
                    selectedSeats.add(seat);
                } else {
                    System.out.println("[ERROR] Seat " + seatNumber + " is already booked.");
                }
            } else {
                System.out.println("[ERROR] Seat " + seatNumber + " is out of range.");
            }
        }

        return selectedSeats;
    }

    // Confirm booking and process payment
    private void confirmBooking(Movie selectedMovie, List<Seat> selectedSeats, Showtime selectedShowtime, Scanner scanner) {
        double totalCost = selectedSeats.size() * selectedShowtime.getPrice();
        System.out.println("Total Cost: " + totalCost + " Baht");
        System.out.print("Confirm booking? (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("yes")) {
            cancelBooking(selectedSeats);
            System.out.println("Booking cancelled. Seats are now available again.");
            return;
        }

        if (!processPayment(totalCost)) {
            cancelBooking(selectedSeats);
            System.out.println("[ERROR] Payment failed.");
            return;
        }
        // Create a new Booking object
        Booking newBooking = new Booking(selectedMovie, selectedShowtime, selectedSeats);
    
        // Add the booking to the member's bookings list
        bookings.add(newBooking);
        System.out.println("Booking Successful! Enjoy your movie.");
    }

    // Cancel booking and free up the seats
    private void cancelBooking(List<Seat> selectedSeats) {
        for (Seat seat : selectedSeats) {
            seat.unbookSeat();  // Unbook the seat and make it available
        }
        selectedSeats.clear();  // Clear the list of selected seats
    }

    // Process payment (dummy implementation)
    private boolean processPayment(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Payment successful! Remaining balance: " + balance + " Baht");
            return true;
        } else {
            System.out.println("[ERROR] Insufficient balance.");
            return false;
        }
    }

    // Add balance to member account
    public void addBalance(Scanner scanner) {
        System.out.print("Enter amount to add to your balance: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline

        if (amount <= 0) {
            System.out.println("[ERROR] Invalid amount.");
            return;
        }
        
        this.balance += amount;
        System.out.println("Balance successfully updated. Current balance: " + balance + " Baht");
    }

    // View booked tickets
    public void viewBookedTickets(Scanner scanner) {
        if (bookings.isEmpty()) {
            System.out.println("You have no booked tickets.");
            return;
        }

        System.out.println("Your Booked Tickets:");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + bookings.get(i).getMovie().getTitle() + " at " + bookings.get(i).getShowtime().getDate() + " " + bookings.get(i).getShowtime().getTime());
        }

        System.out.print("Select a booking to cancel (0 to exit): ");
        int bookingIndex = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        if (bookingIndex < 1 || bookingIndex > bookings.size()) {
            System.out.println("[ERROR] Invalid selection.");
            return;
        }

        Booking selectedBooking = bookings.get(bookingIndex - 1);
        cancelBooking(selectedBooking);
    }

    // Cancel a specific booking
    private void cancelBooking(Booking booking) {
        booking.cancel();
        System.out.println("Booking cancelled and seats freed.");
    }
}
