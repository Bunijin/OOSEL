import java.util.List;

public class Booking {
    private Movie movie; // Movie being booked
    private Showtime showtime; // Showtime of the movie
    private List<Seat> seats; // List of selected seats
    private boolean isConfirmed; // Booking confirmation status

    // Constructor to initialize a booking
    public Booking(Movie movie, Showtime showtime, List<Seat> seats) {
        this.movie = movie;
        this.showtime = showtime;
        this.seats = seats;
        this.isConfirmed = false; // Initially, the booking is not confirmed
    }

    // Getters for the booking details
    public Movie getMovie() {
        return movie;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    // Method to confirm the booking
    public void confirmBooking() {
        this.isConfirmed = true;
        System.out.println("Booking confirmed for " + movie.getTitle() + " at " + showtime.getDate() + " " + showtime.getTime());
    }

    // Method to cancel the booking
    public void cancel() {
        // Unbook all the selected seats
        for (Seat seat : seats) {
            seat.unbookSeat();
        }
        // Mark the booking as cancelled
        this.isConfirmed = false;
        System.out.println("Booking for " + movie.getTitle() + " has been cancelled.");
    }

    // Method to display the booking details
    public String getBookingDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Movie: ").append(movie.getTitle()).append("\n")
               .append("Showtime: ").append(showtime.getDate()).append(" ").append(showtime.getTime()).append("\n")
               .append("Seats: ");
        for (Seat seat : seats) {
            details.append(seat.getSeatNumber()).append(" ");
        }
        details.append("\n");

        return details.toString();
    }
}
