# Build Stable Modules - ElasticSearch v8

This CI/CD Workflow generates plugin zip files compatible with ElasticSearch 8.  Using the stable plugin API, the plugins do not need to be recompiled for each specific Elastic Release.

Builds are triggered either by manually creating a release, or by running the `Build stable ES8 Modules` workflow which will calculate a semantic release version

# (DEPRECATED) Build Modules - ElasticSearch v7

| NOTE - this is system (and plugin publication for new ElasticSearch 7 versions) is no longer supported

The CI/CD system for the Sefaria ElasticSearch Plugins will automatically generate zip files that can be used to install the plugins in specific versions of elasticsearch, as specified in the workflow file.  Additionally it publishes a container image that pre-installs the plugin on the corresponding elasticsearch container image.

The CI/CD system responds to two triggers, intended to be used under different circumstances.

## Updates to the Plugin

When the plugin itself is updated, the merged commit on the default branch should be tagged with a `v1.0.0` formatted commit, with a sensible version number increment as per the changes to the plugin.  This will create a new github release for that version, to which the built plugins will be attached.

## Updates to the targetted Elastic Version

The versions of Elastic to build against are specific in the `build.yaml' file .  If the version list is updated, a rebuild can be triggered through the actions menu in the Github UI.  This will cause the built versions to be uploaded to the most recent release prior to the rebuild.
