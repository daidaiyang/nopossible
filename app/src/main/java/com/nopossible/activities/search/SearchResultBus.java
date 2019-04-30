package com.nopossible.activities.search;

public class SearchResultBus {

    private String result;

    public SearchResultBus(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
