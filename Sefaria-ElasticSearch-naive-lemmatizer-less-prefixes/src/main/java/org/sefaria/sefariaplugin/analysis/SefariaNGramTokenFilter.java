package org.sefaria.sefariaplugin.analysis;

import  org.apache.lucene.analysis.TokenFilter;
import  org.apache.lucene.analysis.TokenStream;
import  org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import  org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import  java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SefariaNGramTokenFilter extends TokenFilter {

    private NGramizer ngramizer;


    public SefariaNGramTokenFilter(TokenStream tokenStream, int n) {
        super(tokenStream);
        ngramizer = new NGramizer(n);
    }
    private CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
    private HebrewTokenTypeAttribute hebTokAttribute = addAttribute(HebrewTokenTypeAttribute.class);
    private PositionIncrementAttribute positionIncrementAttribute =
            addAttribute(PositionIncrementAttribute.class);

    private List<String> previousTokens = new ArrayList<String>();


    @Override
    public boolean incrementToken() throws IOException {

        if (savePrevToken())
            return true;

        // Reached the end of the token stream being processed
        if ( ! this.input.incrementToken()) {
            return false;
        }

        // Get text of the current token and remove any leading/trailing whitespace.
        String currToken =
                this.input.getAttribute(CharTermAttribute.class).toString().trim();

        if (! hebTokAttribute.isExact()) {
            List<String> ngrams = ngramizer.ngramize(currToken);
            for (String ngram : ngrams) {
                previousTokens.add(ngram);
            }

            savePrevToken();
        }

        return true;
    }

    private boolean savePrevToken() {
        if (!previousTokens.isEmpty()) {
            this.charTermAttribute.setEmpty();
            this.charTermAttribute.append(previousTokens.remove(0));
            this.positionIncrementAttribute.setPositionIncrement(0);
            this.hebTokAttribute.setExact(false);
            return true;
        } else {
            return false;
        }
    }
}

