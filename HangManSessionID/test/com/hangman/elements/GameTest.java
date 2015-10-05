package com.hangman.elements;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

	final private static Integer KEY = 1;
	final private static String HINT = " _ _ _ _ _ ";
	final private static String ANSWER = "nepal";
	final private static String TRIED_LETTERS = "epal";

	@Test
	public void testNewGame() {
		Game game = new Game(KEY, new Answer(AnswerType.COUNTRY, ANSWER), HINT);
		assertNotNull(game.getKey());
		assertEquals(ANSWER, game.getAnswer().getValue());
		assertEquals(State.START, game.getState());
		assertEquals(HINT, game.getHint());
		assertEquals("", game.getTriedLetters());
	}

	@Test
	public void testOnGoingGame() {
		Game game = new Game(KEY,  new Answer(AnswerType.COUNTRY, ANSWER), State.ROPE, HINT, TRIED_LETTERS);
		assertEquals(KEY, game.getKey());
		assertEquals(State.ROPE, game.getState());
		assertEquals(ANSWER, game.getAnswer().getValue());
		assertEquals(HINT, game.getHint());
		assertEquals(TRIED_LETTERS, game.getTriedLetters());
	}

}
