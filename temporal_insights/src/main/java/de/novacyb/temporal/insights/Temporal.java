package de.novacyb.temporal.insights;

import de.novacyb.temporal.shared.EntryType;
import de.novacyb.temporal.shared.legacy.LegacyConsumer;
import de.novacyb.temporal.shared.token.ITemporalToken;

/**
 * Temporal Insights
 * Created on 19.12.2018.
 */
public class Temporal {
    public final static String TOKEN_INDICATOR = "$$temporal$$";
    public final static String TOKEN_SEPARATOR = "|";
    private static LegacyConsumer<String> outputLink = new LegacyConsumer<String>() {
        @Override
        public void accept(final String value) {
            System.out.println(value);
        }
    };


    /**
     * can be used to use a custom logger as output<br>
     * <i>(default is system out)</i>
     * @param outputConsumer the output consumer
     */
    public static void setOutputLink(final LegacyConsumer<String> outputConsumer ) {
        outputLink = outputConsumer;
    }

    public static void report(final ITemporalToken token, final EntryType type, final String entryName,
                              final String... tags) {

        report(token, System.currentTimeMillis() , type, entryName, tags);
    }

    public static void report(final ITemporalToken token,final long timestamp, final EntryType type,
                              final String entryName, final String... tags) {

        final StringBuilder sBuilder = new StringBuilder();

        // add token report indicator for easier parsing
        sBuilder.append(TOKEN_INDICATOR);
        sBuilder.append(TOKEN_SEPARATOR);

        // add token identifier
        sBuilder.append(token.getTokenIdentifier());
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
