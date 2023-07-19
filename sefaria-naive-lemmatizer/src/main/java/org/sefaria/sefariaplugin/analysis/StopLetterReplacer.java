package org.sefaria.sefariaplugin.analysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nss on 5/17/17.
 */
public class StopLetterReplacer {

    private String stopLettersPat;
    private List<Character> stopLetterList;
    private int minLength;

    public StopLetterReplacer(char[] stopLetters, int minLength) {
        this.minLength = minLength;

        //dots ensure that there is a character before and after the stop letter
        this.stopLettersPat = "(.)(?:";
        this.stopLetterList = new ArrayList<>();
        boolean isFirst = true;
        for (char ch : stopLetters) {
            if (!isFirst) {
                stopLettersPat += "|";
            }
            isFirst = false;
            stopLettersPat += ch + "([^" + ch + "])"; // look for ch not followed by ch
            this.stopLetterList.add(ch);
        }
        this.stopLettersPat += ")";
    }

    public String filterStopLetters(String in) {
        if (in.length() <= this.minLength) {
            return in; //don't even bother
        }
        String potentialOut = in.replaceAll(this.stopLettersPat, "$1$2$3");
        if (potentialOut.length() >= this.minLength) {
            return potentialOut;
        } else {
            int maxLettersToRemove = in.length() - this.minLength;
            int numRemoved = 0;
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                char currChar = in.charAt(i);
                if (i != 0 && i != in.length() - 1 && stopLetterList.indexOf(currChar) != -1 && numRemoved < maxLettersToRemove) {
                    numRemoved++;
                    continue;
                }
                out.append(currChar);
            }
            return out.toString();
        }
    }
}
