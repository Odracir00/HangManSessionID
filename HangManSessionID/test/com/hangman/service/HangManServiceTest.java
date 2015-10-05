package com.hangman.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hangman.elements.Answer;
import com.hangman.elements.AnswerType;
import com.hangman.elements.Country;
import com.hangman.elements.Game;
import com.hangman.elements.GamesSummary;
import com.hangman.elements.State;

public class HangManServiceTest {
    
    final private static String SESSION_ID = "de325d54-75b4-434b-adb2-eb6b9e547119";
    final private static Integer KEY = 1;
    final private static String HINT = " _ _ _ _ _ ";
    final private static char BAD_NEW_LETTER = 'r';
    final private static String ANSWER = "nepal";
    final private static String TRIED_LETTERS = "epal";
    
	HangManService service;


	public HangManServiceTest() {
		 service = new HangManService();
    }
 
    @Test
    public void testProcessRequest() {
        //TODO: add mockito
        service.processRequest(SESSION_ID, KEY, State.RIGHT_ARM, HINT, BAD_NEW_LETTER, TRIED_LETTERS);

        cleanUpSummaries();
    }

	private static void cleanUpSummaries() {
		GamesSummary.getInstance().deleteAllGames();
	}
    
    @Test
    public void testCreateResponse() {
        
        Game game = new Game(KEY, new Answer(AnswerType.COUNTRY, ANSWER), State.FLOOR, HINT, TRIED_LETTERS);

        service = new HangManService();
        String gameResponse = service.createResponse(game);
        String expecteGameResponse = "<data>"
                + "<id>" + 0 + "</id>"
                + "<key>" + KEY + "</key>"
                + "<state>" + State.FLOOR + "</state>"
                + "<hint>" + HINT + "</hint>"
                + "<letters>" + TRIED_LETTERS + "</letters>"
                + "</data>";
        assertEquals(expecteGameResponse, gameResponse);
    }

}



