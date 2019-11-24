
package com.example.movie_db.data.tmdb;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularMovies {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<PopularMovieBrief> results = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PopularMovies() {
    }

    /**
     * 
     * @param totalResults
     * @param totalPages
     * @param page
     * @param results
     */
    public PopularMovies(Integer page, Integer totalResults, Integer totalPages, List<PopularMovieBrief> results) {
        super();
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<PopularMovieBrief> getResults() {
        return results;
    }

    public void setResults(List<PopularMovieBrief> results) {
        this.results = results;
    }
}
