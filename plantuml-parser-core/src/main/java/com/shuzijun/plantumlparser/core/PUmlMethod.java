package com.shuzijun.plantumlparser.core;

import java.util.LinkedList;
import java.util.List;

/**
 * plantUml方法
 *
 * @author mafayun
 */
public class PUmlMethod {

    private String visibility = "default";

    private boolean isStatic;

    private boolean isAbstract;

    private String returnType;

    private String name;

    private List<String> paramList = new LinkedList<>();

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

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public void addParam(String param) {
        this.paramList.add(param);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    // Returns a list of params with line breaks when it exceeds 
    public String paramsAsString() {
        String result = "";
        final int MAX = 70;
        int currLineLength = name.length();
        int i = 0; 
        for (var param : paramList) {
            if (i++ > 0) {
                result += ", ";
            }
            //Insert line break with tab if line is too long
            if (param.length() + currLineLength > MAX) {
                result += "\\n\\t ";
                currLineLength = 0;
            }
            currLineLength += param.length();
            result += param;
        }
        return result;
    }
    
    @Override
    public String toString() {
        return VisibilityUtils.toCharacter(visibility) + " " + (isStatic ? "{static} " : "") + (isAbstract ? "{abstract}" : "")
                + returnType + " " + name + "("
                + (paramList.isEmpty() ? "" : paramsAsString())
                + ")";
    }

    public String getFullName(){
        return name + "("
                + (paramList.isEmpty() ? "" : paramsAsString())
                + ")";
    }
}
