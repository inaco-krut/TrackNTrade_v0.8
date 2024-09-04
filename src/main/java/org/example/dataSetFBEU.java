package org.example;

public class dataSetFBEU {
    private final String input;

    public dataSetFBEU(String input) {
        this.input = input.toUpperCase();
    }

    public String generateQueries() {

        String fbAustria = "https://www.facebook.com/marketplace/vienna/search?query="+input;
        String fbBelgium = "https://www.facebook.com/marketplace/brussels/search?query="+input;
        String fbBulgaria = "https://www.facebook.com/marketplace/sofia/search?query="+input;
        String fbCroatia = "https://www.facebook.com/marketplace/zagreb/search?query="+input;
        String fbCzechRepublic = "https://www.facebook.com/marketplace/prague/search?query="+input;
        String fbDenmark = "https://www.facebook.com/marketplace/copenhagen/search?query="+input;
                String fbFinland = "https://www.facebook.com/marketplace/helsinki/search?query="+input;
        String fbFrance = "https://www.facebook.com/marketplace/paris/search?query="+input;
        String fbGermany = "https://www.facebook.com/marketplace/berlin/search?query="+input;
        String fbGreece = "https://www.facebook.com/marketplace/athens/search?query="+input;
        String fbHungary = "https://www.facebook.com/marketplace/budapest/search?query="+input;
        String fbIreland = "https://www.facebook.com/marketplace/dublin/search?query="+input;
        String fbItaly = "https://www.facebook.com/marketplace/rome/search?query="+input;
        String fbLatvia = "https://www.facebook.com/marketplace/riga/search?query="+input;
        String fbLithuania = "https://www.facebook.com/marketplace/vilnius/search?query="+input;
        String fbNetherlands = "https://www.facebook.com/marketplace/amsterdam/search?query="+input;
        String fbPoland = "https://www.facebook.com/marketplace/warsaw/search?query="+input;
        String fbPortugal = "https://www.facebook.com/marketplace/lisbon/search?query="+input;
        String fbRomania = "https://www.facebook.com/marketplace/bucharest/search?query="+input;
        String fbSpain = "https://www.facebook.com/marketplace/madrid/search?query="+input;
        String fbSweden = "https://www.facebook.com/marketplace/stockholm/search?query="+input;


        return fbAustria.replace(" ", "%20") + "\n" +
                fbBelgium.replace(" ", "%20") + "\n" +
                fbBulgaria.replace(" ", "%20") + "\n" +
                fbCroatia.replace(" ", "%20") + "\n" +
                fbCzechRepublic.replace(" ", "%20") + "\n" +
                fbDenmark.replace(" ", "%20") + "\n" +
                fbFinland.replace(" ", "%20") + "\n" +
                fbFrance.replace(" ", "%20") + "\n" +
                fbGermany.replace(" ", "%20") + "\n" +
                fbGreece.replace(" ", "%20") + "\n" +
                fbHungary.replace(" ", "%20") + "\n" +
                fbIreland.replace(" ", "%20") + "\n" +
                fbItaly.replace(" ", "%20") + "\n" +
                fbLatvia.replace(" ", "%20") + "\n" +
                fbLithuania.replace(" ", "%20") + "\n" +
                fbNetherlands.replace(" ", "%20") + "\n" +
                fbPoland.replace(" ", "%20") + "\n" +
                fbPortugal.replace(" ", "%20") + "\n" +
                fbRomania.replace(" ", "%20") + "\n" +
                fbSpain.replace(" ", "%20") + "\n" +
                fbSweden.replace(" ", "%20") + "\n";

    }
}
