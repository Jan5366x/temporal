package de.novacyb.temporal.viewer.logic.report;

import org.junit.jupiter.api.Test;

import static de.novacyb.temporal.shared.ReportType.FAIL;
import static de.novacyb.temporal.shared.ReportType.NOTIFY;
import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void getIdentifier() {
        final var reportA = new Report("ident","dsfsdf", NOTIFY);
       assertEquals("ident", reportA.getIdentifier());

        final var reportB = new Report("4543534","dsfsdf", NOTIFY);
        assertEquals("4543534", reportB.getIdentifier());

        assertThrows(NullPointerException.class, () ->  new Report(null,"a", NOTIFY));
        assertThrows(IllegalArgumentException.class, () -> new Report( "","a", NOTIFY));
        assertThrows(IllegalArgumentException.class, () -> new Report( " ","a", NOTIFY));
        assertThrows(IllegalArgumentException.class, () -> new Report( "   ","a", NOTIFY));
        assertThrows(IllegalArgumentException.class, () -> new Report( "      ","a", NOTIFY));
    }

    @Test
    void getName() {
        final var reportA = new Report("ident","testA", NOTIFY);
        assertEquals("testA", reportA.getName());

        final var reportB = new Report("ident","Test B", NOTIFY);
        assertEquals("Test B", reportB.getName());

        final var reportC = new Report("ident","Test C 12\"(/&)/%$=}§§)", NOTIFY);
        assertEquals("Test C 12\"(/&)/%$=}§§)", reportC.getName());

        assertThrows(NullPointerException.class, () ->  new Report("ident",null, NOTIFY));
        assertThrows(IllegalArgumentException.class, () ->  new Report("ident","", NOTIFY));
        assertThrows(IllegalArgumentException.class, () ->  new Report("ident"," ", NOTIFY));
        assertThrows(IllegalArgumentException.class, () ->  new Report("ident","   ", NOTIFY));
        assertThrows(IllegalArgumentException.class, () ->  new Report("ident","      ", NOTIFY));
    }

    @Test
    void getType() {
        final var reportA = new Report("ident","testA", NOTIFY);
        assertEquals( NOTIFY , reportA.getType());
        final var reportB = new Report("ident","testB", FAIL);
        assertEquals( FAIL , reportB.getType());

        assertThrows(NullPointerException.class, () ->  new Report("ident","test", null));
    }

    @Test
    void getTags() {
        final var reportA = new Report("ident","testA", NOTIFY);
        assertEquals(0, reportA.getTags().length);

        final var reportB = new Report("ident","testB", NOTIFY, "Tag1");
        assertEquals(1, reportB.getTags().length);
        assertEquals("Tag1", reportB.getTags()[0]);

        final var reportC = new Report("ident","testC", NOTIFY, "Tag1", "Tag 2", "T a g 3");
        assertEquals(3, reportC.getTags().length);
        assertEquals("Tag1", reportC.getTags()[0]);
        assertEquals("Tag 2", reportC.getTags()[1]);
        assertEquals("T a g 3", reportC.getTags()[2]);
    }
}