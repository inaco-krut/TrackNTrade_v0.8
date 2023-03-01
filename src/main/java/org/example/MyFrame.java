package org.example;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;

public class MyFrame extends JFrame {
    private final JTextField textField;
    private List<String> latestLinks; // added variable to store latest links
    private String latestQuery; // added variable to store latest query
    public MyFrame() {

        // set the size of the frame and make it visible
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 3 - this.getSize().width / 2, dim.height / 4 - this.getSize().height / 2);
        setSize(335, 270);

        // set the title of the frame
        setTitle("Track Parts Finder For Goons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // add this line to exit the entire program when JFrame is closed

        // create a text field for the user to enter the search query
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 100));
        textField.setFont(new Font("Consolas",Font.PLAIN,28));
        textField.setBackground(Color.darkGray);
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.GREEN);
        textField.setToolTipText("Hey Goon! No special characters allowed here, It breaks shit.");
        // add empty border to the left of the textField to position it to the right
        textField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        add(textField, BorderLayout.NORTH);

        // create a URLButton to generate the search queries
        JButton URLButton = new JButton("Click Here Or Press Enter");
        JButton openAllLinks = new JButton("Click Here To Open All Links In Browser");
        URLButton.setFont(new Font("Consolas",Font.PLAIN,20));
        URLButton.setBackground(Color.black); // add this line to change the background color
        URLButton.setForeground(Color.WHITE);
        openAllLinks.setBackground(Color.black);
        openAllLinks.setForeground(Color.WHITE);
        //URLButton.setSize(350,400);

        // Create checkboxes
        JRadioButton europeCheckbox = new JRadioButton("Europe");
        JRadioButton northAmericaCheckbox = new JRadioButton("North America");
        JRadioButton southeastAsiaCheckbox = new JRadioButton("Southeast Asia");

        // Add checkboxes to a button group
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(europeCheckbox);
        buttonGroup.add(northAmericaCheckbox);
        buttonGroup.add(southeastAsiaCheckbox);

        JPanel CheckBoxPanel = new JPanel();
        CheckBoxPanel.add(europeCheckbox);
        CheckBoxPanel.add(northAmericaCheckbox);
        CheckBoxPanel.add(southeastAsiaCheckbox);

        // add the components to the content pane
        add(textField, BorderLayout.NORTH);
        add(URLButton, BorderLayout.CENTER);
        add(CheckBoxPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            // generate and display search queries //dataSets
            String input = textField.getText();
            dataSetEurope dataSetEurope = new dataSetEurope(input);
            dataSetNA dataSetNA = new dataSetNA(input);
            dataSetSEA dataSetSEA = new dataSetSEA(input);


            if (europeCheckbox.isSelected()) {
                System.out.println("Europe checkbox selected.");
                latestQuery = dataSetEurope.generateQueries(); // store the latest query generated
            }
            if (northAmericaCheckbox.isSelected()) {
                System.out.println("North America checkbox selected.");
                latestQuery = dataSetNA.generateQueries(); // store the latest query generated
            }
            if (southeastAsiaCheckbox.isSelected()) {
                System.out.println("Southeast Asia checkbox selected.");
                latestQuery = dataSetSEA.generateQueries(); // store the latest query generated
            }
            if (!europeCheckbox.isSelected() && !northAmericaCheckbox.isSelected() && !southeastAsiaCheckbox.isSelected()) {
                JOptionPane.showMessageDialog(this, "Pick A Region Before You Search.");
            }

            latestLinks = Arrays.asList(latestQuery.split("\n")); // get the links from the latest query

            // create a new frame to display the search queries
            JFrame f = new JFrame("Search Queries");
            Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
            f.setLocation(dim1.width / 4 - f.getSize().width / 2, dim1.height / 4 - f.getSize().height / 2);
            f.setSize(300, 350);
            f.add(openAllLinks, BorderLayout.NORTH);

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

            // set the frame to be visible
            f.setVisible(true);

        });

        setVisible(true);

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
        String[] links = queries.split("\n");
        for (String link : links) {
            try {
                URI uri = new URI(link);
                String domain = uri.getHost();
                if (domain != null) {
                    domain = domain.startsWith("www.") ? domain.substring(4) : domain;
                    html.append("<a href=\"").append(link).append("\" style=\"color: blue; font-family: Helvetica, font-weight: plain;\">").append(domain).append("</a> ").append(userInput).append("<br>");
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return html.toString();
    }
}