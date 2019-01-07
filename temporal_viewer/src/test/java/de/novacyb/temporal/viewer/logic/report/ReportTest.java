package de.novacyb.temporal.viewer.logic.report;

import de.novacyb.temporal.shared.EntryType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void getIdentifier() {
        final var reportA = new Report("ident","dsfsdf", EntryType.NOTIFY);
       assertEquals("ident", reportA.getIdentifier());

        final var reportB = new Report("4543534","dsfsdf", EntryType.NOTIFY);
        assertEquals("4543534", reportB.getIdentifier());

        assertThrows(NullPointerException.class, () ->  new Report(null,"dsfsdf", EntryType.NOTIFY));
        assertThrows(IllegalArgumentException.class, () ->  new Report("","dsfsdf", EntryType.NOTIFY));
    }

    @Test
    void getName() {
        final var reportA = new Report("ident","testA", EntryType.NOTIFY);
        assertEquals("testA", reportA.getName());

        final var reportB = new Report("ident","Test B", EntryType.NOTIFY);
        assertEquals("Test B", reportB.getName());

        final var reportC = new Report("ident","Test C 12\"(/&)/%$=}§§)", EntryType.NOTIFY);
        assertEquals("Test C 12\"(/&)/%$=}§§)", reportB.getName());

        assertThrows(NullPointerException.class, () ->  new Report("ident",null, EntryType.NOTIFY));
        assertThrows(IllegalArgumentException.class, () ->  new Report("ident","", EntryType.NOTIFY));
    }

    @Disabled
    @Test
    void getType() {
        // TODO write type test
    }

    @Disabled
    @Test
    void getTags() {
        // TODO write tags test
    }
}