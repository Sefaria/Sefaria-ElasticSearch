ARG ELASTIC_VERSION

FROM docker.elastic.co/elasticsearch/elasticsearch:${ELASTIC_VERSION} AS static_source

ARG PLUGIN_VERSION
ARG ELASTIC_VERSION

COPY naive-lemmatizer-${PLUGIN_VERSION}-${ELASTIC_VERSION}.zip /
COPY naive-lemmatizer-less-prefixes-${PLUGIN_VERSION}-${ELASTIC_VERSION}.zip /

RUN bin/elasticsearch-plugin install --batch analysis-icu && \
    bin/elasticsearch-plugin install --batch file:///naive-lemmatizer-${PLUGIN_VERSION}-${ELASTIC_VERSION}.zip && \
    bin/elasticsearch-plugin install --batch file:///naive-lemmatizer-less-prefixes-${PLUGIN_VERSION}-${ELASTIC_VERSION}.zip && \ 
    rm *.zip
