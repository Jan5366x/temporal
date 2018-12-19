package de.novacyb.temporal.shared.token;

/**
 * Hash Token<br>
 * Hash tokens are very simple to use but only work if the hash value is ensured to be the same over time
 * Created on 19.12.2018.
 */
public class HashToken extends AbstractTemporalToken {
    private final int hash;


    public HashToken(final Object object, final String... tags) {
        super(tags);
        this.hash = object.hashCode();
    }

    @Override
    public String getTokenIdentifier() {
        return String.valueOf(hash);
    }
}
