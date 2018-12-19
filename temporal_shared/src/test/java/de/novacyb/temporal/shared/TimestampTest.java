package de.novacyb.temporal.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimestampTest {

    // offset is required to avoid flaky test results
    private final static long ALLOWED_OFFSET_MS = 100;

    @Test
    void defaultConstructor() {
        final var ts = new Timestamp();
        assertNotEquals(0, ts, "A default value (now) should be set!");

        assertTrue(Math.abs(ts.get() - System.currentTimeMillis()) <= ALLOWED_OFFSET_MS);
    }


    @Test
    void setterConstructor() {
        assertEquals(0, new Timestamp(0).get());
        assertEquals(1, new Timestamp(1).get());
        assertEquals(23423432, new Timestamp(23423432).get());
        assertEquals(Long.MAX_VALUE, new Timestamp(Long.MAX_VALUE).get());

        assertEquals(-1, new Timestamp(-1).get());
        assertEquals(-23423432, new Timestamp(-23423432).get());
        assertEquals(Long.MIN_VALUE, new Timestamp(Long.MIN_VALUE).get());
    }

    @Test
    void setAndGet() {
        final var ts = new Timestamp();
        ts.set(0);
        assertEquals(0, ts.get());
        ts.set(-1);
        assertEquals(-1, ts.get());

        ts.set(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, ts.get());

        ts.set(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, ts.get());
    }

    @Test
    void makeNow() {
        final var ts = new Timestamp(0);
        assertEquals(0, ts.get());
        ts.makeNow();
        assertTrue(Math.abs(ts.get() - System.currentTimeMillis()) <= ALLOWED_OFFSET_MS);
        assertNotEquals(0, ts);
    }


    // TODO test has passed

    // TODO test difference
}