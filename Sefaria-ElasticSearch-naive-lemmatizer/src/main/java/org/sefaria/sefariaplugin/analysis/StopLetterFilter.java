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
    private StopLetterReplacer slr;

    public StopLetterFilter(TokenStream tokenStream, char[] stopLetters) {
        super(tokenStream);
        this.slr = new StopLetterReplacer(stopLetters, 3);
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
            this.charTermAttribute.setEmpty().append(this.slr.filterStopLetters(currToken));
            this.hebTokAttribute.setExact(false);
        }

        return true;
    }
}
