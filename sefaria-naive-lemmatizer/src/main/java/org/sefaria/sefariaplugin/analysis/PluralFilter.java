package org.sefaria.sefariaplugin.analysis;

import  org.apache.lucene.analysis.TokenFilter;
import  org.apache.lucene.analysis.TokenStream;
import  org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import  org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import  java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class PluralFilter extends TokenFilter {

    private CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
    private HebrewTokenTypeAttribute hebTokAttribute = addAttribute(HebrewTokenTypeAttribute.class);
    private PositionIncrementAttribute positionIncrementAttribute =
            addAttribute(PositionIncrementAttribute.class);
    private List<String> previousTokens;
    private PluralReplacer pluralReplacer;


    public PluralFilter(TokenStream tokenStream) {
        super(tokenStream);
        this.previousTokens = new ArrayList<String>();
        this.pluralReplacer = new PluralReplacer();
    }



    @Override
    public boolean incrementToken() throws IOException {

        if (!previousTokens.isEmpty()) {
            this.charTermAttribute.setEmpty().append(previousTokens.remove(0));
            this.positionIncrementAttribute.setPositionIncrement(0);
            this.hebTokAttribute.setExact(false);
            return true;
        }

        // Loop over tokens in the token stream to find the next one that is not empty
        String nextToken = null;
        while (nextToken == null) {

            // Reached the end of the token stream being processed
            if ( ! this.input.incrementToken()) {
                return false;
            }

            // Get text of the current token and remove any leading/trailing whitespace.
            String currentTokenInStream =
                    this.input.getAttribute(CharTermAttribute.class).toString().trim();

            // Save the token if it is not an empty string
            if (currentTokenInStream.length() > 0) {
                nextToken = currentTokenInStream;
            }
        }

        previousTokens.add(pluralReplacer.filterPlural(nextToken));

        // Save the current token
        this.charTermAttribute.setEmpty().append(nextToken).append('$');
        this.positionIncrementAttribute.setPositionIncrement(1);
        this.hebTokAttribute.setExact(true);
        return true;
    }

}
