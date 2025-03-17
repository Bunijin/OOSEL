import java.util.List;

public class Admin extends Account {
    public Admin(String name, String password) {
        super(name, password);
    }

    public void viewMember(List<Member> memberList) {
        if (memberList.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member member : memberList) {
            System.out.println("Member ID: " + member.getId() + ", Username: " + member.getUsername());
        }
    }
    
    public void deleteMember(List<Member> memberList, int memberId) {
        memberList.removeIf(member -> member.getId() == memberId);
        System.out.println("Member deleted.");
    }

    public void addMovie(List<Movie> movieList, Movie movie) {
        movieList.add(movie);
        System.out.println("Movie added: " + movie.getTitle());
    }

    public void viewMovie(List<Movie> movieList) {
        for(Movie movie : movieList) {
            System.out.println("Movie ID: " + movie.getId() + "\tTitle : " + movie.getTitle() +"\tGenre : " + movie.getGenre());
        }
    }

    public void deleteMovie(List<Movie> movieList, int movieId) {
        movieList.removeIf(movie -> movie.getId() == movieId);
        System.out.println("Movie deleted.");
    }

    public void editMovie(Movie movie, String newTitle, String newGenre) {
        movie.setTitle(newTitle);
        movie.setGenre(newGenre);
        System.out.println("Movie updated: " + newTitle);
    }

    public void addShowtime(Movie movie, Showtime showtime) {
        movie.getShowtimes().add(showtime);
        System.out.println("Showtime added for " + movie.getTitle());
    }
}
