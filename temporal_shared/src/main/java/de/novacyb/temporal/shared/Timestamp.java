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
     * @param timeValue timestamp value
     */
    public Timestamp(final long timeValue) {
        stamp = timeValue;
    }


    /**
     * sets the timestamp to the current system timer<br>
     * Based on midnight, January 1, 1970 UTC.
     */
    public void makeNow() {
        makeNow(0);
    }

    /**
     * TODO javadoc
     * @return
     */
    public static Timestamp now() {
        return now(0);
    }

    /**
     * TODO javadoc
     * @param delta
     * @return
     */
    public static Timestamp now(final long delta) {
        return new Timestamp(System.currentTimeMillis() + delta);
    }

    /**
     * sets the timestamp to the current system timer<br>
     * Based on midnight, January 1, 1970 UTC.
     * @param delta the time delta to apply to the "now" time
     */
    public void makeNow(final long delta) {
        stamp = System.currentTimeMillis() + delta;
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
     * @param timeValue timestamp value
     */
    public void set(final long timeValue) {
        stamp = timeValue;
    }

    /**
     * set timestamp by an other timestamp
     * @param timestamp the timestamp
     */
    public void set(final Timestamp timestamp) {
        stamp = timestamp.get();
    }

    /**
     * check if a given timestamp has passed or reached the current time
     * @return <i>true</i> if reached or passed
     */
    public boolean hasPassed(){
        return hasPassed(0);
    }

    /**
     * check if a given timestamp including a delta has passed or reached the current time
     * @param delta time delta
     * @return <i>true</i> if reached or passed
     */
    public boolean hasPassed(final long delta){
        return stamp + delta <= System.currentTimeMillis();
    }

    /**
     * returns the difference between the timestamp and now
     * @return a positive or negative difference value
     */
    public long getDifference() {
        return getDifference(System.currentTimeMillis());
    }

    /**
     * returns the difference between the timestamp and an other
     * @param timestamp the timestamp for the difference calculation
     * @return a positive or negative difference value
     */
    public long getDifference(final Timestamp timestamp) {
        return getDifference(timestamp.get());
    }

    /**
     * returns the difference between the timestamp and an other time value
     * @param timeValue the time value for the difference calculation
     * @return a positive or negative difference value
     */
    public long getDifference(final long timeValue) {
        return stamp - timeValue;
    }


}
