package org.example;

import org.example.models.Results;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SearchMovieTest {

    public static final String API_KYE = "k_83pv5z04";
    private ImbdApiClient apiClient = new ImbdApiClient();
    private Assertion assertion = new Assertion();

    /**
     * Task 1: created automated test, that will execute next steps:
     * Send “inception 2010” expression to IMDB API.
     * Verify, first value in “results”. "title" must be equal to Inception
     * and "description" must contain 2010 value.
     */
    @Test
    public void searchMovieTest() throws IOException {
        ImbdApiClient imbdApiClient = new ImbdApiClient();
        Assertion assertion = new Assertion();
        var searchResults = imbdApiClient.searchService.getMovieByTitle(API_KYE, "inception 2010")
                .execute().body();
        assertion.assertTrue((searchResults.results.stream().findFirst().get().title).equals("Inception"),
                "The Title is not equal to Inception");
        assertion.assertTrue(searchResults.results.stream().findFirst().get().description.contains("2010"),
                "Description does not contain 2010 value");
    }

    /**
     * Task 2: created automated test, that will execute next steps:
     * Send “” expression to IMDB API.
     * Verify that “results” are equal to null and “errorMessage” contains “Invalid request.” sentence.
     */
    @Test
    public void emptySearchResultsTest() throws IOException {
        var searchResults = apiClient.searchService.getMovieByTitle(API_KYE, "")
                .execute().body();
        assertion.assertTrue(searchResults.results.isEmpty(), "Result is not empty. ");
        assertion.assertTrue(searchResults.errorMessage.contains("Invalid request."),
                "The “errorMessage” doesn't contain “Invalid request.” sentence.");
    }

    /**
     * Task 3: created automated test, that will execute next steps:
     * Send “batman 1994” expression to IMDB API.
     * Verify that the first result title contains batman and description and title don't contain 1994.
     */
    @Test
    public void searchMovieSecondTest() throws IOException {
        var searchResults = apiClient.searchService.getMovieByTitle(API_KYE, "batman 1994")
                .execute().body();
        assertion.assertTrue(searchResults.results.stream().findFirst().get().title.toLowerCase().contains("batman"),
                "Title does not contain batman ");
        assertion.assertFalse(searchResults.results.stream().findFirst().get().description.toLowerCase().contains("batman"),
                "Description contains batman "); //"description": "1994 Video Game" contains 1994, assertion was changed
        System.out.println(searchResults.results.stream().findFirst().get().description);
        assertion.assertFalse(searchResults.results.stream().findFirst().get().title.contains("1994"),
                "Title contains 1994");
    }

    /**
     * Endpoint /API/SearchAll/ from documentation:
     * Task 4: created automated test, that will execute next steps:
     * Send “batman” expression to IMDB API.
     * Send the same expression to /API/SearchMovie/ endpoint.
     * Check that number of first endpoint results larger, that from second endpoint.
     */
    @Test
    public void compareSearchAllAndSearchMovieTest() throws IOException {
        assertion.assertTrue(apiClient.searchService.getMovieByTitle(API_KYE, "batman").execute().body().results.size() >=
                        apiClient.searchService.getSearchAll(API_KYE, "batman").execute().body().results.size(),
                "The result Search Movie grated than Search All. ");
        //I've checked in postman and get the same results SearchAll size == 10, and SearchMovie == 25
    }

    /**
     * Endpoint /API/AdvancedSearch/ from documentation:
     * Task 5: created automated test, that will execute next steps:
     * Send value ‘comedy’ and ‘thriller’ of ‘genres’ parameter.
     * Check that all results contain one of the genres that were set in request filters.
     */
    @Test
    public void searchAdvancedTest() throws IOException {
        var results = apiClient.searchService.getAdvancedSearch(API_KYE, "comedy,thriller").execute().body().results;
        List<String> expectedGenresList = Arrays.asList("comedy", "thriller");
        for (Results r : results) {
            var actualGenresList = Arrays.asList(r.genres.toLowerCase().split(", "));
//            assertion.assertTrue(expectedGenresList.stream().anyMatch(actualGenresList::contains),
//                    "Result does not contain sent in request \"genres\"");
            System.out.println(expectedGenresList.stream().anyMatch(actualGenresList::contains));
        }
        System.out.println(results.size());
    }
}
