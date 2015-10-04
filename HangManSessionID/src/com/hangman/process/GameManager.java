package com.hangman.process;

import com.hangman.data.AnswersData;
import com.hangman.elements.Answer;
import com.hangman.elements.Game;
import com.hangman.elements.GamesSummary;
import com.hangman.elements.State;

public class GameManager {

    final private static String NEW_LINE = ";&lt;/br&gt;";
    
    GamesSummary gamesSummary = GamesSummary.getInstance();
	
	public Game getGame(Integer key, State state, String hint, char c, String triedLetters) {
		
		Game game;
        if (key == null) {    // It is a new game
            Integer newKey = AnswersData.getRandomAnswerId();
            Answer answer = AnswersData.getAnswerFromId(newKey);
            game = new Game(newKey, answer, createEmptyHint(answer.getName()));
        } else {
            Answer answer = AnswersData.getAnswerFromId(key); 
            // acho qure mais tarde isto pode passar tb para o sessiID
            game = new Game(key, answer, state, hint, triedLetters);
        }
        
		return game;
	}
	
	String createEmptyHint(String ans) {
		String emptyhint = " ";
		for (int i = 0; i < ans.length(); i++) {
			emptyhint += "_ ";
		}
		return emptyhint;
	}
	
	/////////////////////////////////////////
	
	public void processNewLetter(Game g, char c) {

		g.setTriedLetters(g.getTriedLetters() + String.valueOf(c));

		boolean hit = false;
		boolean success = false;
		char[] answerArray = g.getAnswer().getName().toCharArray();
		char[] hintArray = g.getHint().replaceAll(" ", "").toCharArray();

		for (int i = 0; i < answerArray.length; i++) {
			if (answerArray[i] == c) {
				hintArray[i] = c;
				hit = true;
			}
		}

		if (!(new String(hintArray).contains("_"))) {
			success = true;
		}

		if (hit) {
			g.setHint(createHintFromArray(hintArray));
			if (success) {
				g.setState(State.SUCCESS);
			}
		} else {
			updateState(g);
			if (g.getState() == State.RIGHT_LEG) { // Game over
				g.setHint(createHintFromArray(answerArray));
			} else {
				g.setHint(createHintFromArray(hintArray));
			}
		}

	}

	private String createHintFromArray(char[] array) {
		String hint = " ";
		for (int i = 0; i < array.length; i++) {
			hint += array[i] + " ";
		}
		return hint;
	}

	// default to facilitate testing, otherwise this method should be private
	void updateState(Game g) {
		if (g.getState() == State.START) {
			g.setState(State.FLOOR);
		} else if (g.getState() == State.FLOOR) {
			g.setState(State.VERTICAL_BAR);
		} else if (g.getState() == State.VERTICAL_BAR) {
			g.setState(State.HORIZONTAL_BAR);
		} else if (g.getState() == State.HORIZONTAL_BAR) {
			g.setState(State.ROPE);
		} else if (g.getState() == State.ROPE) {
			g.setState(State.HEAD);
		} else if (g.getState() == State.HEAD) {
			g.setState(State.BODY_TRUNK);
		} else if (g.getState() == State.BODY_TRUNK) {
			g.setState(State.LEFT_ARM);
		} else if (g.getState() == State.LEFT_ARM) {
			g.setState(State.RIGHT_ARM);
		} else if (g.getState() == State.RIGHT_ARM) {
			g.setState(State.LEFT_LEG);
		} else if (g.getState() == State.LEFT_LEG) {
			g.setState(State.RIGHT_LEG);
		}
	}
	
    public void updateGamesSummary(String sessionId, Game game) {
        State newGameState = game.getState();
        if (newGameState == State.RIGHT_LEG || newGameState == State.SUCCESS) {
            gamesSummary.deleteGameById(sessionId);
        } else { // add or update
            String summary = createGameSummary(game);
            gamesSummary.addorUpdateGameById(sessionId, summary);
        }
    }
    
    String createGameSummary(Game game) {
        String gameSummary = "Game ID:" + game.getId() + NEW_LINE
                + "State:" + game.getState() + NEW_LINE
                + "Answer:" + game.getAnswer().getName() + NEW_LINE
                + "Hint:" + game.getHint() + NEW_LINE
                + "Tried Letters:" + game.getTriedLetters() + NEW_LINE;
        return gameSummary;
    }
	
}
