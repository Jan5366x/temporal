package de.novacyb.temporal.shared.token;

/**
 * Object Token Interface
 * Created on 19.12.2018.
 */
public interface ITemporalToken {
    /**
     * the token identifier
     * @return TODO javadoc
     */
    String getTokenIdentifier();

    /**
     * filter tags for sorting and search purpose (can be empty)
     * @return TODO javadoc
     */
    String[] getTags();
}
