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


    private static final Character[] LETTER_FREQS = {
            'י','ו','א','מ','ה',
            'ל','ר','נ','ב','ש',
            'ת','ד','כ','ע','ח',
            'ק','פ','ס','ט','ז',
            'ג','צ'
    };
    private List<Character> letter_freq_list;
    private int len_min_word;


    public UncommonLetterMap(int len_min_word) {
        this.letter_freq_list = Arrays.asList(LETTER_FREQS);
        this.len_min_word = len_min_word;
    }

    public String getMinifiedWord(String word) {
        Queue<LetterFreqPair> mostInfreq = new LinkedList<LetterFreqPair>();
        PriorityQueue<LetterFreqPair> infreqHeap = new PriorityQueue<LetterFreqPair>();
        for (char letter : word.toCharArray()) {
            int ind = letter_freq_list.indexOf(letter);
            if (ind != -1) {
                if (infreqHeap.size() <= this.len_min_word || ind > infreqHeap.peek().freq) {
                    LetterFreqPair lfp = new LetterFreqPair(letter, ind);
                    mostInfreq.add(lfp);
                    infreqHeap.add(lfp);
                }
                if (mostInfreq.size() > this.len_min_word) {
                    mostInfreq.remove(infreqHeap.poll());
                }
            }
        }

        String out = "";
        for (LetterFreqPair lfp : mostInfreq) {
            out += lfp.letter;
        }

        return out;
    }

    private class LetterFreqPair implements Comparable<LetterFreqPair> {
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

        public int compareTo(LetterFreqPair other) {
            return this.freq - other.freq;
        }
    }
}
