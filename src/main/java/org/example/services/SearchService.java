package org.example.services;

import org.example.models.SearchMovie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchService {
    @GET("en/API/SearchMovie/{api_kye}/{expression}")
    Call<SearchMovie> getMovieByTitle(@Path(value="api_kye", encoded = true) String api_kye, @Path(value="expression", encoded = false) String expression);

    @GET("en/API/SearchAll/{api_kye}/{expression}")
    Call<SearchMovie> getSearchAll(@Path("api_kye") String api_kye, @Path("expression") String expression);

    @GET("API/AdvancedSearch/{api_kye}")
    Call<SearchMovie> getAdvancedSearch(@Path("api_kye") String api_kye, @Query("genres") String genres);
}
