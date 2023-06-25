package org.example;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example.interceptors.BaseHeadersInterceptor;
import org.example.services.MoviesService;
import org.example.services.SearchService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImbdApiClient {

    public SearchService searchService;
    public MoviesService moviesService;
    public ImbdApiClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new BaseHeadersInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build();

        searchService = retrofit.create(SearchService.class);
        moviesService = retrofit.create(MoviesService.class);
    }
}
