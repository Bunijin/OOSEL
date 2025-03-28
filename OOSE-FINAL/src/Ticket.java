public class Ticket {
    private Member member;
    private Showtime showtime;
    private String movieTitle;
    private String seatNumber;
    private int row;
    private int column;
    private long bookingTimestamp;

    public Ticket(Member member, Showtime showtime, String movieTitle, String seatNumber) {
        this.member = member;
        this.showtime = showtime;
        this.movieTitle = movieTitle;
        this.seatNumber = seatNumber;
        
        // Extract row and column from seat number
        int[] coordinates = showtime.getCoordinatesFromSeatNumber(seatNumber);
        if (coordinates != null) {
            this.row = coordinates[0];
            this.column = coordinates[1];
        }
        
        this.bookingTimestamp = System.currentTimeMillis();
    }

    public Member getMember() {
        return member;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public long getBookingTimestamp() {
        return bookingTimestamp;
    }
    
    public double getPrice() {
        return showtime.getPrice();
    }
}