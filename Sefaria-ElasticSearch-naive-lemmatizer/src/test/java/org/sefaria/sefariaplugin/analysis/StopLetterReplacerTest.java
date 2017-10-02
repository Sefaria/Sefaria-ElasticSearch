package org.sefaria.sefariaplugin.analysis;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nss on 5/17/17.
 */
public class StopLetterReplacerTest {
    @Test
    public void TestGetMinifiedWord() throws Exception {
        char[] stopLetters = {'ו','י'};
        StopLetterReplacer slr = new StopLetterReplacer(stopLetters, 3);

        assertEquals("בית", slr.filterStopLetters("בית"));
        assertEquals("ימו", slr.filterStopLetters("יומו"));
    }


}