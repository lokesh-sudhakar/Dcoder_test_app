package com.example.decoder_app.filter;

import androidx.annotation.IntDef;

/**
 * @author Lokesh chennamchetty
 * @date 11/10/2020
 */
@IntDef({
        ProjectLanguageFilter.HTML,
        ProjectLanguageFilter.JAVA,
        ProjectLanguageFilter.PYTHON_3,
        ProjectLanguageFilter.CPP,
        ProjectLanguageFilter.VB_NET,
        ProjectLanguageFilter.C_SHARP,
        ProjectLanguageFilter.PYTHON_2,
        ProjectLanguageFilter.NODE_JS,
        ProjectLanguageFilter.C_KEY,
        ProjectLanguageFilter.SCHEME
})
public @interface ProjectLanguageFilter {

    int HTML = 1035;
    int JAVA = 1004;
    int PYTHON_3 = 1022;
    int CPP = 1007;
    int VB_NET = 1002;
    int C_SHARP = 1001;
    int PYTHON_2 = 1005;
    int NODE_JS = 1021;
    int C_KEY = 1006;
    int SCHEME = 1020;
    int ALL = 0;
}
