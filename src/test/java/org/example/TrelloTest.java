package org.example;

import org.example.models.TrelloBoard;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class TrelloTest {

    TrelloApiClient apiClient = new TrelloApiClient();
    private final static String KEY = "d3b603fdaa559811b46ccb23a48b77b6";
    private final static String BOARD_NAME = "testNewBoard" + getDefaultTimestamp();
    private final static String LIST_NAME = "To Do";
    private static String createdBoardId;
    private static String createdListId;
    private static String createdCardId;
    private final static String TOKEN = "140f3dd7e297b0a11906e71f55a835296304f59f8601e81ad419fa14de959176";
    Assertion assertion = new Assertion();

    /**
     * Complex task 6*
     * Necessary data:
     * API URL: Manage Your Team’s Projects From Anywhere | Trello
     * Key: d3b603fdaa559811b46ccb23a48b77b6
     * Token: 140f3dd7e297b0a11906e71f55a835296304f59f8601e81ad419fa14de959176
     * <p>
     * For Trello(What is Trello?) API create automated test for next case
     * (so you need to create one more API  client for this App):
     * Create a new Board via API.
     * Check, that response is successful and check that name of the board equals to name, that you added in process of creation.
     */
    @Test
    public void createBoardTest() throws IOException {
        var newBord = apiClient.trelloService.createBoard(BOARD_NAME, KEY, TOKEN).execute();
        createdBoardId = newBord.body().id;
        assertion.assertTrue(newBord.isSuccessful(), "Request success failed");
        assertion.assertTrue(newBord.body().name.equals(BOARD_NAME), "Invalid board name. Expected name " + BOARD_NAME + " but found " + newBord.body().name);
    }

    /**
     * Add a Card to list ‘To Do’ with any name.
     * Check, that response is successful and check, that name equals to name, that you added in process of creation.
     * Check that added card has added name.
     */
    @Test(dependsOnMethods = "createBoardTest")
    public void addCartTes() throws IOException {
        var toDoList = apiClient.trelloService.createList(createdBoardId, LIST_NAME, KEY, TOKEN).execute();
        assertion.assertTrue(toDoList.isSuccessful(), "List was not created.");
        createdListId = toDoList.body().id;
        var newCartInToDoList = apiClient.trelloService.createCard(createdListId, KEY, TOKEN).execute();
        assertion.assertTrue(newCartInToDoList.isSuccessful(), "Cart was not created in the ToDo list.");
        assertion.assertEquals(newCartInToDoList.body().idList, createdListId, "Cart was created in the wrong List.");
        assertion.assertEquals(newCartInToDoList.body().idBoard, createdBoardId, "Wrong board ID.");
        apiClient.trelloService.deleteBoard(createdBoardId, KEY, TOKEN).execute().body();
        assertion.assertTrue(apiClient.trelloService.getCard(newCartInToDoList.body().id, KEY, TOKEN).execute().code() == 404, "The card was not removed after the board deleting.");
    }

    /**
     * Delete board and check that board does not exist.
     */
    @Test(dependsOnMethods = "createBoardTest")
    public void deleteBoardTest() throws IOException {
        var deleteBoardResponse = apiClient.trelloService.deleteBoard(createdBoardId, KEY, TOKEN).execute();
        assertion.assertTrue(deleteBoardResponse.code() == 200, "Board was not deleted.");
        System.out.print(deleteBoardResponse.body().toString());
        var getDeletedBoard = apiClient.trelloService.getBoard(createdBoardId, KEY, TOKEN).execute();
        assertion.assertTrue(getDeletedBoard.code() == 404, "Deleted board was found. Expected response 404 failed.");
        assertion.assertTrue(getDeletedBoard.message().isEmpty(), "Response is not empty as expected.");
    }

    public static long getDefaultTimestamp() {
        return new Timestamp(new Date().getTime()).getTime();
    }
}
