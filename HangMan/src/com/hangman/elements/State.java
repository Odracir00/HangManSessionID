package com.hangman.elements;

/**
 * This enum class defines all the possible state in which the game can be.
 */
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

    public static State getStatefromString(String s) {
        if (START.name().equals(s)) {
            return START;
        } else if (FLOOR.name().equals(s)) {
            return FLOOR;
        } else if (VERTICAL_BAR.name().equals(s)) {
            return VERTICAL_BAR;
        } else if (HORIZONTAL_BAR.name().equals(s)) {
            return HORIZONTAL_BAR;
        } else if (ROPE.name().equals(s)) {
            return ROPE;
        } else if (HEAD.name().equals(s)) {
            return HEAD;
        } else if (BODY_TRUNK.name().equals(s)) {
            return BODY_TRUNK;
        } else if (LEFT_ARM.name().equals(s)) {
            return LEFT_ARM;
        } else if (RIGHT_ARM.name().equals(s)) {
            return RIGHT_ARM;
        } else if (LEFT_LEG.name().equals(s)) {
            return LEFT_LEG;
        }
        throw new IllegalStateException("No Enum specified for this string");
    }
}
