package com.dpconde.bouquet.mvp.data.model;

/**
 * Created by dpconde on 29/9/18.
 */

public class ApiInfo {


    private String seed;
    private Integer results;
    private Integer page;
    private String version;

    public ApiInfo() {
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
