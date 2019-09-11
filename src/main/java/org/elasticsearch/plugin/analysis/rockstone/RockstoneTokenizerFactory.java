package org.elasticsearch.plugin.analysis.rockstone;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;

import club.gaiaproject.Configuration;
import club.gaiaproject.RockstoneTokenzier;

/**
 * @author luoxiaolong
 **/
public class RockstoneTokenizerFactory extends AbstractTokenizerFactory {

    private Configuration configuration;

    public RockstoneTokenizerFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, settings);
        this.configuration = new Configuration(environment, settings);
    }

    @Override
    public Tokenizer create() {
        return new RockstoneTokenzier();
    }
}
