package com.hangman.elements;

public class Game {
	/** Game id. Used to identify the game in the Map of games currently being
	 * played. */
	private Integer id = 0;
	/** Game key. Used to identify the correct answer in the Map of answers. */
	private Integer key;
	/** Current state of the game. */
	private State state;
	/** Correct answer. It is obtained Map of answers from the key. */
	private Answer answer;
	/** Current hint. */
	private String hint;
	/** Letters that the user already have tried. */
	private String triedLetters;

	public Game() {
	}

	public Game(Integer key, Answer answer, String hint) { // it is a new game
		this.key = key;
		this.answer = answer;
		this.hint = hint;
		state = State.START;
		triedLetters = "";
	}

	public Game(Integer key, Answer answer, State state, String hint, String triedLetters) {
		this.key = key;
		this.answer = answer;
		this.state = state;
		this.hint = hint;
		this.triedLetters = triedLetters;
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
