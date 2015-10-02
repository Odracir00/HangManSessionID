package com.hangman.elements;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    final private static Integer KEY = 1;
    final private static String HINT = " _ _ _ _ _ ";
    final private static String HINT4 = " n e p a l ";
    final private static char GOOD_NEW_LETTER = 'p';
    final private static char BAD_NEW_LETTER = 'r';
    final private static String ANSWER = "nepal";
    final private static String TRIED_LETTERS = "epal";

    @Test
    public void testNewGame() {
        Game game = new Game(KEY, new Country(ANSWER));
        assertNotNull(game.getKey());
        assertNotNull(game.getAnswer().getName());
        assertEquals(State.START, game.getState());
        assertNotNull(game.getHint());
        assertEquals("", game.getTriedLetters());
    }

    @Test
    public void testOnGoingGame() {
        Game game = new Game(KEY, new Country(ANSWER), State.ROPE, HINT, TRIED_LETTERS);
        assertEquals(KEY, game.getKey());
        assertEquals(State.ROPE, game.getState());
        assertNotNull(game.getAnswer().getName());
        assertEquals(HINT, game.getHint());
        assertEquals(TRIED_LETTERS, game.getTriedLetters());
    }

    @Test
    public void testCreateHint() {
        Game game = new Game(KEY, new Country(ANSWER));
        game.createHint(ANSWER);
        assertEquals(HINT, game.getHint());
    }

    @Test
    public void testProcessNewLetter_HitAndSuccess() {
        Game game = new Game(KEY, new Country(ANSWER), State.ROPE, " n e _ a l ", TRIED_LETTERS);
        game.processNewLetter(GOOD_NEW_LETTER);

        assertEquals(KEY, game.getKey());
        assertEquals(State.SUCCESS, game.getState());
        assertEquals(HINT4, game.getHint());
    }

    @Test
    public void testProcessNewLetter_HitAndNotSuccess() {
        Game game = new Game(KEY, new Country(ANSWER), State.ROPE, " _ e _ a l ", TRIED_LETTERS);
        game.processNewLetter(GOOD_NEW_LETTER);

        assertEquals(KEY, game.getKey());
        assertEquals(State.ROPE, game.getState());
        assertEquals(" _ e p a l ", game.getHint());
    }

    @Test
    public void testProcessNewLetter_MissAndGameOver() {
        Game game = new Game(KEY, new Country(ANSWER), State.LEFT_LEG, " _ e _ a l ", TRIED_LETTERS);
        game.processNewLetter(BAD_NEW_LETTER);

        assertEquals(KEY, game.getKey());
        assertEquals(State.RIGHT_LEG, game.getState());
        assertEquals(" n e p a l ", game.getHint());
    }

    @Test
    public void testProcessNewLetter_MissAndNotGameOver() {
        Game game = new Game(KEY, new Country(ANSWER), State.RIGHT_ARM, " _ e _ a l ", TRIED_LETTERS);
        game.processNewLetter(BAD_NEW_LETTER);

        assertEquals(KEY, game.getKey());
        assertEquals(State.LEFT_LEG, game.getState());
        assertEquals(" _ e _ a l ", game.getHint());
    }

    @Test
    public void testUpdateStateFromStartToFloor() {
        Game game = new Game(KEY, new Country(ANSWER), State.START, HINT, TRIED_LETTERS);
        game.updateState();
        assertEquals(State.FLOOR, game.getState());
    }

    @Test
    public void testUpdateStateFromFloorToVerticalbar() {
        Game game = new Game(KEY, new Country(ANSWER), State.FLOOR, HINT, TRIED_LETTERS);
        game.updateState();
        assertEquals(State.VERTICAL_BAR, game.getState());
    }
    // ... and so on for all the other states
}
