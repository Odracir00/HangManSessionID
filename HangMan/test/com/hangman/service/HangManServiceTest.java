package com.hangman.service;

import com.hangman.elements.State;
import com.hangman.elements.Country;
import com.hangman.elements.Game;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class HangManServiceTest {
    
    final private static String ID = "de325d54-75b4-434b-adb2-eb6b9e547119";
    final private static Integer KEY = 1;
    final private static String HINT = " _ _ _ _ _ ";
    final private static char BAD_NEW_LETTER = 'r';
    final private static String ANSWER = "nepal";
    final private static String TRIED_LETTERS = "epal";
    final private static String NEW_LINE = ";&lt;/br&gt;";
    
    HangManService service;
    
    public HangManServiceTest() {
        service = new HangManService();
    }
 
    @Ignore
    @Test
    public void testProcessRequest() {
        //TODO:
        // A good way to test this method would be to use for example mockit 
        // and verify that the methods
        //generateGame
        //game.processNewLetter(c);
        //updateGamesSummary();
        //createResponse();
        //There is no need for example to test "game.processNewLetter" 
        //because this has already been done in GameTest.
        service.processRequest(ID, KEY, State.RIGHT_ARM, HINT, BAD_NEW_LETTER, TRIED_LETTERS);
    }

    @Test
    public void testGenerateGame_NotNewGame() {
        service.generateGame(ID, KEY, State.RIGHT_ARM, HINT, BAD_NEW_LETTER, TRIED_LETTERS);
        
        Game game = service.getGame();
        assertEquals(ID, game.getId());
        assertEquals(KEY, game.getKey());
        assertEquals(State.RIGHT_ARM, game.getState());
        assertEquals(HINT, game.getHint());
    }
 
    @Test
    public void testGenerateGame_NewGame() {
        service.generateGame("", null, null, null, '\0', null);
        
        Game game = service.getGame();
        assertNotNull(game.getId());
        assertNotNull(game.getKey());
        assertNotNull(game.getAnswer().getName());
        assertEquals(State.START, game.getState());
        assertNotNull(game.getHint());
        assertEquals("", game.getTriedLetters());
    }
    
    @Ignore
    @Test
    public void testUpdateGamesSummary() {
        //TODO
        // A good way to test this method would be to use for example mockit 
        // and verify the correct method's calls
    }

    @Test
    public void testCreateResponse() {
        
        Game game = new Game(ID, KEY, new Country(ANSWER), State.FLOOR, HINT, TRIED_LETTERS);

        service = new HangManService(game);
        String gameResponse = service.createResponse();
        String expecteGameResponse = "<data>"
                + "<id>" + ID + "</id>"
                + "<key>" + KEY + "</key>"
                + "<state>" + State.FLOOR + "</state>"
                + "<hint>" + HINT + "</hint>"
                + "</data>";
        assertEquals(expecteGameResponse, gameResponse);
    }

    @Test
    public void testCreateGameSummary() {

        Game game = new Game(ID, KEY, new Country(ANSWER), State.FLOOR, HINT, TRIED_LETTERS);

        service = new HangManService(game);
        String gameSummary = service.createGameSummary(game);
        String expectedSummary = "Game ID:" + ID + NEW_LINE
                + "State:" + State.FLOOR + NEW_LINE
                + "Answer:" + ANSWER + NEW_LINE
                + "Hint:" + HINT + NEW_LINE
                + "Tried Letters:" + TRIED_LETTERS + NEW_LINE;
        assertEquals(expectedSummary, gameSummary);
    }
}



