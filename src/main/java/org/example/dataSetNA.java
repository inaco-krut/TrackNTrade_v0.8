package org.example;

public class dataSetNA {
    private final String input;

    public dataSetNA(String input) {
        this.input = input.toUpperCase();
    }

    public String generateQueries() {

        StringBuilder queries = new StringBuilder();

        String ebayQuery = "https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw="+input+"&_sacat=0&LH_TitleDesc=0&_odkw="+input+"&_osacat=0";
        String amazonQuery = "https://www.amazon.com/s?k="+input+"&ref=nb_sb_noss";

        queries.append(ebayQuery.replace(" ","+")).append("\n");
        queries.append(amazonQuery.replace(" ","+")).append("\n");


        return queries.toString();

    }
}
