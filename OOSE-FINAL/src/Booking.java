import java.util.List;

public class Booking {
    private static int bookingCounter = 1;
    private int bookingId;
    private Member member;
    private Showtime showtime;
    private List<Seat> seats;
    private double totalPrice;
    private boolean isConfirmed;

    public Booking(Member member, Showtime showtime, List<Seat> seats) {
        this.bookingId = bookingCounter++;
        this.member = member;
        this.showtime = showtime;
        this.seats = seats;
        this.totalPrice = seats.size() * showtime.getPrice();
        this.isConfirmed = false;
    }

    public int getBookingId() {
        return bookingId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void confirm() {
        isConfirmed = true;
        for (Seat seat : seats) {
            seat.setBooked(true); // Mark the selected seats as booked
        }
        System.out.println("✅ Booking #" + bookingId + " confirmed!");
    }

    public void cancel() {
        if (isConfirmed) {
            for (Seat seat : seats) {
                seat.setBooked(false); // Free the seats upon cancellation
            }
            System.out.println("❌ Booking #" + bookingId + " canceled.");
        } else {
            System.out.println("❗ Booking was never confirmed, no need to cancel.");
        }
        isConfirmed = false;
    }

    public Member getMember() {
        return member;
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
}
