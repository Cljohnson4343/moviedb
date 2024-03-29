
package com.example.movie_db.data.tmdb;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenreResponse {

    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GenreResponse() {
    }

    /**
     * 
     * @param genres
     */
    public GenreResponse(List<Genre> genres) {
        super();
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}
