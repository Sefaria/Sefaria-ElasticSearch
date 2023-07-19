package org.sefaria.sefariaplugin.analysis;

import  org.apache.lucene.analysis.TokenFilter;
import  org.apache.lucene.analysis.TokenStream;
import  org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import  org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import  java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SefariaSemiExactFilter extends TokenFilter {

    public static final char FINAL_CHAR = '$';

    /**
     * This filter basically just outputs WORD and WORD$. this allows it slot in well with an index analyzer like hebmorph.
     * Hebmorph will save exact words with $ at the end and all valid lemmas. When searching, we just want to find matches
     * with prefixes and suffixes.
     * @param tokenStream
     */

    public SefariaSemiExactFilter(TokenStream tokenStream) {
        super(tokenStream);
    }
    private CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
    private PositionIncrementAttribute positionIncrementAttribute =
            addAttribute(PositionIncrementAttribute.class);

    private List<String> previousTokens = new ArrayList<String>();


    @Override
    public boolean incrementToken() throws IOException {

        // Loop over tokens in the token stream to find the next one that is not empty
        if (!previousTokens.isEmpty()) {
            this.charTermAttribute.setEmpty();
            this.charTermAttribute.append(previousTokens.remove(0));
            this.positionIncrementAttribute.setPositionIncrement(0);
            return true;
        }

        previousTokens.clear();
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

        previousTokens.add(nextToken);


        this.charTermAttribute.setEmpty();
        this.charTermAttribute.append(nextToken).append(FINAL_CHAR);
        this.positionIncrementAttribute.setPositionIncrement(1);


        return true;
    }
}


