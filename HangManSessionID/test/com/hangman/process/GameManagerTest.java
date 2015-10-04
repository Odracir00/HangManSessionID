package com.hangman.process;

import org.junit.Test;

import com.hangman.elements.Country;
import com.hangman.elements.Game;
import com.hangman.elements.State;

import static org.junit.Assert.*;

import org.junit.Ignore;

public class GameManagerTest {

    final private static Integer KEY = 1;
    final private static String HINT = " _ _ _ _ _ ";
    final private static String HINT4 = " n e p a l ";
    final private static char GOOD_NEW_LETTER = 'p';
    final private static char BAD_NEW_LETTER = 'r';
    final private static String ANSWER = "nepal";
    final private static String TRIED_LETTERS = "epal";

    final private static String NEW_LINE = ";&lt;/br&gt;";

    GameManager gameManager = new GameManager();
    
    @Test
    public void testCreateHint() {
        assertEquals(HINT, gameManager.createEmptyHint(ANSWER));
    }
    
    @Test
    public void testGetGame_NotNewGame() {
    	Game game = gameManager.getGame(KEY, State.RIGHT_ARM, HINT, BAD_NEW_LETTER, TRIED_LETTERS);
         
        assertEquals(KEY, game.getKey());
        assertEquals(State.RIGHT_ARM, game.getState());
        assertEquals(HINT, game.getHint());
    }

	@Test
	public void testGetGame_NewGame() {
		Game game = gameManager.getGame(null, null, null, '\0', null);

		assertNotNull(game.getKey());
		assertNotNull(game.getAnswer().getName());
		assertEquals(State.START, game.getState());
		assertNotNull(game.getHint());
		assertEquals("", game.getTriedLetters());
	}

	@Test
	public void testProcessNewLetter_HitAndSuccess() {
		Game game = new Game(KEY, new Country(ANSWER), State.ROPE, " n e _ a l ", TRIED_LETTERS);
		gameManager.processNewLetter(game, GOOD_NEW_LETTER);

		assertEquals(KEY, game.getKey());
		assertEquals(State.SUCCESS, game.getState());
		assertEquals(HINT4, game.getHint());
	}

    @Test
    public void testProcessNewLetter_HitAndNotSuccess() {
        Game game = new Game(KEY, new Country(ANSWER), State.ROPE, " _ e _ a l ", TRIED_LETTERS);
        gameManager.processNewLetter(game, GOOD_NEW_LETTER);

        assertEquals(KEY, game.getKey());
        assertEquals(State.ROPE, game.getState());
        assertEquals(" _ e p a l ", game.getHint());
    }

    @Test
    public void testProcessNewLetter_MissAndGameOver() {
        Game game = new Game(KEY, new Country(ANSWER), State.LEFT_LEG, " _ e _ a l ", TRIED_LETTERS);
        gameManager.processNewLetter(game, BAD_NEW_LETTER);

        assertEquals(KEY, game.getKey());
        assertEquals(State.RIGHT_LEG, game.getState());
        assertEquals(" n e p a l ", game.getHint());
    }

    @Test
    public void testProcessNewLetter_MissAndNotGameOver() {
        Game game = new Game(KEY, new Country(ANSWER), State.RIGHT_ARM, " _ e _ a l ", TRIED_LETTERS);
        gameManager.processNewLetter(game, BAD_NEW_LETTER);

        assertEquals(KEY, game.getKey());
        assertEquals(State.LEFT_LEG, game.getState());
        assertEquals(" _ e _ a l ", game.getHint());
    }

	@Test
	public void testUpdateStateFromStartToFloor() {
		Game game = new Game(KEY, new Country(ANSWER), State.START, HINT, TRIED_LETTERS);
		gameManager.updateState(game);
		assertEquals(State.FLOOR, game.getState());
	}

	@Test
	public void testUpdateStateFromFloorToVerticalbar() {
		Game game = new Game(KEY, new Country(ANSWER), State.FLOOR, HINT, TRIED_LETTERS);
		gameManager.updateState(game);
		assertEquals(State.VERTICAL_BAR, game.getState());
	}

	@Ignore
	@Test
	public void testUpdateGamesSummary() {
		// TODO
	}

	@Test
	public void testCreateGameSummary() {
		Game game = new Game(KEY, new Country(ANSWER), State.FLOOR, HINT, TRIED_LETTERS);
		String gameSummary = gameManager.createGameSummary(game);
		String expectedSummary = "Game ID:" + 0 + NEW_LINE + "State:" + State.FLOOR + NEW_LINE + "Answer:" + ANSWER
				+ NEW_LINE + "Hint:" + HINT + NEW_LINE + "Tried Letters:" + TRIED_LETTERS + NEW_LINE;
		assertEquals(expectedSummary, gameSummary);
	}

}
