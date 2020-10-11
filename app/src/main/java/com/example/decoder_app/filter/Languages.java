package com.example.decoder_app.filter;

import androidx.annotation.StringDef;

/**
 * @author Lokesh chennamchetty
 * @date 12/10/2020
 */
@StringDef({
        Languages.JAVA,
        Languages.C,
        Languages.C_KEY,
        Languages.C_SHARP,
        Languages.CPP,
        Languages.HTML,
        Languages.NODE_JS,
        Languages.PYTHON_2,
        Languages.PYTHON_3,
        Languages.SCALA,
        Languages.SCHEME,
        Languages.VB_NET

})
public @interface Languages {
    String JAVA = "Java";
    String PYTHON_2 = "Python 2";
    String PYTHON_3 = "Python 3";
    String HTML = "HTML";
    String CPP = "Cpp";
    String SCALA ="Scala";
    String C = "C";
    String VB_NET = "VB.Net";
    String C_SHARP = "C Sharp";
    String NODE_JS = "Node Js";
    String C_KEY = "C Key";
    String SCHEME = "Scheme";
}
