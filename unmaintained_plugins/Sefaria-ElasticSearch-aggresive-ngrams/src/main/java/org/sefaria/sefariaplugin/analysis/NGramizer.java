package org.sefaria.sefariaplugin.analysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nss on 2/20/17.
 */
public class NGramizer {

    private int n;

    public NGramizer(int n) {
        this.n = n;
    }

    public List<String> ngramize(String s) {
        List<String> ngrams = new ArrayList<String>();

        for (int i = 0; i <= s.length() - n; i++) {
            ngrams.add(s.substring(i,i+n));
        }

        return ngrams;
    }
}
