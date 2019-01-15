package de.novacyb.temporal.insights;

import de.novacyb.temporal.shared.ReportType;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static de.novacyb.temporal.shared.Configuration.TOKEN_INDICATOR;
import static de.novacyb.temporal.shared.Configuration.TOKEN_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;


/**
 * <b>Development Note:</b><br>
 * - since this is a debug tool we should never throw exceptions since it would harm the host application
 */
class TemporalTest {

    @Test
    void reportCleanTest() {
        final var temporal = new Temporal();
        final var result = new AtomicReference<String>();
        temporal.setOutputLink(result::set);

        temporal.addReport(() -> "TEST","main",3434L, ReportType.NOTIFY, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA",result.get() );
    }

    @Test
    void reportCleanTestWithTags() {
        final var temporal = new Temporal();
        final var result = new AtomicReference<String>();
        temporal.setOutputLink(result::set);

        // test with one tag
        temporal.addReport(() -> "TEST","main",3434L, ReportType.NOTIFY, "TestA",
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
        temporal.addReport(() -> "TEST", "main",3434L, ReportType.NOTIFY, "TestA",
                "TagA", "Tag B", "Tag C 45 &%&/$§");

        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA" + TOKEN_SEPARATOR + "TagA"
                + TOKEN_SEPARATOR + "Tag B" + TOKEN_SEPARATOR +  "Tag C 45 &%&/$§", result.get() );
    }


    @Test
    void reportNull() {
        final var temporal = new Temporal();
        final var result = new AtomicReference<String>();
        temporal.setOutputLink(result::set);


        temporal.addReport(() -> null,"main", 3434, ReportType.NOTIFY, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA",result.get() );

        temporal.addReport(null,"main",3434, ReportType.NOTIFY, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA",result.get() );

        temporal.addReport(() -> "TEST","main",3434, null, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR +  TOKEN_SEPARATOR + "TestA",result.get() );

        temporal.addReport(() -> "TEST","main",3434, ReportType.NOTIFY, null);
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR,result.get() );
    }


    @Test
    void activeToggle() {
        final var temporal = new Temporal();
        final var result = new AtomicReference<String>();
        temporal.setOutputLink(result::set);

        // should be active by default
        temporal.addReport(() -> null,"main", 3434, ReportType.NOTIFY, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "3434" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestA",result.get() );

        temporal.setActive(false);

        // clear result
        result.set(null);

        temporal.addReport(() -> null,"main", 33222, ReportType.NOTIFY, "TestB");
        assertNull(result.get());

        temporal.setActive(true);

        temporal.addReport(() -> null,"main", 11111, ReportType.NOTIFY, "TestC");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + TOKEN_SEPARATOR + "main" + TOKEN_SEPARATOR
                + "11111" + TOKEN_SEPARATOR + "notify" +  TOKEN_SEPARATOR + "TestC",result.get() );

        temporal.setActive(false);

        // clear result
        result.set(null);

        temporal.addReport(() -> null,"main", 77, ReportType.NOTIFY, "TestD");
        assertNull(result.get());
    }
}