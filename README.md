# PlantUML generator for Java

This is a fork used by `ctp`, checkout [its repos](https://github.com/samuelroland/ctp) to use this !

**Changes made to the CLI and core logic**  
To fit my teacher's needs or just making things prettier, I did a few changes to the source code which you can read in details in the recent commits, but here is a quick recap:
1. Show types after variables name: instead of `int age` it displays `age: int`
1. Add line breaks and tabs after ~70 chars, when methods definitions are too long (this avoids creating very large class rectangle)
1. Show return type of methods at the end: instead of `String toString()` it displays `toString(): String`.
1. Do not use `<<Create>>` as return type for constructors
1. Fix intern class association: instead of `+..` (dotted line) it is now `+--` (solid line)
1. 


**Bugs**
1. Fix a bug when giving an output path as just a file (without any folder path prefix)

TODO: bug interface attribute are public by default even if not specified...

*Note*: the IntelliJ plugin build has been disabled because it currently fails and would require some changes to support the same feature as ctp. I will not make it work to have the same feature as I consider the CLI is enough and solves my problem.

*Note: before the `ctp` repos refactoring, this repos was name `jtpc` (Java To PlantUML CLI) and contained the bash CLI with post-processing, but since I supported C++ and did another fork, the tool needed a refactor, a new repos and new name. Don't wonder when you find old references to jtpc in the commit history.*

----

**See the original README below**

----

# plantuml-parser ![Gradle Package](https://github.com/shuzijun/plantuml-parser/workflows/Gradle%20Package/badge.svg) ![plugin](https://github.com/shuzijun/plantuml-parser/workflows/plugin/badge.svg)

将Java源代码转换为plantuml  
Convert the Java source code to Plantuml

## plantuml-parser-core

```java
    public static void main(String[]args)throws IOException{
        ParserConfig parserConfig=new ParserConfig();
        parserConfig.addFilePath(filePath or fileDirectory);
        parserConfig.setOutFilePath(out file path);
        parserConfig.addMethodModifier(private or protected or default or public );
        parserConfig.addFieldModifier(private or protected or default or public );

        ParserProgram parserProgram=new ParserProgram(parserConfig);
        parserProgram.execute();
        }
```

## plantuml-parser-plugin

<p align="center">
  <img src="https://raw.githubusercontent.com/shuzijun/plantuml-parser/master/doc/demo.gif" alt="demo"/>
</p> 

## output
### demo  
```puml
@startuml
class com.shuzijun.plantumlparser.core.PUmlClass {
+ String getPackageName()
+ void setPackageName(String)
+ String getClassName()
+ void setClassName(String)
+ String getClassType()
+ void setClassType(String)
+ void addPUmlFieldList(PUmlField)
+ void addPUmlMethodList(PUmlMethod)
+ String toString()
}
class com.shuzijun.plantumlparser.core.PUmlField {
+ String getVisibility()
+ void setVisibility(String)
+ boolean isStatic()
+ void setStatic(boolean)
+ String getType()
+ void setType(String)
+ String getName()
+ void setName(String)
+ String toString()
}
class com.shuzijun.plantumlparser.core.ParserConfig {
+ String getOutFilePath()
+ void setOutFilePath(String)
+ Set<File> getFilePaths()
+ void addFilePath(String)
+ void addFieldModifier(String)
+ boolean isFieldModifier(String)
+ void addMethodModifier(String)
+ boolean isMethodModifier(String)
}
class com.shuzijun.plantumlparser.core.ClassVoidVisitor {
+ void visit(ClassOrInterfaceDeclaration,PUmlView)
}
class com.shuzijun.plantumlparser.core.PUmlMethod {
+ String getVisibility()
+ void setVisibility(String)
+ boolean isStatic()
+ void setStatic(boolean)
+ boolean isAbstract()
+ void setAbstract(boolean)
+ String getReturnType()
+ void setReturnType(String)
+ String getName()
+ void setName(String)
+ List<String> getParamList()
+ void addParam(String)
+ String toString()
}
class com.shuzijun.plantumlparser.core.PUmlView {
+ void addPUmlClass(PUmlClass)
+ void addPUmlRelation(PUmlRelation)
+ String toString()
}
class com.shuzijun.plantumlparser.core.PUmlRelation {
+ void setSource(String)
+ void setTarget(String)
+ void setRelation(String)
+ String toString()
}
class com.shuzijun.plantumlparser.core.VisibilityUtils {
+ {static} String toCharacter(String)
}
class com.shuzijun.plantumlparser.core.ParserProgram {
+ void execute(ParserConfig)
}


com.github.javaparser.ast.visitor.VoidVisitorAdapter <|-- com.shuzijun.plantumlparser.core.ClassVoidVisitor
@enduml
```
