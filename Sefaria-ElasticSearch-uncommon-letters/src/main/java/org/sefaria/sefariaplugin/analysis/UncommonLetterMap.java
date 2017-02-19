package org.sefaria.sefariaplugin.analysis;

import java.util.*;

/**
 * Created by nss on 2/19/17.
 */
public class UncommonLetterMap {

    /*
                self.letter_freqs = {
                u'י': 0.0,
                u'ו': 0.2145,
                u'א': 0.2176,
                u'מ': 0.3555,
                u'ה': 0.4586,
                u'ל': 0.4704,
                u'ר': 0.4930,
                u'נ': 0.5592,
                u'ב': 0.5678,
                u'ש': 0.7007,
                u'ת': 0.7013,
                u'ד': 0.7690,
                u'כ': 0.8038,
                u'ע': 0.8362,
                u'ח': 0.8779,
                u'ק': 0.9124,
                u'פ': 0.9322,
                u'ס': 0.9805,
                u'ט': 0.9924,
                u'ז': 0.9948,
                u'ג': 0.9988,
                u'צ': 1.0
            }
     */
    public static final int LEN_MIN_WORD = 3;


    private static final Character[] LETTER_FREQS = {
            'י','ו','א','מ','ה',
            'ל','ר','נ','ב','ש',
            'ת','ד','כ','ע','ח',
            'ק','פ','ס','ט','ז',
            'ג','צ'
    };
    private List<Character> letter_freq_list;

    public UncommonLetterMap() {
        letter_freq_list = Arrays.asList(LETTER_FREQS);
    }

    public String getMinifiedWord(String word) {

        String out = "";
        for (int i = 0; i < LEN_MIN_WORD; i++) {
            out += "0";
        }
        Queue<LetterFreqPair> mostInfreq = new LinkedList<LetterFreqPair>();
        int leastInfreqInQ = LETTER_FREQS.length;
        for (char letter : word.toCharArray()) {
            int ind = LETTER_FREQS.ind
        }

        return out;
    }

    private class LetterFreqPair {
        private char letter;
        private int freq;

        public LetterFreqPair(char letter, int freq) {
            this.letter = letter;
            this.freq = freq;
        }

        public char getLetter() {
            return this.letter;
        }

        public int getFreq() {
            return this.freq;
        }
    }
}
