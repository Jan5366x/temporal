package de.novacyb.temporal.shared.token;

/**
 * Abstract Temporal Token
 *
 * @author Jan Schwien
 * Created on 19.12.2018.
 */
public abstract class AbstractTemporalToken implements ITemporalToken {
    private final String[] tags;

    public AbstractTemporalToken(final String... tags) {
        this.tags = tags;
    }

    @Override
    public String[] getTags() {
        return tags;
    }
}
