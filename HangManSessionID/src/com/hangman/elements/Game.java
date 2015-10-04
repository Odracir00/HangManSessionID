package com.hangman.elements;

public class Game {
	/**
	 * Game id. Used to identify the game in the Map of games currently being
	 * played.
	 */
	private Integer id = 0;
	/**
	 * Game key. Used to identify the correct answer in the Map of answers.
	 */
	private Integer key;
	/**
	 * Current state of the game.
	 */
	private State state;
	/**
	 * Correct answer. It is obtained Map of answers from the key.
	 */
	private Answer answer;

	/**
	 * Current hint.
	 */
	private String hint;
	/**
	 * Letters that the user already have tried.
	 */
	private String triedLetters;

	public Game() {
	}

	public Game(Integer key, Answer answer) { // it is a new game
		this.key = key;
		this.answer = answer;

		state = State.START;
		createHint(answer.getName());
		triedLetters = "";
	}

	public Game(Integer key, Answer answer, State state, String hint, String triedLetters) {
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

	public void processNewLetter(char c) { // isto devia estar numa class
											// dedicada ao gameProcess

		triedLetters += String.valueOf(c);  // Tenho de transformar isto num pojo

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

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	public void setKey(Integer key) {
		this.key = key;
	}
	
	public Integer getKey() {
		return key;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return state;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	public Answer getAnswer() {
		return answer;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public String getHint() {
		return hint;
	}

	public void setTriedLetters(String triedLetters) {
		this.triedLetters = triedLetters;
	}

	public String getTriedLetters() {
		return triedLetters;
	}
}
