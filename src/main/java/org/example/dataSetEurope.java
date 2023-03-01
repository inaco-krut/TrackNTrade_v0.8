package org.example;

public class dataSetEurope {
    private final String input;

    public dataSetEurope(String input) {
        this.input = input.toUpperCase();
    }

    public String generateQueries() {

        StringBuilder queries = new StringBuilder();

        String dbaQuery = "https://www.dba.dk/soeg/?soeg=" + input;
        String mercariQuery = "https://www.mercari.com/search/?keyword="+ input;
        String wallapopQuery = "https://uk.wallapop.com/app/search?keywords="+input+"&filters_source=search_box&latitude=51.509865&longitude=-0.118092";
        String twoEmemainQuery = "https://www.2ememain.be/q/"+input+"/#Language:all-languages";
        String finnNoQuery = "https://www.finn.no/bap/forsale/search.html?q="+input+"&sort=RELEVANCE";
        String ebayKleinanzeigenQuery = "https://www.ebay-kleinanzeigen.de/s-"+input+"/k0";
        String marktplaatsQuery = "https://www.marktplaats.nl/q/"+input+"/";
        String bikemarktQuery = "https://bikemarkt.mtb-news.de/search/"+input;
        String trocVeloQuery = "https://www.troc-velo.com/fr-fr/recherche?_=%22"+input+"%22&filters=%7B%22terms%22%3A%22"+input+"%22%7D";
        String willhabenQuery = "https://www.willhaben.at/iad/kaufen-und-verkaufen/marktplatz?sfId=fe973a99-b481-431b-ab14-dcb3bbb3af03&isNavigation=true&keyword="+input;
        String ricardoQuery = "https://www.ricardo.ch/de/s/"+input+"/";

        queries.append(dbaQuery.replace(" ","+")).append("\n");
        queries.append(mercariQuery.replace(" ","%20")).append("\n");
        queries.append(wallapopQuery.replace(" ", "%20")).append("\n");
        queries.append(twoEmemainQuery.replace(" ", "+")).append("\n");
        queries.append(finnNoQuery.replace(" ", "+")).append("\n");
        queries.append(ebayKleinanzeigenQuery.replace(" ", "-")).append("\n");
        queries.append(marktplaatsQuery.replace(" ", "+")).append("\n");
        queries.append(bikemarktQuery.replace(" ", "-")).append("\n");
        queries.append(trocVeloQuery.replace(" ", "+")).append("\n");
        queries.append(willhabenQuery.replace(" ", "%20")).append("\n");
        queries.append(ricardoQuery.replace(" ", "%20")).append("\n");

        System.out.println(queries);

        return queries.toString();

    }
}
