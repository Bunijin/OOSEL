import java.util.ArrayList;
import java.util.List;

public class Showtime {
    private static int showtimeCounter = 1;
    private int id;
    private String date;
    private String time;
    private double price;
    private Movie movie;
    private List<Seat> seats;

    public Showtime(String date, String time, double price, Movie movie, int seatCount) {
        this.id = showtimeCounter++;
        this.date = date;
        this.time = time;
        this.price = price;
        this.movie = movie;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= seatCount; i++) {
            seats.add(new Seat("S" + i));
        }
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }
}
