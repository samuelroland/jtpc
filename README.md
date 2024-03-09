# JTPC
An experimental CLI wrapper (around the `plantuml-parser-cli`) to generate PlantUML class diagrams from Java source code. JTPC means **J**ava **T**o **P**lantUML **C**LI. I develop this CLI in a fork of original project [shuzijun/plantuml-parser](https://github.com/shuzijun/plantuml-parser). 

**Status**
I don't plan to maintain this project, I want to "just make it work" and will not expand it further. But I'm happy to document how to use it and how it works in case it's useful to someone else in my class or outside. I guess a lot of IT students are learning and need to have up-to-date UML diagrams... If you want to do other changes feel free to fork the project and continue on your own :).

**Context**  
The original problem is that it's tedious to keep your class diagrams up-to-date and it's boring because most of the work can be automated. I found this project `shuzijun/plantuml-parser` that works for 95% of what I need which is great, but I needed a way to make post operations without loosing them at each regeneration and a few changes in the generated PlantUML text. By the way, thanks a lot to @shuzijun for your effort in making in this nice tool ! I will keep the same licence, namely [Apache-2.0](LICENSE) to enable upstream to take back some changes if needed...

*Note*: the IntelliJ plugin build has been disabled because it currently fails and would require some changes to support the same feature as JTPC. I will not make it work to have the same feature as I consider the CLI is enough and solves my problem.

## Examples
Here a few examples of schema, so you can see the final rendering. All examples are placed under [jtpc/examples folder](jtpc/examples).

**TODO**

## How to setup
*I know this is not a straightforward setup but is the best I can do in the short time I invest in this mini project...*
1. Clone the repository
   ```sh
   git clone git@github.com:samuelroland/jtpc.git
   ```
1. Build the CLI with all dependencies included (using the Gradle wrapper, it will install Gradle if necessary)
   ```sh
   ./gradlew uberJar
   ```
1. Build the Docker image
   ```sh
    docker build -t jtpc -f jtpc/Dockerfile .
   ```
1. Run the CLI
   Note: We mount the current folder to `/cli/code` in container, to give it access to the current folder. Therefore, the given path (first argument) needs to be 
    ```
    docker run -v .:/cli/code jtpc
    ```
1. Instead of typing `docker run -v .:/cli/code jtpc` everytime I recommend to setup an alias like this. You can persist this alias in your shell config (i.e. `.bashrc`)
    ```sh
    alias jtpc="docker run -v .:/cli/code jtpc"
    ```
    You can now just run 
    ```sh
    jtpc src/main diagram.puml
    ```
See more options and usage below.

## How to use the CLI
The first argument is a path to find Java source code, the second one is the output filename (generally a `.puml` or `.plantuml`)
```sh
jtpc src/main diagram.puml
```

Optionally, if you need to customize your schema, here is how you can do it.

**TODO**

To see all possible options of the underlying parser CLI:
```sh
jtpc parserhelp
```

## How it works
`jtpc` is just a bash script and Docker image as a convenience to easily run the `plantuml-parser-cli` developed in the original project. In addition to not needing to run `java -jar /mega/long/path/to/full/uber.jar` it enables some post operations to manually adjust the rendering.

**Changes made to the CLI and core logic**  
To fit my teacher's needs, I did a few changes to the source code which you can read in details in the recent commits, but here is a quick recap:
1. Show types after variables name: instead of `int age` it displays `age: int`
1. Add line breaks and tabs after ~70 chars, when methods definitions are too long (this avoids creating very large class rectangle)
**TODO**


## How to update
In case this repository is updated, you can pull changes and do a rebuild
```sh
git pull
./gradlew uberJar
docker build -t jtpc -f jtpc/Dockerfile .
```

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
