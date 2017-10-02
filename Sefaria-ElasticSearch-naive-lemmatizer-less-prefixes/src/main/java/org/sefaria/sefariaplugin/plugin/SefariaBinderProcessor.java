package org.sefaria.sefariaplugin.plugin;

import  org.elasticsearch.index.analysis.AnalysisModule;

public class SefariaBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    /* This is the only function that you need. It simply adds our PlusSignAnalyzerProvider class
     * to a list of bindings.
     */
    @Override
    public void processAnalyzers(AnalyzersBindings analyzersBindings) {
        analyzersBindings.processAnalyzer(SefariaAnalyzerProvider.NAME,
                SefariaAnalyzerProvider.class);
    }
}
