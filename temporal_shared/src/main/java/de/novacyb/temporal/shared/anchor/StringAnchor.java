package de.novacyb.temporal.shared.anchor;

/**
 * String Token<br>
 * String tokens can be used if the developer want to define the anchor without a proper anchor source object
 * Created on 19.12.2018.
 * @deprecated because the reports now directly accept strings
 */
@Deprecated
public class StringAnchor implements IIdentifierAnchor {
    private final String strToken;

    /**
     * String Token
     * @param strToken
     */
    public StringAnchor(final String strToken) {
        this.strToken = strToken;
    }

    @Override
    public String getTokenIdentifier() {
        return strToken;
    }
}
