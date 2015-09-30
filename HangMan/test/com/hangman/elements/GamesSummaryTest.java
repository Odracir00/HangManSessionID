package com.hangman.elements;

import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;


public class GamesSummaryTest {
    
    final private static String ID1 = "18";
    final private static String ID2 = "19";
    final private static String SUMMARY_A = "Summary A";
    final private static String SUMMARY_B = "Summary B";
    final private static String SUMMARY_C = "Summary C";
    
    GamesSummary uniqueInstance = GamesSummary.getInstance();;

    @After
    public void tearDown() {
        // Ensure that the map is left empty after each test
        uniqueInstance.getCurrentGames().clear();
    }

    public GamesSummaryTest() {
    }
    
    
    @Test
    public void testAddorUpdateGameById_add() {
        uniqueInstance.addorUpdateGameById(ID1, SUMMARY_A);
        uniqueInstance.addorUpdateGameById(ID2, SUMMARY_B);
        Map<String, String> summaries = uniqueInstance.getCurrentGames();
        
        assertEquals(2, summaries.size());
        assertEquals(SUMMARY_A, summaries.get(ID1));
        assertEquals(SUMMARY_B, summaries.get(ID2));
    }
    
    @Test
    public void testAddorUpdateGameById_update() {
        uniqueInstance.addorUpdateGameById(ID1, SUMMARY_A);
        uniqueInstance.addorUpdateGameById(ID2, SUMMARY_B);
        uniqueInstance.addorUpdateGameById(ID2, SUMMARY_C);
        
        Map<String, String> summaries = uniqueInstance.getCurrentGames();
        
        assertEquals(2, summaries.size());
        assertEquals(SUMMARY_A, summaries.get(ID1));
        assertEquals(SUMMARY_C, summaries.get(ID2));
    }
    
    @Test
    public void testDeleteGameById() {
        Map<String, String> summariesMap = uniqueInstance.getCurrentGames();
        addSummaries(summariesMap);

        uniqueInstance.deleteGameById(ID1);

        assertEquals(1, summariesMap.size());
        assertEquals(null, summariesMap.get(ID1));
        assertEquals(SUMMARY_B, summariesMap.get(ID2));
    }
 
    @Test
    public void testGetAllSummries() {
        Map<String, String> summariesMap = uniqueInstance.getCurrentGames();
        addSummaries(summariesMap);

        List<String> summaries = uniqueInstance.getAllSummries();

        assertEquals(2, summaries.size());
        assertTrue(summaries.contains(SUMMARY_A));
        assertTrue(summaries.contains(SUMMARY_B));
    }

    @Test
    public void testGetAllSummries_deepCopy() {
        Map<String, String> summariesMap = uniqueInstance.getCurrentGames();
        addSummaries(summariesMap);

        List<String> summaries = uniqueInstance.getAllSummries();

        assertEquals(2, summaries.size());
        assertTrue(summaries.contains(SUMMARY_A));
        assertTrue(summaries.contains(SUMMARY_B));

        // removing an element from the original list
        summariesMap.remove(ID1);

        assertEquals(2, summaries.size());
        assertTrue(summaries.contains(SUMMARY_A));
        assertTrue(summaries.contains(SUMMARY_B));
    }

    private void addSummaries(Map<String, String> summariesMap) {
        summariesMap.put(ID1, SUMMARY_A);
        summariesMap.put(ID2, SUMMARY_B);
    }

}
