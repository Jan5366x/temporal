package de.novacyb.temporal.insights;

import de.novacyb.temporal.shared.ReportType;
import de.novacyb.temporal.shared.legacy.LegacyConsumer;
import de.novacyb.temporal.shared.anchor.IIdentifierAnchor;

import java.util.Objects;

import static de.novacyb.temporal.shared.Configuration.TOKEN_INDICATOR;
import static de.novacyb.temporal.shared.Configuration.TOKEN_SEPARATOR;

/**
 * Temporal Insights
 * Created on 19.12.2018.
 */
public class Temporal {
    private final static String DEFAULT_VALUE = "";

    /**
     * the primary report instance
     */
    private final static Temporal INSTANCE = new Temporal();

    /**
     * output consumer to link a logger or a console output
     */
    private static LegacyConsumer<String> outputLink = new LegacyConsumer<String>() {
        @Override
        public void accept(final String value) {
            System.out.println(value);
        }
    };

    /**
     * report a entry
     * @param identifierString  report anchor string to identify a unique object
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final String identifierString, final ReportType type, final String entryName,
                              final String... tags) {

        report(new IIdentifierAnchor() {
            @Override
            public String getTokenIdentifier() {
                return identifierString;
            }
        }, type, entryName, tags);
    }
    /**
     * report a entry
     * @param identifierString  report anchor string to identify a unique object
     * @param subBranch         the sub branch can be used to track different sub feature branches for a given object
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final String identifierString, final String subBranch, final ReportType type,
                              final String entryName, final String... tags) {

        report(new IIdentifierAnchor() {
            @Override
            public String getTokenIdentifier() {
                return identifierString;
            }
        }, subBranch, type, entryName, tags);
    }

    /**
     * report a entry
     * @param identifierAnchor  report anchor to identify a unique object
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final IIdentifierAnchor identifierAnchor, final ReportType type, final String entryName,
                              final String... tags) {

        INSTANCE.addReport(identifierAnchor, null, type, entryName, tags);
    }

    /**
     * report a entry
     * @param identifierAnchor  report anchor to identify a unique object
     * @param subBranch         the sub branch can be used to track different sub feature branches for a given object
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final IIdentifierAnchor identifierAnchor, final String subBranch, final ReportType type,
                              final String entryName, final String... tags) {

        INSTANCE.addReport(identifierAnchor, subBranch, type, entryName, tags);
    }

    /** report a entry
     * @param identifierAnchorString  report anchor string to identify a unique object
     * @param timestamp               the timestamp (The difference, measured in milliseconds, between the current time
     *                                and midnight, January 1, 1970 UTC.)
     * @param type                    the type of the report
     * @param entryName               the report entry name
     * @param tags                    search and filter tags
     */
    public static void report(final String identifierAnchorString, final long timestamp,
                              final ReportType type, final String entryName, final String... tags) {

        report(new IIdentifierAnchor() {
            @Override
            public String getTokenIdentifier() {
                return identifierAnchorString;
            }
        }, timestamp, type, entryName, tags);
    }

    /** report a entry
     * @param identifierAnchorString  report anchor string to identify a unique object
     * @param subBranch               the sub branch can be used to track different sub feature branches for a given object
     * @param timestamp               the timestamp (The difference, measured in milliseconds, between the current time
     *                                and midnight, January 1, 1970 UTC.)
     * @param type                    the type of the report
     * @param entryName               the report entry name
     * @param tags                    search and filter tags
     */
    public static void report(final String identifierAnchorString,final String subBranch, final long timestamp,
                              final ReportType type, final String entryName, final String... tags) {

        report(new IIdentifierAnchor() {
            @Override
            public String getTokenIdentifier() {
                return identifierAnchorString;
            }
        }, subBranch, timestamp, type, entryName, tags);
    }

    /** report a entry
     * @param identifierAnchor  report anchor to identify a unique object
     * @param timestamp         the timestamp (The difference, measured in milliseconds, between the current time
     *                          and midnight, January 1, 1970 UTC.)
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final IIdentifierAnchor identifierAnchor, final long timestamp,
                              final ReportType type, final String entryName, final String... tags) {

        INSTANCE.addReport(identifierAnchor, null, timestamp, type, entryName, tags);
    }

    /** report a entry
     * @param identifierAnchor  report anchor to identify a unique object
     * @param subBranch         the sub branch can be used to track different sub feature branches for a given object
     * @param timestamp         the timestamp (The difference, measured in milliseconds, between the current time
     *                          and midnight, January 1, 1970 UTC.)
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public static void report(final IIdentifierAnchor identifierAnchor, final String subBranch, final long timestamp,
                              final ReportType type, final String entryName, final String... tags) {

        INSTANCE.addReport(identifierAnchor, subBranch, timestamp, type, entryName, tags);
    }

    /**
     * report a entry
     * @param identifierAnchor  report anchor to identify a unique object
     * @param subBranch         the sub branch can be used to track different sub feature branches for a given object
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public void addReport(final IIdentifierAnchor identifierAnchor, final String subBranch, final ReportType type,
                          final String entryName, final String... tags) {

        addReport(identifierAnchor,subBranch,  System.currentTimeMillis() , type, entryName, tags);
    }

    /**
     * report a entry
     * @param identifierAnchor   report anchor to identify a unique object
     * @param subBranch         the sub branch can be used to track different sub feature branches for a given object
     * @param timestamp         the timestamp (The difference, measured in milliseconds, between the current time
     *                          and midnight, January 1, 1970 UTC.)
     * @param type              the type of the report
     * @param entryName         the report entry name
     * @param tags              search and filter tags
     */
    public void addReport(final IIdentifierAnchor identifierAnchor, final String subBranch, final long timestamp,
                          final ReportType type, final String entryName, final String... tags) {

        final StringBuilder sBuilder = new StringBuilder();

        // add anchor report indicator for easier parsing
        sBuilder.append(TOKEN_INDICATOR);
        sBuilder.append(TOKEN_SEPARATOR);

        // add anchor identifier
        sBuilder.append(identifierAnchor != null && identifierAnchor.getTokenIdentifier() != null
                ? identifierAnchor.getTokenIdentifier() : DEFAULT_VALUE);
        sBuilder.append(TOKEN_SEPARATOR);

        // add sub feature branch indicator
        sBuilder.append(subBranch != null ? subBranch : "main");
        sBuilder.append(TOKEN_SEPARATOR);

        // add the timestamp
        sBuilder.append(timestamp);
        sBuilder.append(TOKEN_SEPARATOR);

        // add the lowercase type name
        sBuilder.append(type != null ? type.toString().toLowerCase() : DEFAULT_VALUE);
        sBuilder.append(TOKEN_SEPARATOR);

        // add the entry name
        sBuilder.append(entryName != null ? entryName : DEFAULT_VALUE);

        // add tags and related separators
        if (tags.length > 0)
            sBuilder.append(TOKEN_SEPARATOR);

        for (int i = 0; i < tags.length; i++) {
            sBuilder.append(tags[i] != null ? tags[i] : DEFAULT_VALUE);
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
