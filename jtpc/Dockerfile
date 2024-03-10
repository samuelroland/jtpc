# JTPC Docker image used to easily use the CLI without installing more than Docker
FROM alpine:latest
RUN apk add --no-cache openjdk21

WORKDIR /cli

COPY plantuml-parser-cli/build/libs/plantuml-parser-cli-0.0.1-uber.jar /cli/plantuml-parser-cli.jar
COPY jtpc/jtpc.sh /cli/jtpc.sh

COPY jtpc/PostMix.java .
RUN javac PostMix.java

ENTRYPOINT ["sh", "/cli/jtpc.sh"]
