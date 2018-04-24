package org.sefaria.sefariaplugin.plugin;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import  org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

public class SefariaPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();

        extra.put("sefaria-naive-lemmatizer-less-prefixes", SefariaAnalyzerProvider::new);

        return extra;
    }

}
