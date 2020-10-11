package com.example.decoder_app.filter;

import androidx.annotation.IntDef;

/**
 * @author Lokesh chennamchetty
 * @date 11/10/2020
 */

@IntDef({
        FileLanguageFilter.JAVA,
        FileLanguageFilter.CPP,
        FileLanguageFilter.PYTHON_2,
        FileLanguageFilter.C,
        FileLanguageFilter.PYTHON_3,
        FileLanguageFilter.HTML,
        FileLanguageFilter.SCALA,
        FileLanguageFilter.ALL,
})
public @interface FileLanguageFilter {
    int JAVA = 4;
    int CPP = 7;
    int PYTHON_2 = 5;
    int C = 6;
    int PYTHON_3 = 24;
    int HTML = 400;
    int SCALA = 21;
    int ALL = 0;
}
