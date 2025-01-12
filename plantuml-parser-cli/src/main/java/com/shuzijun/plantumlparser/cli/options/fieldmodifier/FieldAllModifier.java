package com.shuzijun.plantumlparser.cli.options.fieldmodifier;

import com.shuzijun.plantumlparser.core.Constant;

public class FieldAllModifier extends FieldModifierWrapper {

    protected String getOptName() {
        return "fall";
    }

    protected String getLongOptName() {
        return "field_all";
    }

    protected String getModifier() {
        return Constant.VisibilityAll;
    }

}
