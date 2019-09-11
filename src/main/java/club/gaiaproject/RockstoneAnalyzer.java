package club.gaiaproject;

import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author luoxiaolong
 **/
public class RockstoneAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        return new TokenStreamComponents(new RockstoneTokenzier());
    }

    @Override
    protected Reader initReader(String fieldName, Reader reader) {
        StringBuilder text = new StringBuilder();
        try {
            int read = reader.read();
            while (read != -1) {
                text.append((char) read);
                read = reader.read();
            }
        } catch (IOException e) {

        }
        return new StringReader(text.toString());
    }
}
