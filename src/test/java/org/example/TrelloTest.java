package org.example;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class TrelloTest {

    TrelloApiClient apiClient = new TrelloApiClient();
    Assertion assertion = new Assertion();
    private static final String API_KEY = "k_41npf8kq";

    /**
     * Complex task 6*
     * Necessary data:
     * API URL: Manage Your Team’s Projects From Anywhere | Trello
     * Key: d3b603fdaa559811b46ccb23a48b77b6
     * Token: 140f3dd7e297b0a11906e71f55a835296304f59f8601e81ad419fa14de959176
     *
     * For Trello(What is Trello?) API create automated test for next case
     * (so you need to create one more API  client for this App):
     * Create a new Board via API.
     * Check, that response is successful and check that name of the board equals to name, that you added in process of creation.
     * Add a Card to list ‘To Do’ with any name.
     * Check, that response is successful and check, that name equals to name, that you added in process of creation.
     * Check that added card has added name.
     * Delete board and check that board does not exist.
     * All links to the documentation are added to the test case.
     */
    @Test
    public void createBoardTest(){
    }
}
