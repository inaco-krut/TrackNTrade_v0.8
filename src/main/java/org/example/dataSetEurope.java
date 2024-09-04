package org.example;

public class dataSetEurope {
    private final String input;

    public dataSetEurope(String input) {
        this.input = input.toUpperCase();
    }

    public String generateQueries() {

        String dbaQuery = "https://www.dba.dk/cykler/?soeg="+input;
        String mercariQuery = "https://www.mercari.com/search/?keyword="+input;
        String wallapopQuery = "https://uk.wallapop.com/app/search?keywords="+input+"&filters_source=search_box&latitude=51.509865&longitude=-0.118092";
        String finnNoQuery = "https://www.finn.no/bap/forsale/search.html?q="+input+"&sub_category=1.69.3963";
        String ebayKleinanzeigenQuery = "https://www.kleinanzeigen.de/s-fahrraeder/"+input+"/k0c217";
        String marktplaatsQuery = "https://www.marktplaats.nl/l/fietsen-en-brommers/#q:"+input;
        String bikemarktQuery = "https://bikemarkt.mtb-news.de/search/"+input;
        String trocVeloQuery = "https://www.troc-velo.com/fr-fr/recherche?search="+input;
        String willhabenQuery = "https://www.willhaben.at/iad/kaufen-und-verkaufen/marktplatz/fahrraeder-radsport-4525?sfId=c1892adb-f149-4fd1-9e83-f185016b0fef&rows=30&isNavigation=true&keyword="+input;
        String ricardoQuery = "https://www.ricardo.ch/de/c/radsport-41950/"+input+"/";
        String happyRideQuery = "https://happyride.se/annonser/list.php?search="+input+"&category=&county=&creator=&type=";
        String lebonCOinQuery = "https://www.leboncoin.fr/recherche?text="+input+"&kst=k&pi=65acc10f-3035-481a-a0fa-03940b5b6d35";
        String njuskaloCroatiaQuery = "https://www.njuskalo.hr/search/?keywords="+input;
        String olxPolandQuery = "https://www.olx.pl/sport-hobby/rowery/q-"+input;
        String olxPortugalQuery = "https://www.olx.pt/desporto-e-lazer/bicicletas/q-"+input;
        String olxUkraineQuery = "https://www.olx.ua/uk/hobbi-otdyh-i-sport/velo/q-"+input+"/?currency=UAH";
        String olxBulgariaQuery = "https://www.olx.bg/sport-knigi-hobi/sportni-stoki/velosipedi/q-"+input;
        String anibisQuery = "https://www.anibis.ch/fr/q/velos/?sorting=newest&page=1&query="+input;
        String milanuciosQuery = "https://www.milanuncios.com/bicicletas/?s="+input+"&demanda=n&vendedor=part&orden=relevance&fromSearch=1&fromSuggester=0&suggestionUsed=0&hitOrigin=listing&recentSearchShowed=0&recentSearchUsed=0";
        String ToriQuery = "https://www.tori.fi/recommerce/forsale/search?q="+input+"&sub_category=1.69.3963";


        return dbaQuery.replace(" ", "+") + "\n" +
                mercariQuery.replace(" ", "%20") + "\n" +
                wallapopQuery.replace(" ", "%20") + "\n" +
                finnNoQuery.replace(" ", "+") + "\n" +
                ebayKleinanzeigenQuery.replace(" ", "-") + "\n" +
                marktplaatsQuery.replace(" ", "+") + "\n" +
                bikemarktQuery.replace(" ", "-") + "\n" +
                trocVeloQuery.replace(" ", "+") + "\n" +
                willhabenQuery.replace(" ", "%20") + "\n" +
                ricardoQuery.replace(" ", "%20") + "\n" +
                happyRideQuery.replace(" ", "+") + "\n" +
                lebonCOinQuery.replace(" ", "%20") + "\n" +
                olxPolandQuery.replace(" ", "%20") + "\n" +
                olxPortugalQuery.replace(" ", "%20") + "\n" +
                olxUkraineQuery.replace(" ", "%20") + "\n" +
                anibisQuery.replace(" ", "%20") + "\n" +
                milanuciosQuery.replace(" ", "%20") + "\n" +
                ToriQuery.replace(" ", "%20") + "\n" +
                olxBulgariaQuery.replace(" ", "%20") + "\n" +
                njuskaloCroatiaQuery.replace(" ", "%20") + "\n";

    }
}
