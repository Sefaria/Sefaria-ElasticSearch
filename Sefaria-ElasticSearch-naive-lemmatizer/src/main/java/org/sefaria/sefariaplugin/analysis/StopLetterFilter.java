package org.sefaria.sefariaplugin.analysis;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StopLetterFilter extends TokenFilter {

    private CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
    private HebrewTokenTypeAttribute hebTokAttribute = addAttribute(HebrewTokenTypeAttribute.class);
    private PositionIncrementAttribute positionIncrementAttribute =
            addAttribute(PositionIncrementAttribute.class);
    private String stopLettersPat;
    private List<Character> stopLetterList;

    public StopLetterFilter(TokenStream tokenStream, char[] stopLetters) {
        super(tokenStream);
        this.stopLettersPat = "[";
        this.stopLetterList = new ArrayList<Character>();
        for (char ch : stopLetters) {
            stopLettersPat += ch;
            this.stopLetterList.add(ch);
        }
        this.stopLettersPat += "]";
    }

    @Override
    public boolean incrementToken() throws IOException {
        // Reached the end of the token stream being processed
        if ( ! this.input.incrementToken()) {
            return false;
        }

        String currToken =
                this.input.getAttribute(CharTermAttribute.class).toString().trim();
        if ( ! this.hebTokAttribute.isExact()) {
            this.charTermAttribute.setEmpty().append(filterStopLetters(currToken));
            this.hebTokAttribute.setExact(false);
        }

        return true;
    }

    private String filterStopLetters(String in) {
        if (in.length() <= 3) {
            return in; //don't even bother
        }
        String potentialOut = in.replaceAll(this.stopLettersPat, "");
        if (potentialOut.length() >= 3) {
            return potentialOut;
        } else {
            int maxLettersToRemove = in.length() - 3;
            int numRemoved = 0;
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                char currChar = in.charAt(i);
                if (stopLetterList.indexOf(currChar) != -1 && numRemoved < maxLettersToRemove) {
                    continue;
                }
                out.append(currChar);
            }
            return out.toString();
        }
    }
}
