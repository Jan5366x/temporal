package de.novacyb.temporal.shared.io;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SmartPropertiesTest {

    private SmartProperties properties = null;

    @BeforeEach
    void cleanSetup() throws IOException {
        try (var input = getClass().getResourceAsStream("/shared/io/SmartPropertiesTestA.properties")) {
            properties = new SmartProperties(input);
        }
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