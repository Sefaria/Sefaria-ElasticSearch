package org.sefaria.sefariaplugin.analysis;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlternateSpellingFilter extends TokenFilter {
    /**
     * This class is meant to replace certain words which are known to have alternate spellings, especially in Tanakh
     */
    private CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
    private HebrewTokenTypeAttribute hebTokAttribute = addAttribute(HebrewTokenTypeAttribute.class);
    private PositionIncrementAttribute positionIncrementAttribute =
            addAttribute(PositionIncrementAttribute.class);

    private Map<String, String> alternateSpellings;
    private List<String> previousTokens;


    public AlternateSpellingFilter(TokenStream tokenStream) {
        super(tokenStream);

        this.previousTokens = new ArrayList<String>();

        this.alternateSpellings = new HashMap<String, String>();
        this.alternateSpellings.put("היא", "הוא");
    }

    @Override
    public boolean incrementToken() throws IOException {

        if (!previousTokens.isEmpty()) {
            this.charTermAttribute.setEmpty().append(previousTokens.remove(0));
            this.positionIncrementAttribute.setPositionIncrement(0);
            this.hebTokAttribute.setExact(false);
            return true;
        }

        // Reached the end of the token stream being processed
        if ( ! this.input.incrementToken()) {
            return false;
        }

        String currToken =
                this.input.getAttribute(CharTermAttribute.class).toString().trim();
        if ( ! this.hebTokAttribute.isExact()) {
            this.charTermAttribute.setEmpty().append(currToken);
            this.hebTokAttribute.setExact(false);

            String altSpell = replaceSpelling(currToken);
            if (altSpell != null) {
                this.previousTokens.add(altSpell);
            }

        }

        return true;
    }

    private String replaceSpelling(String in) {
        return this.alternateSpellings.get(in);
    }
}
