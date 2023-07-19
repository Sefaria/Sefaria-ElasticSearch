package org.sefaria.sefariaplugin.analysis;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nss on 3/19/17.
 */
public class PrefixFilter extends TokenFilter {

    private ArrayList<Pattern> prefixPatternList = new ArrayList<Pattern>();
    private CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
    private HebrewTokenTypeAttribute hebTokAttribute = addAttribute(HebrewTokenTypeAttribute.class);
    private PositionIncrementAttribute positionIncrementAttribute =
            addAttribute(PositionIncrementAttribute.class);

    private List<String> previousTokens = new ArrayList<String>();

    public PrefixFilter(TokenStream tokenStream) {
        super(tokenStream);

        for (String pre : prefixes) {
            String prefixPatternStr = "^" + pre + "(.{3,})";
            prefixPatternList.add(Pattern.compile(prefixPatternStr));
        }
    }

    @Override
    public boolean incrementToken() throws IOException {

        if (savePrevToken())
            return true;

        // Reached the end of the token stream being processed
        if ( ! this.input.incrementToken()) {
            return false;
        }

        // Get text of the current token and remove any leading/trailing whitespace.
        String currToken =
                this.input.getAttribute(CharTermAttribute.class).toString().trim();

        if (! hebTokAttribute.isExact()) {
            List<String> prefixedStrippedList = getPrefixStrippedList(currToken);
            for (String preStrip : prefixedStrippedList) {
                previousTokens.add(preStrip);
            }

            savePrevToken();
        }
        return true;
    }

    private List<String> getPrefixStrippedList(String token) {
        List<String> prefixStrippedList = new ArrayList<String>();
        for (Pattern prePat : this.prefixPatternList) {
            Matcher m = prePat.matcher(token);
            if (m.find()) {
                prefixStrippedList.add(m.group(1));
            }
        }

        return prefixStrippedList;
    }

    private boolean savePrevToken() {
        if (!previousTokens.isEmpty()) {
            this.charTermAttribute.setEmpty();
            this.charTermAttribute.append(previousTokens.remove(0));
            this.positionIncrementAttribute.setPositionIncrement(0);
            this.hebTokAttribute.setExact(false);
            return true;
        } else {
            return false;
        }
    }

    private String[] prefixes = {
         /*   "ובכ",
            "וכב",
            "וככ",
            "וכל",
            "וכמ",
            "וכש",
            "ולכ",
            "ומה",
            "ומכ",
            "ומש",
            "ושב",
            "ושה",
            "ושכ",
            "ושל",
            "ושמ",
            "כמה",
            "כשב",
            "כשה",
            "כשכ",
            "כשל",
            "כשמ",
            "לכש",
            "משב",
            "משה",
            "משכ",
            "משל",
            "משמ",
            "שבכ",
            "שכב",
            "שככ",
            "שכל",
            "שכמ",
            "שכש",
            "שלכ",
            "שמה",
            "שמכ",
            "שמש",
    */        "בכ",
            "וב",
            "וה",
            "וכ",
            "ול",
            "ומ",
            "וש",
            "כב",
            "ככ",
            "כל",
            "כמ",
            "כש",
            "לכ",
            "מב",
            "מה",
            "מכ",
            "מל",
            "מש",
            "שב",
            "שה",
            "שכ",
            "של",
            "שמ",
            "ב",
            "ה",
            "ו",
            "כ",
            "ל",
            "מ",
            "ש"
    };
}
