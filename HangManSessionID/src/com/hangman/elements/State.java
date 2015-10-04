package com.hangman.elements;

/** This enum class defines all the possible state in which the game can be. */
public enum State {
    START,
    FLOOR,
    VERTICAL_BAR,
    HORIZONTAL_BAR,
    ROPE,
    HEAD,
    BODY_TRUNK,
    LEFT_ARM,
    RIGHT_ARM,
    LEFT_LEG,
    RIGHT_LEG,
    SUCCESS;
}
