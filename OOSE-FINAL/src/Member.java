import java.util.ArrayList;
import java.util.List;

public class Member extends Account {
    private double balance;
    private List<Ticket> tickets;

    public Member(String name, String password) throws Throwable {
        super(name, password);
        this.balance = 0.0;
        this.tickets = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance += amount;
    }

    public void deductBalance(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
    }

    public List<Ticket> getTickets() {
        return new ArrayList<>(tickets); // Return a copy to prevent direct modification
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
    
    public void bookTicket(Showtime showtime, int row, int col) 
            throws IllegalArgumentException {
        // Check if the seat is available
        if (row < 0 || row >= showtime.getRows() || col < 0 || col >= showtime.getColumns() 
                || !showtime.getSeatAvailability()[row][col]) {
            throw new IllegalArgumentException("Seat is not available");
        }
        
        // Check if user has enough balance
        double price = showtime.getPrice();
        if (balance < price) {
            throw new IllegalArgumentException("Insufficient funds to book this ticket");
        }
        
        // Book the seat
        showtime.bookSeat(row, col);
        
        // Deduct balance
        deductBalance(price);
        
        // Create a ticket and add it to the member's tickets
        String seatNumber = (char)('A' + row) + "" + (col + 1);
        Ticket ticket = new Ticket(this, showtime, showtime.getMovie().getTitle(), seatNumber);
        addTicket(ticket);
        
        return;
    }
    
    public void cancelTicket(Ticket ticket) throws IllegalArgumentException {
        // Check if the ticket belongs to this member
        if (!tickets.contains(ticket)) {
            throw new IllegalArgumentException("Ticket not found in your bookings");
        }
        
        // Get the seat coordinates from the ticket
        int row = ticket.getRow();
        int col = ticket.getColumn();
        Showtime showtime = ticket.getShowtime();
        
        // Make the seat available again in the showtime
        showtime.unbookSeat(row, col);
        
        // Remove the ticket from the member's list
        tickets.remove(ticket);
        
        // Calculate refund amount (you can implement different refund policies here)
        double refundAmount = showtime.getPrice();
        
        // Add the refund to the member's balance
        this.balance += refundAmount;
    }
    
    public List<Movie> searchMovies(List<Movie> allMovies, String searchTerm) {
        List<Movie> results = new ArrayList<>();
        searchTerm = searchTerm.toLowerCase();
        
        for (Movie movie : allMovies) {
            if (movie.getTitle().toLowerCase().contains(searchTerm) || 
                movie.getGenre().toLowerCase().contains(searchTerm)) {
                results.add(movie);
            }
        }
        
        return results;
    }
}