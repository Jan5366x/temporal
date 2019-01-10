package de.novacyb.temporal.viewer.logic.report;

import de.novacyb.temporal.shared.ReportType;

/**
 * Temporal Report
 * Created on 07.01.2019.
 */
public class Report {
    private final String identifier;
    private final String name;
    private final ReportType type;
    private final String[] tags;

    public Report(final String identifier, final String name, final ReportType type, final String... tags)
            throws NullPointerException, IllegalArgumentException {

        if (identifier == null || name == null || type == null)
            throw new NullPointerException("Input can't be null!");

        if (identifier.trim().isEmpty() || name.trim().isEmpty())
            throw new IllegalArgumentException("Input can't be empty!");

        this.identifier = identifier;
        this.name = name;
        this.type = type;
        this.tags = tags;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public ReportType getType() {
        return type;
    }

    public String[] getTags() {
        return tags;
    }
}
