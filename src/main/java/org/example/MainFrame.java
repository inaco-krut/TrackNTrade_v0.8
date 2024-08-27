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
    private List<String> latestLinks; // added variable to store latest links
    private String latestQuery; // added variable to store latest query

    public MainFrame() {

        // main frame settings
        setTitle("Track Parts Finder For Goons");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 3 - this.getSize().width / 2, dim.height / 4 - this.getSize().height / 2);
        setSize(420, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TextField Settings
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 60));
        textField.setFont(new Font("Consolas", Font.PLAIN, 28));
        textField.setBackground(Color.darkGray);
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.GREEN);
        textField.setToolTipText("Hey Goon! No special characters allowed here, It breaks shit.");
        textField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // URLButton settings
        JButton URLButton = new JButton("Click Here Or Press Enter");
        JButton openAllLinks = new JButton("Click Here To Open All Links In Browser");
        URLButton.setFont(new Font("Consolas", Font.PLAIN, 20));
        URLButton.setBackground(Color.black); // add this line to change the background color
        URLButton.setForeground(Color.WHITE);
        openAllLinks.setBackground(Color.black);
        openAllLinks.setForeground(Color.WHITE);

        // Region RadioButton settings
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

        // add components to the main frame
        add(textField, BorderLayout.NORTH);
        add(URLButton, BorderLayout.CENTER);
        add(CheckBoxPanel, BorderLayout.SOUTH);
        setVisible(true);

        // add a key listener to the text field to listen for the Enter key
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

        // add an action listener to the URLButton
        URLButton.addActionListener(e -> {
            if (textField.getText().isEmpty()) { // check if the text field is empty
                JOptionPane.showMessageDialog(this, "Text box is feeling lonely.");
                return;
            }

            // generate and display search queries
            String input = textField.getText();
            dataSetEurope dataSetEurope = new dataSetEurope(input);
            dataSetNA dataSetNA = new dataSetNA(input);
            dataSetSEA dataSetSEA = new dataSetSEA(input);
            dataSetFBEU dataSetFBEU = new dataSetFBEU(input);

            if (europeRadioButton.isSelected()) {
                System.out.println("Europe checkbox selected.");
                latestQuery = dataSetEurope.generateQueries();
            }
            if (northAmericaRadioButton.isSelected()) {
                System.out.println("North America checkbox selected.");
                latestQuery = dataSetNA.generateQueries();
            }
            if (southEastAsiaRadioButton.isSelected()) {
                System.out.println("Southeast Asia checkbox selected.");
                latestQuery = dataSetSEA.generateQueries();
            }
            if (FaceBookEuropeRadioButton.isSelected()) {
                System.out.println("Facebook Europe checkbox selected.");
                latestQuery = dataSetFBEU.generateQueries();
            }
            if (!europeRadioButton.isSelected() && !northAmericaRadioButton.isSelected() && !southEastAsiaRadioButton.isSelected() && !FaceBookEuropeRadioButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "Pick A Region Before You Search.");
            }

            latestLinks = Arrays.asList(latestQuery.split("\n"));

            // create a new frame to display the search queries
            JFrame f = new JFrame("Search Queries");
            Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
            f.setLocation(dim1.width / 4 - f.getSize().width / 2, dim1.height / 4 - f.getSize().height / 2);
            f.setSize(300, 350);
            f.add(openAllLinks, BorderLayout.NORTH);
            f.setVisible(true);

            // create a text area to display the search queries as HTML
            JEditorPane outputTextArea = new JEditorPane();
            outputTextArea.setEditable(false);
            outputTextArea.setContentType("text/html");
            outputTextArea.setText(getHtmlLinks(latestQuery, textField.getText()));

            // add a hyperlink listener to open hyperlinks in the default browser
            outputTextArea.addHyperlinkListener(e1 -> {
                if (e1.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        Desktop.getDesktop().browse(new URI(e1.getURL().toString()));
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // add the text area to the frame
            f.add(new JScrollPane(outputTextArea));

        });

        // add an action listener to the openAllLinks button
        openAllLinks.addActionListener(e2 -> {
            for (String link : latestLinks) { // loop through latest links only
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
            return ""; // Return empty string if input is null
        }

        // Set of European capital cities
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
                URI uri = new URI(link.trim()); // Trim to remove any leading/trailing spaces
                String domain = uri.getHost();
                if (domain != null) {
                    domain = domain.startsWith("www.") ? domain.substring(4) : domain;

                    // Determine display text
                    String displayText = domain;
                    
                    // Check for Facebook URL and European capital
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
                    
                    // Check for specific TLDs and append the country name
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
                // Log the error or skip the invalid URL
                e.printStackTrace();
            }
        }
        return html.toString();
    }
}
