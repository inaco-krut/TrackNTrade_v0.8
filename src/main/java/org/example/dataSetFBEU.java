package org.example;

public class dataSetFBEU {
    private final String input;

    public dataSetFBEU(String input) {
        this.input = input.toUpperCase();
    }

    public String generateQueries() {

        StringBuilder queries = new StringBuilder();

        String fbAustria = "https://www.facebook.com/marketplace/vienna/search?query="+input;
        String fbBelgium = "https://www.facebook.com/marketplace/brussels/search?query="+input;
        String fbBulgaria = "https://www.facebook.com/marketplace/sofia/search?query="+input;
        String fbCroatia = "https://www.facebook.com/marketplace/zagreb/search?query="+input;
        String fbCyprus = "https://www.facebook.com/marketplace/110929015601194/search?query="+input;
        String fbCzechRepublic = "https://www.facebook.com/marketplace/prague/search?query="+input;
        String fbDenmark = "https://www.facebook.com/marketplace/copenhagen/search?query="+input;
        String fbEstonia = "https://www.facebook.com/marketplace/106039436102339/search?query="+input;
        String fbFinland = "https://www.facebook.com/marketplace/helsinki/search?query="+input;
        String fbFrance = "https://www.facebook.com/marketplace/paris/search?query="+input;
        String fbGermany = "https://www.facebook.com/marketplace/berlin/search?query="+input;
        String fbGreece = "https://www.facebook.com/marketplace/athens/search?query="+input;
        String fbHungary = "https://www.facebook.com/marketplace/budapest/search?query="+input;
        String fbIreland = "https://www.facebook.com/marketplace/dublin/search?query="+input;
        String fbItaly = "https://www.facebook.com/marketplace/rome/search?query="+input;
        String fbLatvia = "https://www.facebook.com/marketplace/riga/search?query="+input;
        String fbLithuania = "https://www.facebook.com/marketplace/vilnius/search?query="+input;
        String fbLuxembourg = "https://www.facebook.com/marketplace/106411452727638/search?query="+input;
        String fbMalta = "https://www.facebook.com/marketplace/110612325626836/search?query="+input;
        String fbNetherlands = "https://www.facebook.com/marketplace/amsterdam/search?query="+input;
        String fbPoland = "https://www.facebook.com/marketplace/warsaw/search?query="+input;
        String fbPortugal = "https://www.facebook.com/marketplace/lisbon/search?query="+input;
        String fbRomania = "https://www.facebook.com/marketplace/bucharest/search?query="+input;
        String fbSlovakia = "https://www.facebook.com/marketplace/110507998976900/search?query="+input;
        String fbSpain = "https://www.facebook.com/marketplace/madrid/search?query="+input;
        String fbSweden = "https://www.facebook.com/marketplace/stockholm/search?query="+input;


        queries.append(fbAustria.replace(" ","%20")).append("\n");
        queries.append(fbBelgium.replace(" ","%20")).append("\n");
        queries.append(fbBulgaria.replace(" ","%20")).append("\n");
        queries.append(fbCroatia.replace(" ","%20")).append("\n");
        queries.append(fbCyprus.replace(" ","%20")).append("\n");
        queries.append(fbCzechRepublic.replace(" ","%20")).append("\n");
        queries.append(fbDenmark.replace(" ","%20")).append("\n");
        queries.append(fbEstonia.replace(" ","%20")).append("\n");
        queries.append(fbFinland.replace(" ","%20")).append("\n");
        queries.append(fbFrance.replace(" ","%20")).append("\n");
        queries.append(fbGermany.replace(" ","%20")).append("\n");
        queries.append(fbGreece.replace(" ","%20")).append("\n");
        queries.append(fbHungary.replace(" ","%20")).append("\n");
        queries.append(fbIreland.replace(" ","%20")).append("\n");
        queries.append(fbItaly.replace(" ","%20")).append("\n");
        queries.append(fbLatvia.replace(" ","%20")).append("\n");
        queries.append(fbLithuania.replace(" ","%20")).append("\n");
        queries.append(fbLuxembourg.replace(" ","%20")).append("\n");
        queries.append(fbMalta.replace(" ","%20")).append("\n");
        queries.append(fbNetherlands.replace(" ","%20")).append("\n");
        queries.append(fbPoland.replace(" ","%20")).append("\n");
        queries.append(fbPortugal.replace(" ","%20")).append("\n");
        queries.append(fbRomania.replace(" ","%20")).append("\n");
        queries.append(fbSlovakia.replace(" ","%20")).append("\n");
        queries.append(fbSpain.replace(" ","%20")).append("\n");
        queries.append(fbSweden.replace(" ","%20")).append("\n");


        return queries.toString();

    }
}
