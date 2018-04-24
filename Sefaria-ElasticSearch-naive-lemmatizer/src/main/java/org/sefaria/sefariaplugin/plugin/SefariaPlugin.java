package org.sefaria.sefariaplugin.plugin;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import  org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

//https://amsterdam.luminis.eu/2017/01/31/creating-elasticsearch-plugin-basics/
//http://david.pilato.fr/blog/2016/10/19/adding-a-new-rest-endpoint-to-elasticsearch-updated-for-ga/
//https://github.com/duydo/elasticsearch-analysis-vietnamese/blob/master/src/main/java/org/elasticsearch/plugin/analysis/vi/AnalysisVietnamesePlugin.java
//https://www.elastic.co/guide/en/elasticsearch/plugins/current/analysis.html
//GOLD MINE? https://github.com/medcl/elasticsearch-analysis-ik
//GOLD MINE? https://github.com/medcl/elasticsearch-analysis-pinyin
//GOLD MINE? https://github.com/ofir123/elasticsearch-network-analysis
public class SefariaPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();

        extra.put("sefaria-naive-lemmatizer", SefariaAnalyzerProvider::new);

        return extra;
    }

}
