package org.example;

import org.example.models.Movie;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.security.SecureRandom;

public class MoviesListTest {

    ImbdApiClient apiClient = new ImbdApiClient();
    Assertion assertion = new Assertion();
    // public static final String API_KYE = "k_41npf8kq";
    public static final String API_KYE = "k_83pv5z04";

    /**
     * Что нам нужно  - автотест, который
     * Будет заходить на IMDB (https://www.imdb.com/)
     * Открывать список топ фильмов IMDb Top 250 Movies
     * Собирать список топ 5 фильмов - их название, рейтинг и год выхода
     * Сохранять этом список обьектами типа Movies, которые имеют поля Position, Title, Year, Rating
     * Проверять что в нем есть “Крестный отец”
     * Переходить на страницу фильма  “Крестный отец” и проверять, что данные
     * (год, название и рейтинг) отображаются те же что и на странице списка фильмов
     */
    @Test
    public void top5MoviesTest() throws IOException {
        var topFiveMoviesList = apiClient.moviesService.getTop250Movies(API_KYE).execute().body().movies.subList(0, 5);
        Movie targetedMovie = new Movie();
        for (Movie m : topFiveMoviesList) {
            if (m.title.toLowerCase().contains("godfather")) {
                targetedMovie = m;
                break;
            }
        }
        var targetedMovieTitle = apiClient.moviesService.getItemTitle(API_KYE, targetedMovie.id).execute().body();
        assertion.assertTrue(targetedMovieTitle.title.equals(targetedMovie.title),
                String.format("Titles are not equal. Expected %s, but found %s", targetedMovie.title, targetedMovieTitle.title));
        assertion.assertTrue(targetedMovieTitle.year == targetedMovie.year,
                String.format("Years are not equal. Expected %s, but found %s", targetedMovieTitle.year, targetedMovie.year));
        assertion.assertTrue(targetedMovieTitle.ratings.ratings == targetedMovie.rating,
                "The rating of the movie in the top 5 Movies is not equal to the movie rating on the movie title page.");
    }

    @Test
    public void movieInfoTest() throws IOException {
        var randomMovie = apiClient.moviesService.getTop250Movies(API_KYE).execute().body()
                .movies.get(new SecureRandom().nextInt(250));
        var randomMovieTitle = apiClient.moviesService.getItemTitle(API_KYE, randomMovie.id);

    }
}
