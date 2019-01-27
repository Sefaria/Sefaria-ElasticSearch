package org.sefaria.sefariaplugin.plugin;
import  org.elasticsearch.index.analysis.AnalysisModule;
import  org.elasticsearch.plugins.Plugin;

public class SefariaPlugin extends Plugin {

    /* Set the name that will be assigned to this plugin. */
    @Override
    public String name() {
        return "plugin-sefaria-aggressive-ngram";
    }

    /* Return a description of this plugin. */
    @Override
    public String description() {
        return "Sefaria!";
    }

    /* This is the function that will register our analyzer with Elasticsearch. */
    public void onModule(AnalysisModule analysisModule) {
        analysisModule.addProcessor(new SefariaBinderProcessor());
    }
}
