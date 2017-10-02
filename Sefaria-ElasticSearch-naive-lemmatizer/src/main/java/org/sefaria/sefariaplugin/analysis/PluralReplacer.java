package org.sefaria.sefariaplugin.analysis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nss on 6/22/17.
 */
public class PluralReplacer {

    private String[] pluralExceptionsArray = { "שמים"};
    private Set<String> pluralExceptionsSet;
    private String pluralPat;

    public PluralReplacer() {
        this.pluralPat = "(.{2,})(ים|ות)$";
        this.pluralExceptionsSet = new HashSet<String>();
        this.pluralExceptionsSet.addAll(Arrays.asList(pluralExceptionsArray));
    }

    public String filterPlural(String in) {
        if (this.pluralExceptionsSet.contains(in)) {
            return in;
        }
        return in.replaceFirst(this.pluralPat,"$1");
    }
}
