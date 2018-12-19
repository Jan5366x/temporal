package de.novacyb.temporal.insights;

import de.novacyb.temporal.shared.token.ITemporalToken;
import de.novacyb.temporal.shared.token.StringToken;

/**
 * Temporal Insights
 *
 * @author jschwien
 * Created on 19.12.2018.
 */
public class Temporal {

    public static void report(final ITemporalToken token, final String message) {
        report(new StringToken("hallo"),"miep");
    }
}
