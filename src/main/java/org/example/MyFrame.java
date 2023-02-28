package org.example;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.HyperlinkEvent;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
    private final JTextField textField;
    private List<String> latestLinks; // added variable to store latest links
    private String latestQuery; // added variable to store latest query
    public MyFrame() {

        // set the title of the frame
        setTitle("Track Parts Finder");

        // set the layout manager of the content pane
        setLayout(new BorderLayout());

        // create a text field for the user to enter the search query
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));

        // create a URLButton to generate the search queries
        JButton URLButton = new JButton("Click Here To Get URL");
        JButton openAllLinks = new JButton("Open all links in browser");

        // create a text area to display the generated search queries
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);


        // add the components to the content pane
        add(textField, BorderLayout.NORTH);
        add(URLButton, BorderLayout.CENTER);
        add(new JScrollPane(textArea), BorderLayout.SOUTH);

        // set the size of the frame and make it visible
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 3 - this.getSize().width / 2, dim.height / 4 - this.getSize().height / 2);
        setSize(400, 400);
        setVisible(true);

        // add an action listener to the URLButton
        URLButton.addActionListener(e -> {

            String input = textField.getText();
            SearchQuery searchQuery = new SearchQuery(input);
            latestQuery = searchQuery.generateQueries(); // store the latest query generated
            latestLinks = Arrays.asList(latestQuery.split("\n")); // get the links from the latest query

            // create a new frame to display the search queries
            JFrame f = new JFrame("Search Queries");
            Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
            f.setLocation(dim1.width / 16 - f.getSize().width / 2, dim1.height / 4 - f.getSize().height / 2);
            f.setSize(900, 300);
            f.add(openAllLinks, BorderLayout.NORTH);


            // create a text area to display the search queries as HTML
            JEditorPane outputTextArea = new JEditorPane();
            outputTextArea.setEditable(false);
            outputTextArea.setContentType("text/html");
            outputTextArea.setText(getHtmlLinks(latestQuery)); // display latest query links


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
    private String getHtmlLinks(String queries) {
        StringBuilder html = new StringBuilder();
        String[] links = queries.split("\n");
        for (String link : links) {
            html.append("<a href=\"").append(link).append("\">").append(link).append("</a><br>");
        }
        return html.toString();
    }

}