import java.util.ArrayList;
import java.util.List;

public class Movie {
    private static int nextId = 1;
    private int id;
    private String title;
    private String genre;
    private List<Showtime> showtimes;

    public Movie(String title, String genre) {
        this.id = nextId++;
        this.title = title;
        this.genre = genre;
        this.showtimes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    public int getShowtimeCount() {
        return showtimes.size();
    }
}
