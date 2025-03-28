import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MovieTicketSystem {
    private static Admin admin;
    private static List<Member> memberList = new ArrayList<>();
    private static List<Movie> movieList = new ArrayList<>();

    // Main frame and current panel
    private static JFrame mainFrame;
    private static JPanel currentPanel;

    public static void main(String[] args) {
        // Set look and feel to match the system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and set up the main frame
        mainFrame = new JFrame("Movie Ticketing System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLocationRelativeTo(null);

        // Start with the admin registration screen
        showAdminRegistrationPanel();

        // Display the frame
        mainFrame.setVisible(true);
    }

    // Method to switch to a new panel
    private static void switchPanel(JPanel newPanel) {
        if (currentPanel != null) {
            mainFrame.remove(currentPanel);
        }
        currentPanel = newPanel;
        mainFrame.add(currentPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    // Admin Registration Panel
    private static void showAdminRegistrationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Register Admin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton registerButton = new JButton("Register");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);

        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    admin = new Admin(username, password);
                    showMainMenu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Error: " + ex.getMessage(),
                            "Registration Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        switchPanel(panel);
    }

    // Main Menu Panel
    private static void showMainMenu() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Movie Ticketing System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(registerButton, gbc);

        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        // Add action listeners
        registerButton.addActionListener(e -> showRegisterPanel());
        loginButton.addActionListener(e -> showLoginPanel());
        exitButton.addActionListener(e -> System.exit(0));

        switchPanel(panel);
    }

    // Registration Panel
    private static void showRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Member Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Add action listeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    memberList.add(new Member(name, password));
                    JOptionPane.showMessageDialog(mainFrame,
                            "Registration successful!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    showMainMenu();
                } catch (Throwable ex) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Error: " + ex.getMessage(),
                            "Registration Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> showMainMenu());

        switchPanel(panel);
    }

    // Login Panel
    private static void showLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (admin.getUsername().equals(username) && admin.isCorrectPassword(password)) {
                    showAdminMenu();
                    return;
                }

                for (Member m : memberList) {
                    if (m.getUsername().equals(username) && m.isCorrectPassword(password)) {
                        showMemberMenu(m);
                        return;
                    }
                }

                JOptionPane.showMessageDialog(mainFrame,
                        "Invalid username or password",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> showMainMenu());

        switchPanel(panel);
    }

    // Admin Menu Panel
    private static void showAdminMenu() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Administrator Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton userManagementButton = new JButton("User Management");
        JButton movieManagementButton = new JButton("Movie Management");
        JButton logoutButton = new JButton("Logout");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(userManagementButton, gbc);

        gbc.gridy = 2;
        panel.add(movieManagementButton, gbc);

        gbc.gridy = 3;
        panel.add(logoutButton, gbc);

        // Add action listeners
        userManagementButton.addActionListener(e -> showUserManagementPanel());
        movieManagementButton.addActionListener(e -> showMovieManagementPanel());
        logoutButton.addActionListener(e -> showMainMenu());

        switchPanel(panel);
    }

    // User Management Panel
    private static void showUserManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("User Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Get member data from admin
        List<Object[]> memberData = admin.viewMembers(memberList);
        String[] columnNames = { "ID", "Username" };
        Object[][] data = memberData.toArray(new Object[memberData.size()][]);

        JTable memberTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(memberTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton deleteButton = new JButton("Delete Selected Member");
        JButton backButton = new JButton("Back");

        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = memberTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int memberId = (int) memberTable.getValueAt(selectedRow, 0);
                    admin.deleteMember(memberList, memberId);
                    showUserManagementPanel(); // Refresh the panel
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Please select a member to delete",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> showAdminMenu());

        switchPanel(panel);
    }

    // Movie Management Panel
    private static void showMovieManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Movie Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Get movie data from admin
        List<Object[]> movieData = admin.viewMovies(movieList);
        String[] columnNames = { "ID", "Title", "Genre", "Showtimes" };
        Object[][] data = movieData.toArray(new Object[movieData.size()][]);

        JTable movieTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(movieTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        JButton addButton = new JButton("Add Movie");
        JButton editButton = new JButton("Edit Movie");
        JButton deleteButton = new JButton("Delete Movie");
        JButton addShowtimeButton = new JButton("Add Showtime");
        JButton viewShowtimesButton = new JButton("View Showtimes");
        JButton backButton = new JButton("Back");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(addShowtimeButton);
        buttonPanel.add(viewShowtimesButton);
        buttonPanel.add(backButton);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(e -> showAddMovieDialog());

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = movieTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int movieId = (int) movieTable.getValueAt(selectedRow, 0);
                    for (Movie movie : movieList) {
                        if (movie.getId() == movieId) {
                            showEditMovieDialog(movie);
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Please select a movie to edit",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = movieTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int movieId = (int) movieTable.getValueAt(selectedRow, 0);
                    admin.deleteMovie(movieList, movieId);
                    showMovieManagementPanel(); // Refresh the panel
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Please select a movie to delete",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        addShowtimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = movieTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int movieId = (int) movieTable.getValueAt(selectedRow, 0);
                    for (Movie movie : movieList) {
                        if (movie.getId() == movieId) {
                            showAddShowtimeDialog(movie);
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Please select a movie to add showtime",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        viewShowtimesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = movieTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int movieId = (int) movieTable.getValueAt(selectedRow, 0);
                    for (Movie movie : movieList) {
                        if (movie.getId() == movieId) {
                            showShowtimesDialog(movie);
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Please select a movie to view showtimes",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> showAdminMenu());

        switchPanel(panel);
    }

    // Add Movie Dialog
    private static void showAddMovieDialog() {
        JDialog dialog = new JDialog(mainFrame, "Add Movie", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);

        JLabel genreLabel = new JLabel("Genre:");
        JTextField genreField = new JTextField(20);

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(genreLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(genreField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(buttonPanel, gbc);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String genre = genreField.getText();

                if (title.isEmpty() || genre.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please fill in all fields",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Movie movie = new Movie(title, genre);
                admin.addMovie(movieList, movie);
                dialog.dispose();
                showMovieManagementPanel(); // Refresh
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    // Edit Movie Dialog
    private static void showEditMovieDialog(Movie movie) {
        JDialog dialog = new JDialog(mainFrame, "Edit Movie", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(movie.getTitle(), 20);

        JLabel genreLabel = new JLabel("Genre:");
        JTextField genreField = new JTextField(movie.getGenre(), 20);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(genreLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(genreField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(buttonPanel, gbc);

        // Add action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String genre = genreField.getText();

                if (title.isEmpty() || genre.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please fill in all fields",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                admin.editMovie(movie, title, genre);
                dialog.dispose();
                showMovieManagementPanel(); // Refresh
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    // Add Showtime Dialog
    private static void showAddShowtimeDialog(Movie movie) {
        JDialog dialog = new JDialog(mainFrame, "Add Showtime for " + movie.getTitle(), true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel dateLabel = new JLabel("Date (YYYY/MM/DD):");
        JTextField dateField = new JTextField(10);

        JLabel timeLabel = new JLabel("Time:");
        JTextField timeField = new JTextField(10);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(10);

        JLabel rowLabel = new JLabel("Seats (Rows):");
        JSpinner rowSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 20, 1));

        JLabel columnLabel = new JLabel("Seats (Columns):");
        JSpinner columnSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 20, 1));

        JButton addButton = new JButton("Add Showtime");
        JButton cancelButton = new JButton("Cancel");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(dateLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(timeLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(timeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(rowLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(rowSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(columnLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(columnSpinner, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(buttonPanel, gbc);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String date = dateField.getText();
                    String time = timeField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int rows = (int) rowSpinner.getValue();
                    int columns = (int) columnSpinner.getValue();

                    if (date.isEmpty() || time.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog,
                                "Please fill in all fields",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Showtime showtime = new Showtime(date, time, price, movie, rows, columns);
                    admin.addShowtime(movie, showtime);
                    dialog.dispose();
                    showMovieManagementPanel(); // Refresh
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please enter a valid price",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    // View Showtimes Dialog
    private static void showShowtimesDialog(Movie movie) {
        JDialog dialog = new JDialog(mainFrame, "Showtimes for " + movie.getTitle(), true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 300);

        JLabel titleLabel = new JLabel("Showtimes for " + movie.getTitle(), JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dialog.add(titleLabel, BorderLayout.NORTH);

        // Create a table model for showtimes
        String[] columnNames = { "Date", "Time", "Price", "Available Seats" };
        List<Showtime> showtimes = movie.getShowtimes();
        Object[][] data = new Object[showtimes.size()][4];

        for (int i = 0; i < showtimes.size(); i++) {
            Showtime showtime = showtimes.get(i);
            data[i][0] = showtime.getDate();
            data[i][1] = showtime.getTime();
            data[i][2] = String.format("$%.2f", showtime.getPrice());
            data[i][3] = showtime.getAvailableSeats();
        }

        JTable showtimeTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(showtimeTable);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        closeButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    // Member Menu Panel
    private static void showMemberMenu(Member member) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Member Menu - " + member.getUsername());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel balanceLabel = new JLabel("Current Balance: $" + String.format("%.2f", member.getBalance()));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton viewMoviesButton = new JButton("View Movies & Book Tickets");
        JButton addBalanceButton = new JButton("Add Balance");
        JButton viewTicketsButton = new JButton("View Booked Tickets");
        JButton logoutButton = new JButton("Logout");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        panel.add(balanceLabel, gbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(viewMoviesButton, gbc);

        gbc.gridy = 3;
        panel.add(addBalanceButton, gbc);

        gbc.gridy = 4;
        panel.add(viewTicketsButton, gbc);

        gbc.gridy = 5;
        panel.add(logoutButton, gbc);

        // Add action listeners
        viewMoviesButton.addActionListener(e -> {
            if (movieList.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame,
                        "No movies available at the moment.",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                showMovieBookingPanel(member);
            }
        });

        addBalanceButton.addActionListener(e -> showAddBalanceDialog(member, balanceLabel));

        viewTicketsButton.addActionListener(e -> showBookedTicketsPanel(member));

        logoutButton.addActionListener(e -> showMainMenu());

        switchPanel(panel);
    }

    // Add Balance Dialog
    private static void showAddBalanceDialog(Member member, JLabel balanceLabel) {
        JDialog dialog = new JDialog(mainFrame, "Add Balance", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel amountLabel = new JLabel("Amount to Add: $");
        JTextField amountField = new JTextField(10);

        JButton addButton = new JButton("Add Balance");
        JButton cancelButton = new JButton("Cancel");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        dialog.add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(amountField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(buttonPanel, gbc);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    member.addBalance(amount); // Using the enhanced Member class method
                    balanceLabel.setText("Current Balance: $" + String.format("%.2f", member.getBalance()));
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please enter a valid amount",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog,
                            ex.getMessage(),
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    // Movie Booking Panel
    private static void showMovieBookingPanel(Member member) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Available Movies", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create a table model for movies
        String[] columnNames = { "ID", "Title", "Genre", "Showtimes" };
        Object[][] data = new Object[movieList.size()][4];

        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            data[i][0] = movie.getId();
            data[i][1] = movie.getTitle();
            data[i][2] = movie.getGenre();
            data[i][3] = movie.getShowtimeCount();
        }

        JTable movieTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(movieTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton bookButton = new JButton("Book Selected Movie");
        JButton backButton = new JButton("Back");

        buttonPanel.add(bookButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = movieTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int movieId = (int) movieTable.getValueAt(selectedRow, 0);
                    for (Movie movie : movieList) {
                        if (movie.getId() == movieId) {
                            showBookShowtimeDialog(member, movie);
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Please select a movie to book",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> showMemberMenu(member));

        switchPanel(panel);
    }

    // Book Showtime Dialog
    private static void showBookShowtimeDialog(Member member, Movie movie) {
        List<Showtime> showtimes = movie.getShowtimes();
        if (showtimes.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame,
                    "No showtimes available for this movie.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(mainFrame, "Book Showtime for " + movie.getTitle(), true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 300);

        JLabel titleLabel = new JLabel("Select Showtime for " + movie.getTitle(), JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dialog.add(titleLabel, BorderLayout.NORTH);

        // Create a table model for showtimes
        String[] columnNames = { "Date", "Time", "Price", "Available Seats" };
        Object[][] data = new Object[showtimes.size()][4];

        for (int i = 0; i < showtimes.size(); i++) {
            Showtime showtime = showtimes.get(i);
            data[i][0] = showtime.getDate();
            data[i][1] = showtime.getTime();
            data[i][2] = String.format("$%.2f", showtime.getPrice());
            data[i][3] = showtime.getAvailableSeats();
        }

        JTable showtimeTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(showtimeTable);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton selectButton = new JButton("Select Seats");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(selectButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = showtimeTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Showtime selectedShowtime = showtimes.get(selectedRow);
                    showSeatSelectionDialog(member, selectedShowtime);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog,
                            "Please select a showtime",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    // Seat Selection Dialog - refactored to use updated Member class
    private static void showSeatSelectionDialog(Member member, Showtime showtime) {
        JDialog dialog = new JDialog(mainFrame, "Select Seats", true);
        dialog.setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Select Seats for " + showtime.getMovie().getTitle() +
                " on " + showtime.getDate() + " at " + showtime.getTime(),
                JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel seatPanel = new JPanel(new GridLayout(showtime.getRows(), showtime.getColumns(), 5, 5));
        JToggleButton[][] seats = new JToggleButton[showtime.getRows()][showtime.getColumns()];

        for (int i = 0; i < showtime.getRows(); i++) {
            for (int j = 0; j < showtime.getColumns(); j++) {
                seats[i][j] = new JToggleButton();
                seats[i][j].setPreferredSize(new Dimension(40, 40));
                seats[i][j].setText(showtime.getSeatNumberFromCoordinates(i, j)); // Using the enhanced Showtime method

                if (!showtime.isSeatAvailable(i, j)) { // Using the enhanced Showtime method
                    seats[i][j].setEnabled(false);
                    seats[i][j].setBackground(Color.RED);
                } else {
                    seats[i][j].setBackground(Color.GREEN);
                }

                seatPanel.add(seats[i][j]);
            }
        }

        JScrollPane scrollPane = new JScrollPane(seatPanel);

        JPanel infoPanel = new JPanel(new FlowLayout());
        JLabel priceLabel = new JLabel("Price per seat: $" + String.format("%.2f", showtime.getPrice()));
        JLabel totalLabel = new JLabel("Total: $0.00");
        infoPanel.add(priceLabel);
        infoPanel.add(totalLabel);

        JPanel buttonPanel = new JPanel();
        JButton bookButton = new JButton("Book Tickets");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(infoPanel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(titleLabel, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(southPanel, BorderLayout.SOUTH);

        // Add action listeners to seat buttons to update total price
        for (int i = 0; i < showtime.getRows(); i++) {
            for (int j = 0; j < showtime.getColumns(); j++) {
                seats[i][j].addActionListener(e -> updateTotalPrice(seats, showtime, totalLabel));
            }
        }

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<int[]> selectedSeatsCoordinates = new ArrayList<>();
                double totalPrice = 0;

                for (int i = 0; i < showtime.getRows(); i++) {
                    for (int j = 0; j < showtime.getColumns(); j++) {
                        if (seats[i][j].isSelected()) {
                            selectedSeatsCoordinates.add(new int[] { i, j });
                            totalPrice += showtime.getPrice();
                        }
                    }
                }

                if (selectedSeatsCoordinates.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please select at least one seat",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(dialog,
                        "Confirm booking for " + selectedSeatsCoordinates.size() + " seats at a total of $" +
                                String.format("%.2f", totalPrice) + "?",
                        "Confirm Booking",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        // Process the booking using the enhanced Member class
                        for (int[] coords : selectedSeatsCoordinates) {
                            member.bookTicket(showtime, coords[0], coords[1]);
                        }

                        JOptionPane.showMessageDialog(dialog,
                                "Booking successful! Your tickets have been added to your account.",
                                "Booking Confirmed",
                                JOptionPane.INFORMATION_MESSAGE);

                        dialog.dispose();
                        showMemberMenu(member); // Refresh member menu
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(dialog,
                                "Insufficient balance. Please add funds to your account.",
                                "Insufficient Funds",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    // Helper method to update the total price based on seat selection
    private static void updateTotalPrice(JToggleButton[][] seats, Showtime showtime, JLabel totalLabel) {
        double total = 0;
        int count = 0;

        for (int i = 0; i < showtime.getRows(); i++) {
            for (int j = 0; j < showtime.getColumns(); j++) {
                if (seats[i][j].isSelected()) {
                    total += showtime.getPrice();
                    count++;
                }
            }
        }

        totalLabel.setText("Total: $" + String.format("%.2f", total) + " (" + count + " seats)");
    }

    // View Booked Tickets Panel
    private static void showBookedTicketsPanel(Member member) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Your Booked Tickets", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        List<Ticket> tickets = member.getTickets();

        if (tickets.isEmpty()) {
            JLabel noTicketsLabel = new JLabel("You have not booked any tickets yet.", JLabel.CENTER);
            panel.add(noTicketsLabel, BorderLayout.CENTER);
        } else {
            // Create a table model for tickets
            String[] columnNames = { "Movie", "Date", "Time", "Seat", "Price" };
            Object[][] data = new Object[tickets.size()][5];

            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);
                data[i][0] = ticket.getMovieTitle();
                data[i][1] = ticket.getShowtime().getDate();
                data[i][2] = ticket.getShowtime().getTime();
                data[i][3] = ticket.getSeatNumber();
                data[i][4] = String.format("$%.2f", ticket.getShowtime().getPrice());
            }

            JTable ticketTable = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(ticketTable);
            panel.add(scrollPane, BorderLayout.CENTER);

            // Add a panel for buttons
            JPanel buttonPanel = new JPanel();
            JButton cancelButton = new JButton("Cancel Selected Ticket");
            JButton backButton = new JButton("Back");
            buttonPanel.add(cancelButton);
            buttonPanel.add(backButton);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            // Add action listener for cancel button
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = ticketTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        cancelSelectedTicket(member, tickets.get(selectedRow));
                        // Refresh the panel after cancellation
                        showBookedTicketsPanel(member);
                    } else {
                        JOptionPane.showMessageDialog(panel,
                                "Please select a ticket to cancel",
                                "Selection Required",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            backButton.addActionListener(e -> showMemberMenu(member));
        }

        switchPanel(panel);
    }

    // Helper method to cancel a selected ticket
    private static void cancelSelectedTicket(Member member, Ticket ticket) {
        // Check if the showtime has already passed
        // You might want to add date/time comparison logic here

        int confirm = JOptionPane.showConfirmDialog(mainFrame,
                "Are you sure you want to cancel your ticket for " + ticket.getMovieTitle() +
                        " on " + ticket.getShowtime().getDate() + " at " + ticket.getShowtime().getTime() +
                        "?\nSeat: " + ticket.getSeatNumber() +
                        "\nYou will be refunded $" + String.format("%.2f", ticket.getShowtime().getPrice()),
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Get the row and column from the ticket
                int row = ticket.getRow();
                int col = ticket.getColumn();

                // Make the seat available again in the showtime
                ticket.getShowtime().unbookSeat(row, col);

                // Call the cancelTicket method from Member class
                member.cancelTicket(ticket);

                JOptionPane.showMessageDialog(mainFrame,
                        "Ticket cancelled successfully! Your account has been refunded.",
                        "Cancellation Successful",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame,
                        "An error occurred while cancelling your ticket: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}