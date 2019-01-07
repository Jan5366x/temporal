package de.novacyb.temporal.insights;

import de.novacyb.temporal.shared.EntryType;
import de.novacyb.temporal.shared.token.ITemporalToken;

import java.util.function.Consumer;

/**
 * Temporal Insights
 *
 * @author Jan Schwien
 * Created on 19.12.2018.
 */
public class Temporal {
    public final static String TOKEN_INDICATOR = "$$temporal$$";
    public final static String TOKEN_SEPARATOR = "|";

    private static Consumer<String> outputLink = System.out::println;

    /**
     * can be used to use a custom logger as output<br>
     * <i>(default is system out)</i>
     * @param outputConsumer the output consumer
     */
    public static void setOutputLink(final Consumer<String> outputConsumer ) {
        outputLink = outputConsumer;
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

        report(identifierToken, System.currentTimeMillis() , type, entryName, tags);
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
    public static void report(final ITemporalToken identifierToken,final long timestamp, final EntryType type,
                              final String entryName, final String... tags) {

        final var sBuilder = new StringBuilder();

        // add token report indicator for easier parsing
        sBuilder.append(TOKEN_INDICATOR);
        sBuilder.append(TOKEN_SEPARATOR);

        // add token identifier
        sBuilder.append(identifierToken.getTokenIdentifier());
        sBuilder.append(TOKEN_SEPARATOR);

        // add the timestamp
        sBuilder.append(timestamp);
        sBuilder.append(TOKEN_SEPARATOR);

        // add the lowercase type name
        sBuilder.append(type.toString().toLowerCase());
        sBuilder.append(TOKEN_SEPARATOR);

        // add the entry name
        sBuilder.append(entryName);
        sBuilder.append(TOKEN_SEPARATOR);

        // add tags
        for (int i = 0; i < tags.length; i++) {
            sBuilder.append(tags[i]);
            if (i != tags.length - 1) {
                sBuilder.append(TOKEN_SEPARATOR);
            }
        }

        // send the result to output
        outputLink.accept(sBuilder.toString());
    }


}
