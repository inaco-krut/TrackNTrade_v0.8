package org.example;

public class dataSetSEA {
    private final String input;

    public dataSetSEA(String input) {
        this.input = input.toUpperCase();
    }

    public String generateQueries() {

        StringBuilder queries = new StringBuilder();


        String carousellQuery = "https://www.carousell.com.hk/search/"+input+"?addRecent=true&canChangeKeyword=true&includeSuggestions=true&searchId=KXpPzI";

        queries.append(carousellQuery.replace(" ","+%20")).append("\n");


        System.out.println(queries);

        return queries.toString();

    }
}
