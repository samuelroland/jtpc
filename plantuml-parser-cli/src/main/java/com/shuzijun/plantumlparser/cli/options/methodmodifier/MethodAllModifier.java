package com.shuzijun.plantumlparser.cli.options.methodmodifier;

import com.shuzijun.plantumlparser.core.Constant;

public class MethodAllModifier extends MethodModifierWrapper {

    protected String getOptName() {
        return "mall";
    }

    protected String getLongOptName() {
        return "method_all";
    }

    protected String getModifier() {
        return Constant.VisibilityAll;
    }

}
