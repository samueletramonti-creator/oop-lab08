package it.unibo.deathnote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test ;
import static it.unibo.deathnote.api.DeathNote.RULES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.lang.Thread.sleep;

import java.text.RuleBasedCollator;
import java.util.List;

import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static String HUMAN_NAME1="Matteo Rossi";
    private static String HUMAN_NAME2="Marco Bianchi";
    private static String HUMAN_NAME3="Piero Pierini";
    private static String HUMAN_NAME4="Franco Verdi";
    private static String HUMAN_NAME5="Stefano Galli";
    private static String HUMAN_NAME6="Gigi Esposito";
    private static int FIRST_WAITING=100;
    private static int SECOND_WAITING=100;

    private DeathNoteImpl deathnote;

    @BeforeEach
    void init() {
        deathnote = new DeathNoteImpl();
    }

    @Test 
    public void testIllegalNumber() {
        //index deve prendere i valori non accetteabili
        for (int index : List.of(-1,0,RULES.size()+1)) {
        deathnote.getRule(index);
        }
    }

    @Test 
    public void testIllegalString() {
        for (int i=1;i<=RULES.size();i++) {
            String message=deathnote.getRule(i);
            assertNotNull(message);
            assertFalse(message.isBlank());
        }
    }

    @Test 
    public void testIsWrittenName() {
        assertFalse(deathnote.isNameWritten(HUMAN_NAME1));
        deathnote.writeName(HUMAN_NAME1);
        assertTrue(deathnote.isNameWritten(HUMAN_NAME1));
        assertFalse(deathnote.isNameWritten(HUMAN_NAME2));
        assertFalse(deathnote.isNameWritten(""));
    }

    @Test 
    public void testDeathCause() throws InterruptedException {
        assertFalse(deathnote.writeDeathCause("combustion"));
        deathnote.writeName(HUMAN_NAME3);
        assertEquals(deathnote.getDeathCause(HUMAN_NAME3), "heart attack");
        deathnote.writeName(HUMAN_NAME4);
        assertTrue(deathnote.writeDeathCause("karting accident"));
        assertEquals("karting accident",deathnote.getDeathCause(HUMAN_NAME4));
        sleep( FIRST_WAITING);
        assertFalse(deathnote.writeDeathCause("combustion"));
        assertEquals("karting accident",deathnote.getDeathCause(HUMAN_NAME4));
    }

    @Test 
    public void testDetailsCause() throws InterruptedException {
        assertNull(deathnote.getDeathDetails(HUMAN_NAME1));
        deathnote.writeName(HUMAN_NAME5);
        assertEquals("",deathnote.getDeathDetails(HUMAN_NAME5));
        deathnote.writeDetails("ran for too long");
        assertEquals("ran for too long",deathnote.getDeathDetails(HUMAN_NAME5));
        deathnote.writeName(HUMAN_NAME6);
        sleep(SECOND_WAITING);
        assertFalse(deathnote.writeDetails("suffered"));
        assertEquals("",deathnote.getDeathDetails(HUMAN_NAME6));
    }
}