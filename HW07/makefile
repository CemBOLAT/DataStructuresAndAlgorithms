JFLAGS := -classpath .
JD := javadoc
JDOCFLAGS := -protected -splitindex -use -author -version -d ./javadoc

SRC := $(wildcard *.java)
CLASSES := $(SRC:.java=.class)

all: $(CLASSES)
	@echo "\033[0;32mThe Stock Management System has been created by Cemal BOLAT.\nStudent No: 210104004010\033[0m"
	@echo "\033[0;34mDefault run option is with using 'input.txt' file.\033[0m"
	@echo "\033[0;33mmake | doc | clean | cleandoc | re | redoc | fclean | run\033[0m"

run: $(CLASSES)
	@java $(JFLAGS) Main input.txt

%.class : %.java
	@javac $(JFLAGS) $<

doc : $(SRC)
	@javadoc $(JDOCFLAGS) $(SRC)
	@echo "\033[0;32mThe documentation has been created successfully.\033[0m"

clean:
	@rm -f $(CLASSES)
	@echo "\033[0;31mAll class files have been removed.\033[0m"

cleandoc:
	@rm -rf ./javadoc
	@echo "\033[0;31mThe documentation has been removed.\033[0m"

fclean: clean cleandoc

re: clean all

redoc: cleandoc doc

.PHONY: all run doc clean cleandoc re redoc fclean
