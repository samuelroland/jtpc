package com.shuzijun.plantumlparser.core;

import static com.shuzijun.plantumlparser.core.Util.listToString;

import java.util.ArrayList;
import java.util.List;

/**
 * PUml视图
 *
 * @author shuzijun
 */
public class PUmlView implements PUml {

    private List<PUmlClass> pUmlClassList = new ArrayList<>();

    private List<PUmlRelation> pUmlRelationList = new ArrayList<>();

    public void addPUmlClass(PUmlClass pUmlClass) {
        pUmlClassList.add(pUmlClass);
    }

    public void addPUmlRelation(PUmlRelation pUmlRelation) {
        pUmlRelationList.add(pUmlRelation);
    }

    @Override
    public String toString() {
        return "@startuml"
               + listToString(pUmlClassList, "\n", "\n", "")
               + listToString(pUmlRelationList, "\n", "\n\n", "")
               + "\n@enduml";
    }
}
