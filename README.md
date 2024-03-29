# Sefaria-ElasticSearch
various ElasticSearch analyzer plugins useful for analyzing ancient Hebrew

## Plugin builds
THe latest plugin builds can be found in `Releases`. Given a `.zip` file from a release, use the instructions below to install it.


## How to install a plugin
This repo contains a few ElasticSearch plugins. Each folder in the root is another plugin.
To install:

First you need to locate the `bin` folder in your ES installation (henceforth `$ES_BIN`). This can be in one of two places:

1. If you installed ES as a service: `/usr/share/elasticsearch/bin`
2. If you downloaded the source: `$SRC_ROOT/bin`

Let's say you're trying to install a plugin in folder `$PLUGIN_ROOT`:

1. Start ElasticSearch
   - If ElasticSearch is a service, run `service start elasticsearch`
   - If you downloaded the source, run `$ES_BIN/elasticsearch`
2. (OPTIONAL) If you're reinsalling a plugin, first unistall it
   - Locate the name of the plugin. This should be in `$PLUGIN_ROOT/plugin-descriptor.properties` under the `name` variable. Let's call it `$PLUGIN_NAME`
   - `$ES_BIN/elasticsearch-plugin remove $PLUGIN_NAME`
3. Install plugin
   - `$ES_BIN/elasticsearch-plugin install file://$PLUGIN_ROOT/out/artifacts/elasticsearch_analysis_sefaria_jar.zip` (NOTE: this path needs to be absolute)
   
   
   
