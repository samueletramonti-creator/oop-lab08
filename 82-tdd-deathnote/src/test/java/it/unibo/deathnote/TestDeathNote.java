package it.unibo.deathnote;

import org.junit.jupiter.api.Test ;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import it.unibo.deahnote.deathnote.impl.DeathNoteImpl;
import it.unibo.deathnote.api.DeathNote;

class TestDeathNote {

    private DeathNoteImpl deathnote;

    @BeforeEach
    void init() {
        deathnote = new DeathNoteImpl();
    }

    @Test 
    public void Test1() {

    }
}