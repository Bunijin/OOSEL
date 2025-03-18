public class Showtime {
    private static int showtimeCounter = 1;
    private int id;
    private String date;
    private String time;
    private double price;
    private Movie movie;
    private Seat[][] seats;

    public Showtime(String date, String time, double price, Movie movie, int rows, int cols) {
        this.id = showtimeCounter++;
        this.date = date;
        this.time = time;
        this.price = price;
        this.movie = movie;
        this.seats = new Seat[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char rowChar = (char) ('A' + i); // Convert row index to letter (A, B, C, ...)
                String seatNumber = rowChar + Integer.toString(j + 1); // Construct seat name (A1, B2, etc.)
                seats[i][j] = new Seat(seatNumber); // Create a Seat with the format A1, B2, etc.
            }
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

    public Seat[][] getSeats() {
        return seats;
    }

    public void displaySeats() {
        if (seats == null || seats.length == 0) {
            System.out.println("[INFO] No seats available for this showtime.");
            return;
        }
    
        // Display seat layout
        System.out.println("\n Available Seats:");
        System.out.print("   "); // Padding for column numbers
        for (int col = 0; col < seats[0].length; col++) {
            System.out.print(" " + (col + 1) + " "); // Print column numbers
        }
        System.out.println();
    
        for (int row = 0; row < seats.length; row++) {
            System.out.print((char) ('A' + row) + "  "); // Row label (A, B, C...)
            for (int col = 0; col < seats[row].length; col++) {
                System.out.print(seats[row][col].isBooked() ? "[X]" : "[O]");
            }
            System.out.println();
        }
    }
}
