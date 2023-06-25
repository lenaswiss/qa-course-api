package org.example;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example.interceptors.BaseHeadersInterceptor;
import org.example.interceptors.TrelloHeadersInterceptor;
import org.example.services.MoviesService;
import org.example.services.TrelloService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrelloApiClient {

    public TrelloService trelloService;
    public TrelloApiClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trello.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build();

        trelloService = retrofit.create(TrelloService.class);
    }
}
