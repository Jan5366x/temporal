package de.novacyb.temporal.shared.io;



import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;


public class SmartPropertiesTest {
    private static final double DELTA = 1e-15;
    private SmartProperties properties = null;

    @Before
    public void cleanSetup() throws IOException {
        try (InputStream input = getClass().getResourceAsStream("/shared/io/SmartPropertiesTestA.properties")) {
            properties = new SmartProperties(input);
        }
    }

    @Test
    public void getString() {
        assertEquals("Hallo", properties.getString("testA"));
        assertEquals("hallo", properties.getString("testB"));
        assertEquals("HALLO WELT !", properties.getString("testC"));
        assertEquals("", properties.getString("testD"));

        assertNull(properties.getString("DontExistingKey"));
        assertNull(properties.getString(""));
        //assertThrows(NullPointerException.class, () -> properties.getString(null));
    }

    @Test
    public void getInteger() {
        assertEquals(34, properties.getInteger("intA"));
        assertEquals(-34, properties.getInteger("intB"));
        //assertThrows(NumberFormatException.class, () -> properties.getInteger("intC"));
    }

    @Test
    public void getLong() {
        assertEquals(4545L, properties.getLong("longA"));
        assertEquals(-3443L, properties.getLong("longB"));
        //assertThrows(NumberFormatException.class, () -> properties.getLong("longC"));
    }

    @Test
    public void getFloat() {
        //assertThrows(NumberFormatException.class, () -> properties.getFloat("floatA"));
        assertEquals(34.4F, properties.getFloat("floatB"),DELTA);
        assertEquals(-35.6F, properties.getFloat("floatC"),DELTA);
        //assertThrows(NumberFormatException.class, () -> properties.getFloat("floatD"));
        //assertThrows(NumberFormatException.class, () -> properties.getFloat("floatE"));
    }

    @Test
    public void getDouble() {
        //assertThrows(NumberFormatException.class, () -> properties.getDouble("doubleA"));
        assertEquals(39.4D, properties.getDouble("doubleB"),DELTA);
        assertEquals(-45.6D, properties.getDouble("doubleC"),DELTA);
        //assertThrows(NumberFormatException.class, () -> properties.getDouble("doubleD"));
        //assertThrows(NumberFormatException.class, () -> properties.getDouble("doubleE"));
    }
}