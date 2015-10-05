package com.hangman.service;

import com.hangman.data.DAOCompGamesManager;
import com.hangman.elements.Game;
import com.hangman.elements.State;
import com.hangman.process.GameManager;

/**
 * This class is responsible for processing the business logic associated to the
 * HangManServlet.
 */
public class HangManService {
    
    GameManager gameManager = new GameManager();

	DAOCompGamesManager daoCompGamesManager = new DAOCompGamesManager();
    
    public HangManService() {
    }

	public String processRequest(String sessionId, Integer key, State state, String hint, char c, String triedLetters) {

		Game g = gameManager.getGame(key, state, hint, c, triedLetters);
		if (key != null) {
			gameManager.processNewLetter(g, c);
		}
		gameManager.updateGamesSummary(sessionId, g);

		if (g.getState() == State.RIGHT_LEG || g.getState() == State.SUCCESS) {
			daoCompGamesManager.addGame(g); // saving game
		}

		return createResponse(g);
	}

    String createResponse(Game game) {
        String newGameResponseData = "<data>"
                + "<id>" + game.getId() + "</id>"
                + "<key>" + game.getKey() + "</key>"
                + "<state>" + game.getState() + "</state>"
                + "<hint>" + game.getHint() + "</hint>"
                + "<letters>" + game.getTriedLetters() + "</letters>"
                + "</data>";
        return newGameResponseData;
    }

}
