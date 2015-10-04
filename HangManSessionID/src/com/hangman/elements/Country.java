package com.hangman.elements;

public class Country implements Answer {

    String answer;

    public Country(String answer) {
        this.answer = answer;
    }

    public Country() {
    }
    
    @Override
    public String getName() {
        return answer;
    }
    
    public void setName(String answer) {
        this.answer = answer;
    }

 
    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
