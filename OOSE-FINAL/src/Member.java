import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Member extends Account {
    private double balance;
    private List<Booking> bookingList;

    public Member(String name, String password) {
        super(name, password);
        this.balance = 0.0;
        this.bookingList = new ArrayList<>();
    }
    
    public void addBalance(double amount) {
        this.balance += amount;
        System.out.println("Added " + amount + " Baht. New balance: " + this.balance);
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
    
    public void selectMovie(List<Movie> movieList) {
        System.out.println("Available Movies");
        if (movieList.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }
        System.out.println("Available Movies");
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + movieList.get(i).getTitle() + " - " + movieList.get(i).getGenre());
        }
        System.out.print("Enter the number of the movie you want to watch: ");
        Scanner scanner = new Scanner(System.in);
        int movieIndex = scanner.nextInt();
        scanner.close();
        if (movieIndex < 1 || movieIndex > movieList.size()) {
            System.out.println("[ERROR] Invalid selection.");
            return;
        }
    
        Movie selectedMovie = movieList.get(movieIndex - 1);

        Showtime selectedShowtime = selectShowtime(selectedMovie);
        if (selectedShowtime == null) return;

        List<Seat> selectedSeats = selectSeats(selectedShowtime);
        if (selectedSeats.isEmpty()) return;

        double totalCost = selectedSeats.size() * selectedShowtime.getPrice();
        System.out.println("Total Cost: " + totalCost + " Baht");
        System.out.print("Confirm booking? (yes/no): ");
        Scanner confirmation = new Scanner(System.in);
        String confirm = confirmation.nextLine();
        confirmation.close();
    
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Booking cancelled.");
            return;
        }

        if (!processPayment(totalCost)) return;
    
        bookMovie(selectedShowtime, selectedSeats);
        System.out.println("Booking Successful! Enjoy your movie.");
    }

    public Showtime selectShowtime(Movie movie) {
        List<Showtime> showtimes = movie.getShowtimes();
        
        if (showtimes.isEmpty()) {
            System.out.println("[INFO] No showtimes available for this movie.");
            return null;
        }
    
        System.out.println("\nAvailable Showtimes:");
        for (int i = 0; i < showtimes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + showtimes.get(i).getDate() + " - " + showtimes.get(i).getTime());
        }
    
        System.out.print("Select a showtime: ");
        Scanner scanner = new Scanner(System.in);
        int showtimeIndex;
        while (true) {
            try {
                showtimeIndex = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.close();
        if (showtimeIndex < 1 || showtimeIndex > showtimes.size()) {
            System.out.println("[ERROR] Invalid selection.");
            return null;
        }
        return showtimes.get(showtimeIndex - 1);
    }

    public List<Seat> selectSeats(Showtime showtime) {
        List<Seat> availableSeats = showtime.getAvailableSeats();
        List<Seat> selectedSeats = new ArrayList<>();
    
        if (availableSeats.isEmpty()) {
            System.out.println("[INFO] No available seats for this showtime.");
            return selectedSeats;
        }
    
        System.out.println("\n🪑 Available Seats:");
        for (Seat seat : availableSeats) {
            System.out.print(seat.getSeatNumber() + " ");
        }
        System.out.println("\nEnter seat numbers separated by space (e.g., A1 B2 C3):");
        Scanner scanner = new Scanner(System.in);
        String[] seatNumbers = scanner.nextLine().split(" ");
        scanner.close();
        for (String seatNumber : seatNumbers) {
            for (Seat seat : availableSeats) {
                if (seat.getSeatNumber().equalsIgnoreCase(seatNumber)) {
                    selectedSeats.add(seat);
                    break;
                }
            }
        }
    
        if (selectedSeats.isEmpty()) {
            System.out.println("[ERROR] No valid seats selected.");
        }
    
        return selectedSeats;
    }

    public boolean processPayment(double totalCost) {
        System.out.println("\n💳 Payment Options:");
        System.out.println("[1] Pay with Balance (Current: " + this.getBalance() + " Baht)");
        System.out.println("[2] Cancel Booking");

        Scanner scanner = new Scanner(System.in);
        int paymentChoice;
        while (true) {
            try {
                paymentChoice = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.close();

        if (paymentChoice == 1) {
            if (this.getBalance() < totalCost) {
                System.out.println("[ERROR] Insufficient balance. Please add more funds.");
                return false;
            }
            this.deductBalance(totalCost);
            System.out.println("Payment Successful! Remaining balance: " + this.getBalance() + " Baht");
            return true;
        } else {
            System.out.println("Booking cancelled.");
            return false;
        }
    }

    private void bookMovie(Showtime selectedShowtime, List<Seat> selectedSeats) {
        Booking newBooking = new Booking(this, selectedShowtime, selectedSeats);
        bookingList.add(newBooking);
        for (Seat seat : selectedSeats) {
            seat.setBooked(true);
        }
    }
}
