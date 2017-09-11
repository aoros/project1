package com.aoros.project1;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SentenceDetectorMECreatorTest {

    private final SentenceDetectorMECreator instance;

    public SentenceDetectorMECreatorTest() {
        this.instance = new SentenceDetectorMECreator();
    }

    @Test
    public void testGetDetector() {
        String testString = "I was suddenly logged off Gmail. I can't remember my Gmail password and just realized the recovery email is no longer alive. What can I do?";
        String[] actual = instance.getDetector().sentDetect(testString);

        assertEquals(3, actual.length);
        assertEquals("I was suddenly logged off Gmail.", actual[0]);
        assertEquals("I can't remember my Gmail password and just realized the recovery email is no longer alive.", actual[1]);
        assertEquals("What can I do?", actual[2]);
    }
}
