package org.sefaria.sefariaplugin.plugin;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.sefaria.sefariaplugin.analysis.SefariaAnalyzer;

public class SefariaAnalyzerProvider extends AbstractIndexAnalyzerProvider<SefariaAnalyzer> {
    private final SefariaAnalyzer analyzer;

    public SefariaAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);

        analyzer = new SefariaAnalyzer();
    }

    @Override public SefariaAnalyzer get() {
        return this.analyzer;
    }
}
