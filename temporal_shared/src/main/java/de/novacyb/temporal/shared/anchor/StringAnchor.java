package de.novacyb.temporal.shared.anchor;

/**
 * String Token<br>
 * String tokens can be used if the developer want to define the anchor without a proper anchor source object
 * Created on 19.12.2018.
 * @deprecated because the reports now directly accept strings
 */
@Deprecated(forRemoval = true , since = "0.1.1.0")
public class StringAnchor implements IIdentifierAnchor {
    private final String strToken;

    /**
     * String Anchor constructor
     * @param strToken the string token
     */
    public StringAnchor(final String strToken) {
        this.strToken = strToken;
    }

    @Override
    public String getTokenIdentifier() {
        return strToken;
    }
}
