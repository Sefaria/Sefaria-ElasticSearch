# Sefaria-ElasticSearch
various ElasticSearch analyzer plugins useful for analyzing ancient Hebrew

## How to install a plugin
This repo contains a few ElasticSearch plugins. Each folder in the root is another plugin.
To install:

First you need to locate the `bin` folder in your ES installation (henceforth `$ES_BIN`). This can be in one of two places:

1. If you installed ES as a service: `/usr/share/elasticsearch/bin`
2. If you downloaded the source: `$SRC_ROOT/bin`

Let's say you're trying to install a plugin in folder `$PLUGIN_ROOT`:

1. Start ElasticSearch
   a. If ElasticSearch is a service, run `service start elasticsearch`
   b. If you downloaded the source, run `$ES_BIN/elasticsearch`
2. (OPTIONAL) If you're reinsalling a plugin, first unistall it
   a. Locate the name of the plugin. This should be in `$PLUGIN_ROOT/plugin-descriptor.properties` under the `name` variable. Let's call it `$PLUGIN_NAME`
   b. `$ES_BIN/plugin remove $PLUGIN_NAME`
3. Install plugin
   a. `$ES_BIN/plugin install file://$PLUGIN_ROOT/out/artifacts/elasticsearch_analysis_sefaria_jar.zip` (NOTE: this path needs to be absolute)
   
   
   
