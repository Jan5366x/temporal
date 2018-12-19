package de.novacyb.temporal.shared.token;

/**
 * String Token<br>
 * String tokens can be used if the developer want to define the token without a proper token source object
 * @author Jan Schwien
 * Created on 19.12.2018.
 */
public class StringToken extends AbstractTemporalToken {
    private final String strToken;

    public StringToken(final String strToken, final String... tags) {
        super( tags);
        this.strToken = strToken;
    }

    @Override
    public String getTokenIdentifier() {
        return strToken;
    }
}
