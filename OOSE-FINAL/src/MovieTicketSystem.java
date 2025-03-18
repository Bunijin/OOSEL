import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MovieTicketSystem {
    private static Admin admin;
    private static List<Member> memberList = new ArrayList<>();
    private static List<Movie> movieList = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        addAdmin();
        main: while (true) {
            System.out.println("=".repeat(50));
            System.out.println("Movie ticketing system");
            System.out.println("=".repeat(50));
            System.out.println("[1] Register");
            System.out.println("[2] Login");
            System.out.println("[3] Exit");
            int choices = getUserChoice();
            switch (choices) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("exit...");
                    break main;
                default:
                    break;
            }
        }
        scanner.close();
    }

    private static void addAdmin() {
        while (true) {
            System.out.println("=".repeat(50));
            System.out.println("Register Admin");
            System.out.println("=".repeat(50));
            System.out.print("Username : ");
            String username = scanner.nextLine();
            System.out.print("Password : ");
            String password = scanner.nextLine();
            try {
                admin = new Admin(username, password);
                break;
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
        return;
    }

    private static void register() {
        while (true) {
            System.out.print("Username : ");
            String username = scanner.nextLine();
            System.out.print("Password : ");
            String password = scanner.nextLine();
            try {
                memberList.add(new Member(username, password));
                break;
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
        return;
    }

    private static void login() {
        while (true) {
            System.out.print("Username : ");
            String username = scanner.nextLine();
            System.out.print("Password : ");
            String password = scanner.nextLine();
            if (admin.getUsername().equals(username) && admin.isCorrectPassword(password)) {
                System.out.println("Login successfully.");
                adminMenu();
                return;
            }
            for (Member m : memberList) {
                if (m.getUsername().equals(username) && m.isCorrectPassword(password)) {
                    System.out.println("Login successfully.");
                    memberMenu(m);
                    return;
                }
            }
            System.out.println("Invalid ID or Password. Please try again");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("=".repeat(50));
            System.out.println("Administrator Menu");
            System.out.println("=".repeat(50));
            System.out.println("[1] User Management");
            System.out.println("[2] Movie Management");
            System.out.println("[3] Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    userManagement();
                    break;
                case 2:
                    movieManagement();
                    break;
                case 3:
                    System.out.println("Exiting Admin Menu...");
                    return;
                default:
                    System.out.println("[ERROR] Invalid choice. Please select again.");
            }
        }
    }

    private static void userManagement() {
        while (true) {
            System.out.println("=".repeat(50));
            System.out.println("User Management");
            System.out.println("=".repeat(50));
            System.out.println("[1] View Members");
            System.out.println("[2] Delete Member");
            System.out.println("[3] Back to Admin Menu");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    admin.viewMember(memberList);
                    break;
                case 2:
                    System.out.print("Enter Member ID to delete: ");
                    int memberId = getUserChoice();
                    admin.deleteMember(memberList, memberId);
                    break;
                case 3:
                    return; // Go back to the previous menu
                default:
                    System.out.println("[ERROR] Invalid choice. Please try again.");
            }
        }
    }

    private static void movieManagement() {
        while (true) {
            System.out.println("=".repeat(50));
            System.out.println("Movie Management");
            System.out.println("=".repeat(50));
            System.out.println("[1] View Movies");
            System.out.println("[2] Add Movie");
            System.out.println("[3] Delete Movie");
            System.out.println("[4] Edit Movie");
            System.out.println("[5] Add Showtime");

            System.out.println("[6] Back to Admin Menu");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    admin.viewMovie(movieList);
                    break;
                case 2:
                    System.out.print("Enter Movie Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Movie Genre: ");
                    String genre = scanner.nextLine();
                    Movie addMovie = new Movie(title, genre);
                    admin.addMovie(movieList, addMovie);
                    break;
                case 3:
                    System.out.print("Enter Movie ID to delete: ");
                    int movieId = getUserChoice();
                    admin.deleteMovie(movieList, movieId);
                    break;
                case 4:
                    System.out.print("Enter Movie ID to edit: ");
                    int editMovieId = getUserChoice();
                    System.out.print("Enter new movie title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new movie genre: ");
                    String newGenre = scanner.nextLine();

                    for (Movie movie : movieList) {
                        if (movie.getId() == editMovieId) {
                            admin.editMovie(movie, newTitle, newGenre);
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter Movie ID:");
                    Movie movies;
                    adddd: while (true) {
                        movieId = getUserChoice();
                        for (Movie mv : movieList) {
                            if (movieId == mv.getId()) {
                                movies = mv;
                                break adddd;
                            }
                        }
                        System.out.println("Invalid id. please try again.");
                    }
                    System.out.print("Enter date (YYYY/MM/DD):");
                    String date = scanner.nextLine();
                    System.out.print("Enter Showtime:");
                    String time = scanner.nextLine();
                    System.out.print("Enter price:");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter seat size");
                    System.out.print("width:");
                    int row = getUserChoice();
                    int column = getUserChoice();
                    admin.addShowtime(movies, new Showtime(date, time, price, movies, row, column));
                    break;
                case 6:
                    return; // Go back to Admin Menu
                default:
                    System.out.println("[ERROR] Invalid choice. Please try again.");
            }
        }
    }

    private static int getUserChoice() {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void memberMenu(Member member) {
        while (true) {
            System.out.println("\nMember Menu");
            System.out.println("=".repeat(50));
            System.out.println("[1] View Movies & Book Tickets");
            System.out.println("[2] Add Balance");
            System.out.println("[3] View Booked Tickets");
            System.out.println("[4] Logout");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    if(!movieList.isEmpty()) member.viewMoviesAndBookTickets(movieList, scanner);
                    break;
                case 2:
                    member.addBalance(scanner);
                    break;
                case 3:
                    member.viewBookedTickets(scanner);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("[ERROR] Invalid choice. Please try again.");
            }
        }
    }

}
