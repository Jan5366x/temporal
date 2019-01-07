package de.novacyb.temporal.shared.token;

/**
 * String Token<br>
 * String tokens can be used if the developer want to define the token without a proper token source object
 * Created on 19.12.2018.
 */
public class StringToken implements ITemporalToken {
    private final String strToken;

    /**
     * String Token
     * @param strToken
     */
    public StringToken(final String strToken) {
        this.strToken = strToken;
    }

    @Override
    public String getTokenIdentifier() {
        return strToken;
    }
}
