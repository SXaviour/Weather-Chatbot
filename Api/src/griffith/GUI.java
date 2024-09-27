package Api.src.griffith;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JButton startButton;
    private WeatherChatbot chatbot;
    private final int totalLocations = 5;

    //.....
    // Constructor to initialize and setup the GUI components.
    public GUI() {
        // This sets Gui theme to match user system theme for a more authentic experince
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        chatbot = new WeatherChatbot();
        frame = new JFrame("Weather Chatbot");
        // sets width and height of gui pop up
        frame.setMinimumSize(new Dimension(500, 400));

        chatArea = new JTextArea(16, 50);
        chatArea.setEditable(false);
        // Sets font for text area
        chatArea.setFont(new Font("Serif", Font.PLAIN, 16));
        chatArea.setForeground(Color.BLACK);
        // sets background for text area
        chatArea.setBackground(Color.RED);
        chatArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(Color.RED)));
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // this section customizes specifically the main button
        startButton = new JButton("Plan My Trip");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setForeground(Color.BLUE);
        startButton.setBackground(Color.BLUE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planTrip();
            }
        });

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(startButton);

        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        frame.getContentPane().add(scrollPane, constraints);
        constraints.gridy = 1;
        frame.getContentPane().add(panel, constraints);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);

    }

    // Method to handle the trip planning workflow.
    private void planTrip() {
        chatArea.setText(""); // Clear the chat area at the start of planning.
        for (int i = 0; i < totalLocations; i++) {
            String location = JOptionPane.showInputDialog(frame, "Enter location " + (i + 1) + " (e.g., city name, country):");
            if (location != null && !location.trim().isEmpty()) {
                String day = JOptionPane.showInputDialog(frame, "What day will you visit " + location + "? (day1, day2, or day3):");
                if (day != null && !day.trim().isEmpty()) {
                    fetchWeatherData(location.trim(), day.trim());
                }
            }
        }
    }

    // Method to fetch and display weather data.
    private void fetchWeatherData(String location, String day) {
        chatArea.append("Fetching weather data for: " + location + " on " + day + "...\n");
        WeatherData weatherData = chatbot.fetchWeather(location, day);
        if (weatherData != null) {
            String suggestion = chatbot.suggestClothing(weatherData);
            chatArea.append("For " + location + " on " + day + " (" + weatherData.getTemperature() + "°C): " + suggestion + "\n\n");
        } else {
            chatArea.append("Failed to fetch data for " + location + " on " + day + "\n\n");
        }
    }
// Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
