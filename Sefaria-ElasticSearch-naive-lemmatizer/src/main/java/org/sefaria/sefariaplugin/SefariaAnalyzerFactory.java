package org.sefaria.sefariaplugin;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.plugin.analysis.AnalyzerFactory;
import org.elasticsearch.plugin.NamedComponent;
import org.elasticsearch.plugin.Inject;

import org.sefaria.sefariaplugin.analysis.SefariaAnalyzer;


@NamedComponent( "sefaria-naive-lemmatizer")
public class SefariaAnalyzerFactory implements AnalyzerFactory {

    @Inject
    public SefariaAnalyzerFactory() {
    }

    @Override
    public Analyzer create() {
        return new SefariaAnalyzer();
    }
}