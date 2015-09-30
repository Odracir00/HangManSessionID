package com.hangman.service;

import com.hangman.data.AnswersData;
import com.hangman.elements.Answer;
import com.hangman.elements.Game;
import com.hangman.elements.GamesSummary;
import com.hangman.elements.State;
import java.util.UUID;

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

    public String processRequest(String id) {
        return processRequest(id, null, null, null, '\0', null);
    }

    public String processRequest(String id, Integer key, State state, 
            String hint, char c, String triedLetters) {
        generateGame(id, key, state, hint, c, triedLetters);
        if (!"".equals(id) && c != '\0') {
            game.processNewLetter(c);
        }
        updateGamesSummary();

        String response = createResponse();
        return response;
    }

    void generateGame(String id, Integer key, State state,
            String hint, char c, String triedLetters) {

        if ("".equals(id)) {    // It is a new game
            String newId = UUID.randomUUID().toString();
            Integer newKey = AnswersData.getRandomAnswerId();
            Answer answer = AnswersData.getAnswerFromId(newKey);
            game = new Game(newId, newKey, answer);
        } else {
            Answer answer = AnswersData.getAnswerFromId(key);
            game = new Game(id, key, answer, state, hint, triedLetters);
        }
    }

    void updateGamesSummary() {
        State newGameState = game.getState();
        if (newGameState == State.RIGHT_LEG || newGameState == State.SUCCESS) {
            gamesSummary.deleteGameById(game.getId());
        } else { // add or update
            String summary = createGameSummary(game);
            gamesSummary.addorUpdateGameById(game.getId(), summary);
        }
    }

    String createResponse() {
        String newGameResponseData = "<data>"
                + "<id>" + game.getId() + "</id>"
                + "<key>" + game.getKey() + "</key>"
                + "<state>" + game.getState() + "</state>"
                + "<hint>" + game.getHint() + "</hint>"
                + "</data>";
        return newGameResponseData;
    }

    String createGameSummary(Game game) {

        String gameSummary = "Game ID:" + game.getId() + NEW_LINE
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
