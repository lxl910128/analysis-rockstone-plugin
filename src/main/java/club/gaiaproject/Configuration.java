package club.gaiaproject;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;

/**
 * @author luoxiaolong
 **/
public class Configuration {

    private Environment environment;

    private Settings settings;

    private Configuration configuration;

    @Inject
    public Configuration(Environment env, Settings settings) {
        this.environment = env;
        this.settings = settings;
    }
}
