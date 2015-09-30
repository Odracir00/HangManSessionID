package com.hangman.elements;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

public class StateTest {

    @After
    public void tearDown() {
    }

    @Test
    public void testGetStatefromString() {
        assertEquals(State.START, State.getStatefromString("START"));
        assertEquals(State.FLOOR, State.getStatefromString("FLOOR"));
        assertEquals(State.VERTICAL_BAR, State.getStatefromString("VERTICAL_BAR"));
        assertEquals(State.HORIZONTAL_BAR, State.getStatefromString("HORIZONTAL_BAR"));
        assertEquals(State.ROPE, State.getStatefromString("ROPE"));
        assertEquals(State.HEAD, State.getStatefromString("HEAD"));
        assertEquals(State.BODY_TRUNK, State.getStatefromString("BODY_TRUNK"));
        assertEquals(State.LEFT_ARM, State.getStatefromString("LEFT_ARM"));
        assertEquals(State.RIGHT_ARM, State.getStatefromString("RIGHT_ARM"));
        assertEquals(State.LEFT_LEG, State.getStatefromString("LEFT_LEG"));
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidGetStatefromString() {
        assertEquals(State.LEFT_LEG, State.getStatefromString("start"));
    }

}
