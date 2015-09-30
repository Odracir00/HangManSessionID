package com.hangman.elements;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Singleton class responsible for storing the information about the games that
 * are currently being played.
 */
public class GamesSummary {

    private static GamesSummary summary;
    // <game Id, game summary>
    // An alternative would be to create a map
    // Map<String, Game>, storing the full Game state. 
    // Once that the only requirement is to display the current state of the games, 
    // it was chosen to store only a String with that information; in this way, 
    // we improve performance once the following synchronised methods 
    // operate in a simpler object (faster to process).
    private final Map<String, String> currentGames = new HashMap<>();

    private GamesSummary() {
    }

    public static synchronized GamesSummary getInstance() {
        if (summary == null) {
            summary = new GamesSummary();
        }
        return summary;
    }

    public synchronized void deleteGameById(String id) {
        currentGames.remove(id);        // Game over or success
    }

    public synchronized void deleteAllGames() {
        currentGames.clear();        // Game over or success
    }
    
    public synchronized void addorUpdateGameById(String id, String gameSummary) {
        currentGames.put(id, gameSummary); // new game or new interactio
    }

    public synchronized List<String> getAllSummries() {
        Collection<String> summaries = getCurrentGames().values();
        List<String> summariesCopy = new LinkedList<>();

        // Creates a deep copy of the currentGames collection
        // After this method ends, if the original collection will be change 
        // by another thread, the returned list will not be affected.
        for (String s : summaries) {
            summariesCopy.add(s);
        }
        return summariesCopy;
    }

    // This method is here only to to facilitate the testing
    Map<String, String> getCurrentGames() {
        return currentGames;
    }
}
