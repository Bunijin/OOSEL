public class Seat {
    private String seatNumber;
    private boolean isBooked;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        this.isBooked = booked;
    }

    public void bookSeat() {
        if (!isBooked) {
            isBooked = true;
            System.out.println("Seat " + seatNumber + " successfully booked.");
        } else {
            System.out.println("Seat " + seatNumber + " is already booked.");
        }
    }

    public void cancelBooking() {
        if (isBooked) {
            isBooked = false;
            System.out.println("Seat " + seatNumber + " booking canceled.");
        } else {
            System.out.println("Seat " + seatNumber + " was not booked.");
        }
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
