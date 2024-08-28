package org.example;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;

public class MainFrame extends JFrame {
    private final JTextField textField;
    private List<String> latestLinks;
    private String latestQuery;
    private JFrame searchFrame; // Reference to the frame displaying the search queries

    public MainFrame() {
        setTitle("Track Parts Finder For Goons");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 3 - this.getSize().width / 2, dim.height / 4 - this.getSize().height / 2);
        setSize(455, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 60));
        textField.setFont(new Font("Consolas", Font.PLAIN, 28));
        textField.setBackground(Color.darkGray);
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.GREEN);
        textField.setToolTipText("Hey Goon! No special characters allowed here, It breaks shit.");
        textField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JButton URLButton = new JButton("Click Here Or Press Enter");
        JButton openAllLinks = new JButton("Click Here To Open All Links In Browser");
        URLButton.setFont(new Font("Consolas", Font.PLAIN, 20));
        URLButton.setBackground(Color.black);
        URLButton.setForeground(Color.WHITE);
        URLButton.setOpaque(true); // mac setting
        URLButton.setBorderPainted(false); // mac setting
        openAllLinks.setBackground(Color.black);
        openAllLinks.setForeground(Color.WHITE);

        JRadioButton europeRadioButton = new JRadioButton("Europe");
        JRadioButton northAmericaRadioButton = new JRadioButton("North America");
        JRadioButton southEastAsiaRadioButton = new JRadioButton("Southeast Asia");
        JRadioButton FaceBookEuropeRadioButton = new JRadioButton("Facebook EU");
        JPanel CheckBoxPanel = new JPanel();
        CheckBoxPanel.add(europeRadioButton);
        CheckBoxPanel.add(northAmericaRadioButton);
        CheckBoxPanel.add(southEastAsiaRadioButton);
        CheckBoxPanel.add(FaceBookEuropeRadioButton);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(europeRadioButton);
        buttonGroup.add(northAmericaRadioButton);
        buttonGroup.add(southEastAsiaRadioButton);
        buttonGroup.add(FaceBookEuropeRadioButton);

        add(textField, BorderLayout.NORTH);
        add(URLButton, BorderLayout.CENTER);
        add(CheckBoxPanel, BorderLayout.SOUTH);
        setVisible(true);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    URLButton.doClick();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetterOrDigit(c) || Character.isWhitespace(c)) {
                    super.keyTyped(e);
                } else {
                    e.consume();
                }
            }
        });

        URLButton.addActionListener(e -> {
            if (textField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Text box is feeling lonely.");
                return;
            }

            String input = textField.getText();
            dataSetEurope dataSetEurope = new dataSetEurope(input);
            dataSetNA dataSetNA = new dataSetNA(input);
            dataSetSEA dataSetSEA = new dataSetSEA(input);
            dataSetFBEU dataSetFBEU = new dataSetFBEU(input);

            if (europeRadioButton.isSelected()) {
                latestQuery = dataSetEurope.generateQueries();
            } else if (northAmericaRadioButton.isSelected()) {
                latestQuery = dataSetNA.generateQueries();
            } else if (southEastAsiaRadioButton.isSelected()) {
                latestQuery = dataSetSEA.generateQueries();
            } else if (FaceBookEuropeRadioButton.isSelected()) {
                latestQuery = dataSetFBEU.generateQueries();
            } else {
                JOptionPane.showMessageDialog(this, "Pick A Region Before You Search.");
                return;
            }

            latestLinks = Arrays.asList(latestQuery.split("\n"));

            // Close the previous frame if it is open
            if (searchFrame != null) {
                searchFrame.setVisible(false); // Hide the frame first
                searchFrame.dispose(); // Then dispose of it
            }

            // Create a new frame to display the search queries
            searchFrame = new JFrame("Search Queries");

            // Calculate the new frame's position relative to the main window
            int newFrameX = getLocation().x - searchFrame.getWidth() - 355; // Adjusted to move 10 pixels to the right
            int newFrameY = getLocation().y;
            searchFrame.setLocation(newFrameX, newFrameY); // Position to the left of the main window
            searchFrame.setSize(350, 480);
            searchFrame.add(openAllLinks, BorderLayout.NORTH);
            searchFrame.setVisible(true);

            JEditorPane outputTextArea = new JEditorPane();
            outputTextArea.setEditable(false);
            outputTextArea.setContentType("text/html");
            outputTextArea.setText(getHtmlLinks(latestQuery, textField.getText()));

            outputTextArea.addHyperlinkListener(e1 -> {
                if (e1.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        Desktop.getDesktop().browse(new URI(e1.getURL().toString()));
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            searchFrame.add(new JScrollPane(outputTextArea));
        });

        openAllLinks.addActionListener(e2 -> {
            for (String link : latestLinks) {
                try {
                    Desktop.getDesktop().browse(new URI(link.startsWith("http") ? link : "http://" + link));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private String getHtmlLinks(String queries, String userInput) {
        StringBuilder html = new StringBuilder();
        if (queries == null || userInput == null) {
            return "";
        }

        Set<String> europeanCapitals = new HashSet<>(Arrays.asList(
                "Amsterdam", "Andorra la Vella", "Athens", "Belgrade", "Berlin",
                "Bratislava", "Brussels", "Bucharest", "Budapest", "Chisinau",
                "Copenhagen", "Dodoma", "Helsinki", "Kiev", "Lisbon",
                "Ljubljana", "London", "Luxembourg", "Madrid", "Minsk",
                "Monte Carlo", "Moscow", "Nicosia", "Oslo", "Paris",
                "Podgorica", "Prague", "Reykjavik", "Rome", "San Marino",
                "Sarajevo", "Skopje", "Sofia", "Stockholm", "Tallinn",
                "Tbilisi", "Tirana", "Vaduz", "Valletta", "Vatican City",
                "Vienna", "Vilnius", "Warsaw", "Zagreb", "Zurich", "dublin", "riga"
        ));

        String[] links = queries.split("\n");
        for (String link : links) {
            try {
                URI uri = new URI(link.trim());
                String domain = uri.getHost();
                if (domain != null) {
                    domain = domain.startsWith("www.") ? domain.substring(4) : domain;
                    String displayText = domain;

                    if (domain.contains("facebook.com")) {
                        String path = uri.getPath();
                        if (path != null) {
                            for (String capital : europeanCapitals) {
                                if (path.toLowerCase().contains(capital.toLowerCase())) {
                                    displayText = "Facebook.com - " + capital;
                                    break;
                                }
                            }
                        }
                    }

                    String country = "";
                    if (domain.endsWith(".se")) {
                        country = " - Sweden";
                    } else if (domain.endsWith(".uk")) {
                        country = " - United Kingdom";
                    } else if (domain.endsWith(".de")) {
                        country = " - Germany";
                    } else if (domain.endsWith(".dk")) {
                        country = " - Denmark";
                    } else if (domain.endsWith(".fr")) {
                        country = " - France";
                    } else if (domain.endsWith(".no")) {
                        country = " - Norway";
                    } else if (domain.endsWith(".fi")) {
                        country = " - Finland";
                    } else if (domain.endsWith(".nl")) {
                        country = " - Netherlands";
                    } else if (domain.endsWith(".at")) {
                        country = " - Austria";
                    } else if (domain.endsWith(".hr")) {
                        country = " - Croatia";
                    } else if (domain.endsWith(".pl")) {
                        country = " - Poland";
                    } else if (domain.endsWith(".pt")) {
                        country = " - Portugal";
                    } else if (domain.endsWith(".ua")) {
                        country = " - Ukraine";
                    } else if (domain.endsWith(".bg")) {
                        country = " - Bulgaria";
                    } else if (domain.endsWith(".ch")) {
                        country = " - Switzerland";
                    }

                    html.append("<a href=\"").append(link).append("\" style=\"color: blue; font-family: Helvetica; font-weight: normal;\">")
                            .append(displayText).append(country)
                            .append("</a> ")
                            .append(userInput)
                            .append("<br>");
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return html.toString();
    }
}
