package com.hangman.service;

import com.hangman.data.AnswersData;
import com.hangman.elements.Answer;
import com.hangman.elements.Game;
import com.hangman.elements.GamesSummary;
import com.hangman.elements.State;

/**
 * This class is responsible for processing the business logic associated to the
 * HangManServlet.
 */
public class HangManService {

    final private static String NEW_LINE = ";&lt;/br&gt;";
    
    GamesSummary gamesSummary = GamesSummary.getInstance();;

    Game game;
    
    public HangManService() {
    }
    
    // Used for testing only -> Package visibility
    HangManService(Game game) {
        this.game = game;
    }

    public String processRequest(String sessionId) {
        return processRequest( sessionId, null, null, null, '\0', "");
    }
    
    public String processRequest(String sessionId, Integer key, State state, 
            String hint, char c, String triedLetters) {
        generateGame(key, state, hint, c, triedLetters); // first arg to be removed
        if (key != null && c != '\0') {
            game.processNewLetter(c);
        }
        updateGamesSummary(sessionId);

        String response = createResponse();
        return response;
    }

    void generateGame(Integer key, State state, String hint, char c, String triedLetters) {

        if (key == null) {    // It is a new game
            Integer newKey = AnswersData.getRandomAnswerId();
            Answer answer = AnswersData.getAnswerFromId(newKey);
            game = new Game(newKey, answer);
        } else {
            Answer answer = AnswersData.getAnswerFromId(key);
            game = new Game(key, answer, state, hint, triedLetters);
        }
    }

    void updateGamesSummary(String sessionId) {
        State newGameState = game.getState();
        if (newGameState == State.RIGHT_LEG || newGameState == State.SUCCESS) {
            gamesSummary.deleteGameById(sessionId);
        } else { // add or update
            String summary = createGameSummary(game);
            gamesSummary.addorUpdateGameById(sessionId, summary);
        }
    }

    String createResponse() {
        String newGameResponseData = "<data>"
                + "<id>" + game.getId() + "</id>"
                + "<key>" + game.getKey() + "</key>"
                + "<state>" + game.getState() + "</state>"
                + "<hint>" + game.getHint() + "</hint>"
                + "<letters>" + game.getTriedLetters() + "</letters>"
                + "</data>";
        return newGameResponseData;
    }

    String createGameSummary(Game game) {

        String gameSummary = "Game:" + NEW_LINE
                + "State:" + game.getState() + NEW_LINE
                + "Answer:" + game.getAnswer().getName() + NEW_LINE
                + "Hint:" + game.getHint() + NEW_LINE
                + "Tried Letters:" + game.getTriedLetters() + NEW_LINE;

        return gameSummary;
    }

    // Used for testing only -> Package visibility
    Game getGame() { //test only
        return game;
    }
}
