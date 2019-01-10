package de.novacyb.temporal.insights;

import de.novacyb.temporal.shared.ReportType;
import de.novacyb.temporal.shared.legacy.LegacyConsumer;
import de.novacyb.temporal.shared.token.ITemporalToken;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static de.novacyb.temporal.shared.Configuration.TOKEN_INDICATOR;
import static de.novacyb.temporal.shared.Configuration.TOKEN_SEPARATOR;
import static org.junit.Assert.*;


/**
 * <b>Development Note:</b><br>
 * - since this is a debug tool we should never throw exceptions since it would harm the host application
 */
public class TemporalTest {

    @Test
    public void reportCleanTest() {
        final Temporal temporal = new Temporal();
        final AtomicReference<String> result = new AtomicReference<>();
        temporal.setOutputLink(new LegacyConsumer<String>() {
            @Override
            public void accept(String value) {
                result.set(value);
            }
        });

        temporal.addReport(new ITemporalToken() {
            @Override
            public String getTokenIdentifier() {
                return "TEST";
            }
        },"main",3434L, ReportType.NOTIFY, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA",result.get() );
    }

    @Test
    public void reportCleanTestWithTags() {
        final Temporal temporal = new Temporal();
        final AtomicReference<String> result = new AtomicReference<>();
        temporal.setOutputLink(new LegacyConsumer<String>() {
            @Override
            public void accept(String value) {
                result.set(value);
            }
        });

        // test with one tag
        temporal.addReport(new ITemporalToken() {
            @Override
            public String getTokenIdentifier() {
                return "TEST";
            }
        },"main",3434L, ReportType.NOTIFY, "TestA",
                "TagA");

        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA" + TOKEN_SEPARATOR + "TagA",
                result.get() );

        // test with two tags
        temporal.addReport(() -> "TEST","main",3434L, ReportType.NOTIFY, "TestA",
                "TagA", "Tag B");

        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                        + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA" + TOKEN_SEPARATOR
                        + "TagA" + TOKEN_SEPARATOR + "Tag B", result.get() );

        // test with three tags
        temporal.addReport(new ITemporalToken() {
                               @Override
                               public String getTokenIdentifier() {
                                   return "TEST";
                               }
                           }, "main",3434L, ReportType.NOTIFY, "TestA",
                "TagA", "Tag B", "Tag C 45 &%&/$§");

        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA" + TOKEN_SEPARATOR + "TagA"
                + TOKEN_SEPARATOR + "Tag B" + TOKEN_SEPARATOR +  "Tag C 45 &%&/$§", result.get() );
    }


    @Test
    public void reportNull() {
        final Temporal temporal = new Temporal();
        final AtomicReference<String> result = new AtomicReference<>();
        temporal.setOutputLink(new LegacyConsumer<String>() {
            @Override
            public void accept(String value) {
                result.set(value);
            }
        });


        temporal.addReport(new ITemporalToken() {
            @Override
            public String getTokenIdentifier() {
                return null;
            }
        },"main", 3434, ReportType.NOTIFY, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA",result.get() );

        temporal.addReport(null,"main",3434, ReportType.NOTIFY, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA",result.get() );

        temporal.addReport(new ITemporalToken() {
            @Override
            public String getTokenIdentifier() {
                return "TEST";
            }
        },"main",3434, null, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR +  TOKEN_SEPARATOR + "TestA",result.get() );

        temporal.addReport(new ITemporalToken() {
            @Override
            public String getTokenIdentifier() {
                return "TEST";
            }
        },"main",3434, ReportType.NOTIFY, null);
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR,result.get() );
    }
}