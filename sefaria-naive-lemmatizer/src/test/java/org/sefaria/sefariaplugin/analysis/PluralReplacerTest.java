package org.sefaria.sefariaplugin.analysis;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nss on 5/17/17.
 */
public class PluralReplacerTest {
    @Test
    public void TestPluralFilter() throws Exception {
        System.out.println("Hi");
        PluralReplacer pl = new PluralReplacer();
        assertEquals("שמים", pl.filterPlural("שמים"));
        assertEquals("עצ", pl.filterPlural("עצים"));
    }


}