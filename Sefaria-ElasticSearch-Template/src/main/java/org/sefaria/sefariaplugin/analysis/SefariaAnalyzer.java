package org.sefaria.sefariaplugin.analysis;

import  org.apache.lucene.analysis.Analyzer;
import  org.apache.lucene.analysis.TokenStream;
import  org.apache.lucene.analysis.Tokenizer;
import  org.apache.lucene.analysis.core.LowerCaseFilter;
import  java.io.IOException;
import  java.io.Reader;

/**
 * Created by nss on 2/15/17.
 */
public class SefariaAnalyzer extends Analyzer {

    /* This is the only function that we need to override for our analyzer.
     * It takes in a java.io.Reader object and saves the tokenizer and list
     * of token filters that operate on it.
     */
    @Override
    protected TokenStreamComponents createComponents(String field) {
        Tokenizer tokenizer = new SefariaTokenizer();
        TokenStream filter = new EmptyStringTokenFilter(tokenizer);
        filter = new LowerCaseFilter(filter);
        return new TokenStreamComponents(tokenizer, filter);
    }
}
