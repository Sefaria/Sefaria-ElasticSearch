package org.sefaria.sefariaplugin.analysis;
import org.apache.lucene.util.Attribute;

/**
 * This attribute is used to pass info on tokens as parsed and identified
 * by the HebMorph tokenizer
 */
public interface HebrewTokenTypeAttribute extends Attribute{
    enum HebrewType {
        Unknown
    }

    void setType(HebrewType type);
    HebrewType getType();
    boolean isExact();
    void setExact(boolean isExact);
}
