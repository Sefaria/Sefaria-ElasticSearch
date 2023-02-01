ARG PLUGIN_VERSION
ARG ELASTIC_VERSION

FROM docker.elastic.co/elasticsearch/elasticsearch:${ELASTIC_VERSION} AS static_source

COPY *.zip .

RUN bin/elasAticsearch-plugin install --batch analysis-icu naive-lemmatizer-${PLUGIN_VERSION}-${ELASTIC_VERSION}.zip naive-lemmatizer-less-prefixes-${PLUGIN_VERSION}-${ELASTIC_VERSION}.zip && rm *.zip
