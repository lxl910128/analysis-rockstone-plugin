package club.gaiaproject;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.AttributeFactory;

import java.io.IOException;

import static org.apache.lucene.analysis.standard.StandardTokenizer.MAX_TOKEN_LENGTH_LIMIT;

/**
 * @author luoxiaolong
 **/
public class RockstoneTokenzier extends Tokenizer {
    /**
     * Default read buffer size
     */
    public static final int DEFAULT_BUFFER_SIZE = 256;

    private int length = -1;
    private int index = 0;
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);

    public RockstoneTokenzier() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public RockstoneTokenzier(int bufferSize) {
        if (bufferSize > MAX_TOKEN_LENGTH_LIMIT || bufferSize <= 0) {
            throw new IllegalArgumentException("maxTokenLen must be greater than 0 and less than " + MAX_TOKEN_LENGTH_LIMIT + " passed: " + bufferSize);
        }
        termAtt.resizeBuffer(bufferSize);
    }

    public RockstoneTokenzier(AttributeFactory factory, int bufferSize) {
        super(factory);
        if (bufferSize > MAX_TOKEN_LENGTH_LIMIT || bufferSize <= 0) {
            throw new IllegalArgumentException("maxTokenLen must be greater than 0 and less than " + MAX_TOKEN_LENGTH_LIMIT + " passed: " + bufferSize);
        }
        termAtt.resizeBuffer(bufferSize);
    }

    @Override
    public final boolean incrementToken() throws IOException {
        clearAttributes();
        int upto = 0;
        char[] buffer = termAtt.buffer();
        while (true) {
            int first = input.read();
            if (first == -1) break;
            // 为了标记,先读一个
            buffer[0] = (char) first;
            input.mark(1);
            upto++;

            final int length = input.read(buffer, upto, buffer.length - upto);
            // 再正常读 如果读到值则长度累加
            if (length != -1) {
                upto += length;
            }
            if (upto == buffer.length)
                buffer = termAtt.resizeBuffer(1 + buffer.length);
        }
        // 第一次需要设置长度
        if (length == -1) {
            length = upto;
        }
        termAtt.setLength(upto);
        offsetAtt.setOffset(correctOffset(index), length);
        index++;

        input.reset();

        if (length == (index - 1)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public final void end() throws IOException {
        super.end();
        // set final offset
        offsetAtt.setOffset(length, length);
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        this.length = -1;
        this.index = 0;
    }

}