package de.novacyb.temporal.shared.io;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartPropertiesTest {

    private SmartProperties properties = null;

    @BeforeAll
    void setup() {
        properties = new SmartProperties("/shared/io/SmartPropertiesTestA.properties");
    }

    @Test
    void getString() {
        assertEquals("Hallo",properties.getString("testA"));
        assertEquals("hallo",properties.getString("testB"));
        assertEquals("HALLO WELT !",properties.getString("testC"));
        assertEquals("", properties.getString("testD"));
    }

    @Test
    void getInteger() {
        // TODO write basic test
        throw new AssertionError("Missing Test!");
    }

    @Test
    void getLong() {
        // TODO write basic test
        throw new AssertionError("Missing Test!");
    }

    @Test
    void getFloat() {
        // TODO write basic test
        throw new AssertionError("Missing Test!");
    }

    @Test
    void getDouble() {
        // TODO write basic test
        throw new AssertionError("Missing Test!");
    }
}