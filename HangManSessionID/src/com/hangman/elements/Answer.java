package com.hangman.elements;

public class Answer {

	int id;
	AnswerType type;

	String value;

	public Answer() {
	}
	
	public Answer(AnswerType type, String value) {
		this.type = type;
		this.value = value;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public AnswerType getType() {
		return type;
	}

	public void setType(AnswerType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
