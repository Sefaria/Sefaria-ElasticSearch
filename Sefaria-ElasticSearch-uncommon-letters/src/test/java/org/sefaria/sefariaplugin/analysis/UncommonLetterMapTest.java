package org.sefaria.sefariaplugin.analysis;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nss on 2/19/17.
 */
public class UncommonLetterMapTest {
    @Test
    public void TestGetMinifiedWord() throws Exception {
        UncommonLetterMap ulm = new UncommonLetterMap(3);
        assertEquals("אבג",ulm.getMinifiedWord("אבג"));
        assertEquals("בגד",ulm.getMinifiedWord("אבגד"));
        assertEquals("דגב",ulm.getMinifiedWord("דגבא"));
        assertEquals("דגב",ulm.getMinifiedWord("דגאב"));
        assertEquals("גדב",ulm.getMinifiedWord("גאדב"));
        assertEquals("צצצ",ulm.getMinifiedWord("אקראלחדגךכלחצשכדגכצדגכשדגכשדגכצצגכשדגכצ"));
        assertEquals("דג",ulm.getMinifiedWord("דג"));
        assertEquals("",ulm.getMinifiedWord(""));
        assertEquals("י",ulm.getMinifiedWord("י"));
    }
}