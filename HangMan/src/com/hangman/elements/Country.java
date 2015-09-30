package com.hangman.elements;

public class Country implements Answer {

    String answer;

    public Country(String answer) {
        this.answer = answer;
    }

    @Override
    public String getName() {
        return answer;
    }

}
