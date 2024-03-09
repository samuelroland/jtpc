package com.shuzijun.plantumlparser.core;

/**
 * plantUml字段
 *
 * @author shuzijun
 */
public class PUmlField implements PUml {

    private String visibility = "default";

    private boolean isStatic;

    private String type;

    private String name;

    private String comment;

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return VisibilityUtils.toCharacter(visibility) + " " + (isStatic ? "{static} " : "")
                + name + ": " + type;
    }
}
