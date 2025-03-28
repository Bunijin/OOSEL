import java.util.ArrayList;
import java.util.List;

public class Admin extends Account {
    public Admin(String name, String password) throws Exception {
        super(name, password);
    }

    public List<Object[]> viewMembers(List<Member> members) {
        List<Object[]> memberData = new ArrayList<>();
        for (Member member : members) {
            Object[] row = new Object[2];
            row[0] = member.getId();
            row[1] = member.getUsername();
            memberData.add(row);
        }
        return memberData;
    }


    public void deleteMember(List<Member> members, int id) {
        members.removeIf(member -> member.getId() == id);
    }

    public List<Object[]> viewMovies(List<Movie> movies) {
        List<Object[]> movieData = new ArrayList<>();
        for (Movie movie : movies) {
            Object[] row = new Object[4];
            row[0] = movie.getId();
            row[1] = movie.getTitle();
            row[2] = movie.getGenre();
            row[3] = movie.getShowtimeCount();
            movieData.add(row);
        }
        return movieData;
    }

    public void addMovie(List<Movie> movies, Movie movie) {
        movies.add(movie);
    }

    public void deleteMovie(List<Movie> movies, int id) {
        movies.removeIf(movie -> movie.getId() == id);
    }

    public void editMovie(Movie movie, String title, String genre) {
        movie.setTitle(title);
        movie.setGenre(genre);
    }

    public void addShowtime(Movie movie, Showtime showtime) {
        movie.addShowtime(showtime);
    }
}
