package de.novacyb.temporal.shared;

/**
 * <b>Timestamp</b><br>
 * Stores a timestamp value based on milliseconds since midnight, January 1, 1970 UTC and provides related operations.
 * @author Jan Schwien
 * Created on 19.12.18.
 */
public class Timestamp {
    private long stamp;


    /**
     * default constructor<br>
     * Time will be set to current system time
     * @see #makeNow()
     */
    public Timestamp() {
        makeNow();
    }

    /**
     * constructor with start value in milliseconds based on midnight, January 1, 1970 UTC.
     * @param value timestamp value
     */
    public Timestamp(final long value) {
        stamp = value;
    }


    /**
     * sets the timestamp to the current system timer<br>
     * Based on midnight, January 1, 1970 UTC.
     */
    public void makeNow() {
        stamp = System.currentTimeMillis();
    }

    /**
     * returns the timestamp value in milliseconds based on midnight, January 1, 1970 UTC.
     * @return  the timestamp value
     */
    public long get() {
        return stamp;
    }

    /**
     * set a timestamp value in milliseconds based on midnight, January 1, 1970 UTC.
     * @param value timestamp value
     */
    public void set(final long value) {
        stamp = value;
    }

    /**
     * set timestamp by an other timestamp
     * @param timestamp the timestamp
     */
    public void set(final Timestamp timestamp) {
        stamp = timestamp.get();
    }
}
