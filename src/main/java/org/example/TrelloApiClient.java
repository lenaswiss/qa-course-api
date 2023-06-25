package org.example;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example.interceptors.BaseHeadersInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrelloApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://trello.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
