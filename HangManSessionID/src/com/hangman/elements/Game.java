package com.hangman.elements;

public class Game {

    /**
     * Game id. Used to identify the game in the Map of games currently being
     * played.
     */
    final private String id;
    /**
     * Game key. Used to identify the correct answer in the Map of answers.
     */
    final private Integer key;
    /**
     * Correct answer. It is obtained Map of answers from the key.
     */
    final private Answer answer;
    /**
     * Current state of the game.
     */
    private State state;
    /**
     * Current hint.
     */
    private String hint;
    /**
     * Letters that the user already have tried.
     */
    private String triedLetters;

    public Game(String id, Integer key, Answer answer) { // it is a new game 
        this.id = id;
        this.key = key;
        this.answer = answer;

        state = State.START;
        createHint(answer.getName());
        triedLetters = "";
    }

    public Game(String id, Integer key, Answer answer, State state, String hint, String triedLetters) { // it is a new game (!)
        this.id = id;
        this.key = key;
        this.answer = answer;
        this.state = state;
        this.hint = hint;
        this.triedLetters = triedLetters;
    }

    // default visibility to facuilitate testing, 
    // otherwhise this method should be private
    final void createHint(String ans) {
        String emptyhint = " ";
        for (int i = 0; i < ans.length(); i++) {
            emptyhint += "_ ";
        }
        this.hint = emptyhint;
    }

    public void processNewLetter(char c) {
    	
    	triedLetters += String.valueOf(c);
    	
        boolean hit = false;
        boolean success = false;
        char[] answerArray = answer.getName().toCharArray();
        char[] hintArray = hint.replaceAll(" ", "").toCharArray();

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
            createHintFromArray(hintArray);
            if (success) {
                state = State.SUCCESS;
            }
        } else {
            updateState();
            if (state == State.RIGHT_LEG) { // Game over
                createHintFromArray(answerArray);
            } else {
                createHintFromArray(hintArray);
            }
        }

    }

   private void createHintFromArray(char[] array) {
        hint = " ";
        for (int i = 0; i < array.length; i++) {
            hint += array[i] + " ";
        }
    }

    // default to facilitate testing, otherwise this method should be private
    void updateState() {
        if (state == State.START) {
            state = State.FLOOR;
        } else if (state == State.FLOOR) {
            state = State.VERTICAL_BAR;
        } else if (state == State.VERTICAL_BAR) {
            state = State.HORIZONTAL_BAR;
        } else if (state == State.HORIZONTAL_BAR) {
            state = State.ROPE;
        } else if (state == State.ROPE) {
            state = State.HEAD;
        } else if (state == State.HEAD) {
            state = State.BODY_TRUNK;
        } else if (state == State.BODY_TRUNK) {
            state = State.LEFT_ARM;
        } else if (state == State.LEFT_ARM) {
            state = State.RIGHT_ARM;
        } else if (state == State.RIGHT_ARM) {
            state = State.LEFT_LEG;
        } else if (state == State.LEFT_LEG) {
            state = State.RIGHT_LEG;
        }
    }

    public Integer getKey() {
        return key;
    }

    public State getState() {
        return state;
    }

    public Answer getAnswer() {
        return answer;
    }

    public String getHint() {
        return hint;
    }

    public String getId() {
        return id;
    }

    public String getTriedLetters() {
        return triedLetters;
    }
}
