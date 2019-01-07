package de.novacyb.temporal.shared.time;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimestampTest {

    // offset is required to avoid flaky test results
    private final static long ALLOWED_OFFSET_MS = 30;

    @Test
    public void defaultConstructor() {
        final Timestamp ts = new Timestamp();
        assertNotEquals("A default value (now) should be set!", 0, ts);

        assertTrue(Math.abs(ts.get() - System.currentTimeMillis()) <= ALLOWED_OFFSET_MS);
    }

    @Test
    public void setterConstructor() {
        assertEquals(0, new Timestamp(0).get());
        assertEquals(1, new Timestamp(1).get());
        assertEquals(23423432, new Timestamp(23423432).get());
        assertEquals(Long.MAX_VALUE, new Timestamp(Long.MAX_VALUE).get());

        assertEquals(-1, new Timestamp(-1).get());
        assertEquals(-23423432, new Timestamp(-23423432).get());
        assertEquals(Long.MIN_VALUE, new Timestamp(Long.MIN_VALUE).get());
    }

    @Test
    public void setAndGet() {
        final Timestamp ts = new Timestamp();
        ts.set(0);
        assertEquals(0, ts.get());
        ts.set(-1);
        assertEquals(-1, ts.get());

        ts.set(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, ts.get());

        ts.set(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, ts.get());

        ts.set(new Timestamp(45));
        assertEquals(45,ts.get());
    }

    @Test
    public void makeNow() {
        final Timestamp ts = new Timestamp(0);
        assertEquals(0, ts.get());
        ts.makeNow();
        assertTrue(Math.abs(ts.get() - System.currentTimeMillis()) <= ALLOWED_OFFSET_MS);
        assertNotEquals(0, ts);
    }

    @Test
    public void hasPassed() throws Exception {
        final Timestamp ts = new Timestamp();
        Thread.sleep(ALLOWED_OFFSET_MS);
        assertTrue(ts.hasPassed());

        ts.makeNow(100);
        Thread.sleep(100 + ALLOWED_OFFSET_MS );
        assertTrue(ts.hasPassed());
    }

    @Test
    public void difference() {
        final Timestamp ts = new Timestamp(0);
        assertEquals(-1,ts.getDifference(1));
        assertEquals(1,ts.getDifference(-1));

        ts.set(150);
        assertEquals(50,ts.getDifference(100));

        ts.set(100000);
        assertEquals(500, ts.getDifference(new Timestamp(99500)));

        final Timestamp tsNow = Timestamp.now(1000);
        assertTrue(Math.abs(tsNow.getDifference() - 1000) <= ALLOWED_OFFSET_MS);
    }
}