package org.example.services;

import okhttp3.ResponseBody;
import org.example.models.TrelloBoard;
import org.example.models.TrelloCards;
import org.example.models.TrelloLists;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TrelloService {

    @POST("1/boards/")
    Call<TrelloBoard> createBoard(@Query("name") String name, @Query("key") String key, @Query("token") String token);

    @GET("1/boards/{id}")
    Call<TrelloBoard> getBoard(@Path("id") String id, @Query("key") String key, @Query("token") String token);

    @DELETE("1/boards/{id}")
    Call<ResponseBody> deleteBoard(@Path("id") String id, @Query("key") String key, @Query("token") String token);

    @POST("1/boards/{board_id}/lists")
    Call<TrelloLists> createList(@Path("board_id") String board_id, @Query("name") String name, @Query("key") String key, @Query("token") String token);

    @POST("1/cards")
    Call<TrelloCards> createCard(@Query("idList") String idList, @Query("key") String key, @Query("token") String token);

    @GET("1/cards/{cards_id}")
    Call<TrelloCards> getCard(@Path("cards_id") String cards_id, @Query("key") String key, @Query("token") String token);
}
