package com.hangman.service;

import com.hangman.elements.GamesSummary;
import org.junit.Test;
import static org.junit.Assert.*;

public class MonitorServiceTest {

    final private static String ID1 = "1";
    final private static String ID2 = "2";
    final private static String SUMMARY1 = "Summary one";
    final private static String SUMMARY2 = "Summary two";

    private final MonitorService service = new MonitorService();

    @Test
    public void testCreateGamesReport() {
        GamesSummary gamesSummary = GamesSummary.getInstance();
        gamesSummary.addorUpdateGameById(ID1, SUMMARY1);
        gamesSummary.addorUpdateGameById(ID2, SUMMARY2);

        String expectedReport = "<games><game>" + SUMMARY1 + "</game><game>" + SUMMARY2 + "</game></games>";
        String report = service.createGamesReport();
        assertEquals(expectedReport, report);
    }
}
