JFLAGS := -classpath .
JD := javadoc
JDOCFLAGS := -protected -splitindex -use -author -version -d ./javadoc

SRC := $(wildcard *.java)
CLASSES := $(SRC:.java=.class)

all: $(CLASSES)

run : $(CLASSES)
	java $(JFLAGS) Test

%.class : %.java
	javac $(JFLAGS) $<

doc : $(SRC)
	javadoc $(JDOCFLAGS) $(SRC)

clean:
	rm -f $(CLASSES)

cleandoc:
	rm -rf ./javadoc

re: clean all

redoc: cleandoc doc

.PHONY: all run doc clean cleandoc re redoc