package org.sefaria.sefariaplugin.analysis;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SofitLetterFilter extends TokenFilter {

    private CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
    private HebrewTokenTypeAttribute hebTokAttribute = addAttribute(HebrewTokenTypeAttribute.class);
    private PositionIncrementAttribute positionIncrementAttribute =
            addAttribute(PositionIncrementAttribute.class);

    private List<Character> sofitList;
    private List<Character> nonSofitList;


    public SofitLetterFilter(TokenStream tokenStream) {
        super(tokenStream);

        char[] sofitArray = {'ך', 'ם', 'ן', 'ף', 'ץ'};
        char[] nonSofitArray = {'כ', 'מ', 'נ', 'פ', 'צ'};
        this.sofitList = new ArrayList<Character>();
        this.nonSofitList = new ArrayList<Character>();
        for (int i = 0; i < sofitArray.length; i++) {
            sofitList.add(sofitArray[i]);
            nonSofitList.add(nonSofitArray[i]);
        }
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
            this.charTermAttribute.setEmpty().append(replaceSofits(currToken));
            this.hebTokAttribute.setExact(false);
        }

        return true;
    }

    private String replaceSofits(String in) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            char currChar = in.charAt(i);
            int ind = this.sofitList.indexOf(currChar);
            if (ind != -1) {
                out.append(this.nonSofitList.get(ind));
            } else {
                out.append(currChar);
            }
        }
        return out.toString();
    }
}
