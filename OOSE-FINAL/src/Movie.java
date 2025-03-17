import java.util.ArrayList;
import java.util.List;

public class Movie {
    private static int movieCounter = 1;
    private int id;
    private String title;
    private String genre;
    private List<Showtime> showtimes;

    public Movie(String title, String genre) {
        this.id = movieCounter++;
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

    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
