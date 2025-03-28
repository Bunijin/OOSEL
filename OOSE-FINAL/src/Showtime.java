import java.util.ArrayList;
import java.util.List;

class Showtime {
    private String date;
    private String time;
    private double price;
    private Movie movie;
    private int rows;
    private int columns;
    private boolean[][] seatAvailability;

    public Showtime(String date, String time, double price, Movie movie, int rows, int columns) {
        this.date = date;
        this.time = time;
        this.price = price;
        this.movie = movie;
        this.rows = rows;
        this.columns = columns;
        this.seatAvailability = new boolean[rows][columns];

        // Initialize all seats as available
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seatAvailability[i][j] = true;
            }
        }
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

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean[][] getSeatAvailability() {
        return seatAvailability;
    }

    public boolean isSeatAvailable(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            return seatAvailability[row][col];
        }
        return false;
    }

    public void bookSeat(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            seatAvailability[row][col] = false;
        }
    }
    
    public void unbookSeat(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            seatAvailability[row][col] = true;
        }
    }

    public int getAvailableSeats() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (seatAvailability[i][j])
                    count++;
            }
        }
        return count;
    }
    
    public List<String> getAllAvailableSeats() {
        List<String> availableSeats = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (seatAvailability[i][j]) {
                    String seatNumber = (char)('A' + i) + "" + (j + 1);
                    availableSeats.add(seatNumber);
                }
            }
        }
        return availableSeats;
    }
    
    public String getSeatNumberFromCoordinates(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            return (char)('A' + row) + "" + (col + 1);
        }
        return null;
    }
    
    public int[] getCoordinatesFromSeatNumber(String seatNumber) {
        if (seatNumber != null && seatNumber.length() >= 2) {
            char rowChar = seatNumber.charAt(0);
            String colStr = seatNumber.substring(1);
            try {
                int row = rowChar - 'A';
                int col = Integer.parseInt(colStr) - 1;
                if (row >= 0 && row < rows && col >= 0 && col < columns) {
                    return new int[]{row, col};
                }
            } catch (NumberFormatException e) {
                // Invalid format
            }
        }
        return null;
    }
}