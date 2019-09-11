package org.elasticsearch.plugin.analysis.rockstone;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

import club.gaiaproject.RockstoneAnalyzer;

/**
 * @author luoxiaolong
 **/
public class RockstoneAnalyzerProvider extends AbstractIndexAnalyzerProvider<RockstoneAnalyzer> {

    private final RockstoneAnalyzer analyzer;

    public RockstoneAnalyzerProvider(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, name, settings);
        
        analyzer = new RockstoneAnalyzer();
    }

    @Override
    public RockstoneAnalyzer get() {
        return analyzer;
    }
}
