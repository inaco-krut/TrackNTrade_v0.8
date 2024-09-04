package org.example;

public class dataSetSEA {
    private final String input;

    public dataSetSEA(String input) {
        this.input = input.toUpperCase();
    }

    public String generateQueries() {


        String carousellQuery = "https://www.carousell.com.hk/search/"+input+"?addRecent=true&canChangeKeyword=true&includeSuggestions=true&searchId=KXpPzI";


        return carousellQuery.replace(" ", "%20") + "\n";

    }
}
