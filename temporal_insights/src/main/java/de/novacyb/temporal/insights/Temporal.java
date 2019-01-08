package de.novacyb.temporal.insights;

import de.novacyb.temporal.shared.EntryType;
import de.novacyb.temporal.shared.legacy.LegacyConsumer;
import de.novacyb.temporal.shared.token.ITemporalToken;

import java.util.Objects;

import static de.novacyb.temporal.shared.Configuration.TOKEN_INDICATOR;
import static de.novacyb.temporal.shared.Configuration.TOKEN_SEPARATOR;

/**
 * Temporal Insights
 * Created on 19.12.2018.
 */
public class Temporal {
    private final static String DEFAULT_VALUE = "";

    private final static Temporal INSTANCE = new Temporal();

    private static LegacyConsumer<String> outputLink = new LegacyConsumer<String>() {
        @Override
        public void accept(final String value) {
            System.out.println(value);
        }
    };

    /**
     * report a entry
     * @param identifierString  report token string to identify a unique object
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final String identifierString, final EntryType type, final String entryName,
                              final String... tags) {
        report(() -> identifierString, type, entryName, tags);
    }

    /**
     * report a entry
     * @param identifierToken   report token to identify a unique object
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final ITemporalToken identifierToken, final EntryType type, final String entryName,
                          final String... tags) {

        INSTANCE.addReport(identifierToken, type, entryName, tags);
    }

    /** report a entry
     * @param identifierString  report token string to identify a unique object
     * @param timestamp         the timestamp (The difference, measured in milliseconds, between the current time
     *                          and midnight, January 1, 1970 UTC.)
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final String identifierString,final long timestamp, final EntryType type,
                              final String entryName, final String... tags) {
        report(() -> identifierString, timestamp, type, entryName, tags);
    }

    /** report a entry
     * @param identifierToken   report token to identify a unique object
     * @param timestamp         the timestamp (The difference, measured in milliseconds, between the current time
            *                          and midnight, January 1, 1970 UTC.)
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final ITemporalToken identifierToken,final long timestamp, final EntryType type,
                          final String entryName, final String... tags) {

        INSTANCE.addReport(identifierToken, timestamp, type, entryName, tags);
    }

    /**
     * report a entry
     * @param identifierToken   report token to identify a unique object
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public void addReport(final ITemporalToken identifierToken, final EntryType type, final String entryName,
                              final String... tags) {

        addReport(identifierToken, System.currentTimeMillis() , type, entryName, tags);
    }

    /**
     * report a entry
     * @param identifierToken   report token to identify a unique object
     * @param timestamp         the timestamp (The difference, measured in milliseconds, between the current time
     *                          and midnight, January 1, 1970 UTC.)
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public void addReport(final ITemporalToken identifierToken,final long timestamp, final EntryType type,
                              final String entryName, final String... tags) {

        final StringBuilder sBuilder = new StringBuilder();

        // add token report indicator for easier parsing
        sBuilder.append(TOKEN_INDICATOR);
        sBuilder.append(TOKEN_SEPARATOR);

        // add token identifier
        sBuilder.append(identifierToken != null && identifierToken.getTokenIdentifier() != null
                ? identifierToken.getTokenIdentifier() : DEFAULT_VALUE);
        sBuilder.append(TOKEN_SEPARATOR);

        // add the timestamp
        sBuilder.append(timestamp);
        sBuilder.append(TOKEN_SEPARATOR);

        // add the lowercase type name
        sBuilder.append(type != null ? type.toString().toLowerCase() : DEFAULT_VALUE);
        sBuilder.append(TOKEN_SEPARATOR);

        // add the entry name
        sBuilder.append(Objects.requireNonNullElse(entryName,DEFAULT_VALUE));

        // add tags and related separators
        if (tags.length > 0)
            sBuilder.append(TOKEN_SEPARATOR);

        for (int i = 0; i < tags.length; i++) {
            sBuilder.append(Objects.requireNonNullElse(tags[i],DEFAULT_VALUE));
            if (i != tags.length - 1) {
                sBuilder.append(TOKEN_SEPARATOR);
            }
        }

        // send the result to output
        outputLink.accept(sBuilder.toString());
    }


    /**
     * can be used to use a custom logger as output<br>
     * <i>(default is system out)</i>
     * @param outputConsumer the output consumer
     */
    public void setOutputLink(final LegacyConsumer<String> outputConsumer ) {
        outputLink = outputConsumer;
    }



}
