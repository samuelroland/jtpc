#!/bin/bash
## JTPC - Java To PlantUML CLI 
# Hand crafted hacky build based on forked version of plantuml-parser
# Warning: this is very experimental

## DOCS: For reference, here is the output of parser cli -h option
# usage: plantuml-parser-cli [-f <arg>] [-fdef] [-fpri] [-fpro] [-fpub] [-h]
#        [-l <arg>] [-mdef] [-mpri] [-mpro] [-mpub] [-o <arg>] [-scom]
#        [-sctr] [-sdctr] [-spkg]
#  -f,--filepath <arg>                  Set the input file/directory
#  -fdef,--field_default                Add default fields
#  -fpri,--field_private                Add private fields
#  -fpro,--field_protected              Add protected fields
#  -fpub,--field_public                 Add public fields
#  -h,--help                            Show the help
#  -l,--language_level <arg>            Set language level
#  -mdef,--method_default               Add default methods
#  -mpri,--method_private               Add private methods
#  -mpro,--method_protected             Add protected methods
#  -mpub,--method_public                Add public methods
#  -o,--outfile <arg>                   Set the output file
#  -scom,--show_comment                 Show comment
#  -sctr,--show_constructors            Show constructors
#  -sdctr,--show_default_constructors   Show default constructors
#  -spkg,--show_package                 Show package


# Constants
PARSER=plantuml-parser-cli.jar
### Alternative jar path when not running in Docker container, used during development
PLAIN_PATH=plantuml-parser-cli/build/libs/plantuml-parser-cli-0.0.1-uber.jar
if [ -f $PLAIN_PATH ]; then
    PARSER=$PLAIN_PATH
fi

PARSER_ARGS="-fdef -fpri -fpro -fpub -mdef -mpri -mpro -mpub -sctr -sdctr"

# Command "parserhelp"
if [ "$1" == "parserhelp" ]; then
    echo "Just printing the help of the parser to show possible configuration options"
    echo "Do not include -f and -o, we include them ourself via first and second argument to jtpc"
    echo ""
    java -jar ${PARSER} -h
    exit
fi

# Welcome message
echo "JTPC - Java To PlantUML CLI"
echo "Usage: <Java file or folder to parse> <Out PlantUML filename> [<Parser options file>]"
echo ""

# Checks args count
if [ $# -lt 2 ]; then
    echo "2 missing arguments at least"
    exit
fi

# Managing args (load given ones if provided)
FINAL_PARSER_ARGS="$PARSER_ARGS"
if [ ! -z $3 ]; then
    if [ -f $3 ]; then
        FINAL_PARSER_ARGS=$(cat "$3")
    else
        echo "Ignoring invalid path to args value file, using default instead"
    fi
fi

BASE_PATH=""
# If we run the docker container, we prefix all path by `/cli/code`
# this is where the current host folder is mounted in container
if [ -d /cli/code ]; then
    BASE_PATH="/cli/code/"
fi
TOSCAN="$BASE_PATH$1"

if [[ ! -f $TOSCAN && ! -d $TOSCAN ]]; then
    echo "Invalid file or folder reference to be parsed: $TOSCAN"
    echo "Here is mounted files view in /cli/code"
    ls /cli/code
    exit
fi

if [ -z $2 ]; then
    echo "No output PlantUML filename provided"
    exit
fi
OUTPUT="./$2"   ## we add ./ to avoid fail of ParserProgram.getParentFile() call

# Finally run the PUML generation !
echo "Starting parser with following configuration:"
echo "PARSER=$PARSER"
echo "PARSER_ARGS=${PARSER_ARGS}"
echo "TOSCAN=$TOSCAN"
echo "OUTPUT=$OUTPUT"

CMD="java -jar "${PARSER}" -f "${TOSCAN}" -o "${OUTPUT}" ${FINAL_PARSER_ARGS}"
echo "Final command: $CMD"
$CMD

# Post operations
# TODO

# Done !
echo ""
echo "Done ! File should be created at $OUTPUT"