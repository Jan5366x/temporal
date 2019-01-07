package de.novacyb.temporal.insights;

import de.novacyb.temporal.shared.EntryType;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static de.novacyb.temporal.shared.Configuration.TOKEN_INDICATOR;
import static de.novacyb.temporal.shared.Configuration.TOKEN_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;

class TemporalTest {

    @Test
    void reportCleanTest() {
        final var temporal = new Temporal();
        final var result = new AtomicReference<String>();
        temporal.setOutputLink(result::set);

        temporal.addReport(() -> "TEST",3434, EntryType.NOTIFY, "TestA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "3434" + TOKEN_SEPARATOR
                + "notify" +  TOKEN_SEPARATOR + "TestA",result.get() );
    }

    @Test
    void reportCleanTestWithTags() {
        final var temporal = new Temporal();
        final var result = new AtomicReference<String>();
        temporal.setOutputLink(result::set);

        // test with one tag
        temporal.addReport(() -> "TEST",3434, EntryType.NOTIFY, "TestA", "TagA");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "3434" + TOKEN_SEPARATOR
                + "notify" +  TOKEN_SEPARATOR + "TestA" + TOKEN_SEPARATOR + "TagA",result.get() );

        // test with two tags
        temporal.addReport(() -> "TEST",3434, EntryType.NOTIFY, "TestA", "TagA", "Tag B");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "3434" + TOKEN_SEPARATOR
                + "notify" +  TOKEN_SEPARATOR + "TestA" + TOKEN_SEPARATOR + "TagA" + TOKEN_SEPARATOR + "Tag B",
                result.get() );

        // test with three tags
        temporal.addReport(() -> "TEST",3434, EntryType.NOTIFY, "TestA", "TagA", "Tag B",
                "Tag C 45 &%&/$§");
        assertEquals(TOKEN_INDICATOR + TOKEN_SEPARATOR + "TEST" + TOKEN_SEPARATOR + "3434" + TOKEN_SEPARATOR
                        + "notify" +  TOKEN_SEPARATOR + "TestA" + TOKEN_SEPARATOR + "TagA" + TOKEN_SEPARATOR + "Tag B"
                        + TOKEN_SEPARATOR +  "Tag C 45 &%&/$§", result.get() );
    }
}