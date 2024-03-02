EXECUTABLE := Test.class
SRC := *.java

all : $(EXECUTABLE)

run : $(EXECUTABLE)
	java Test

$(EXECUTABLE) : $(SRC)
	javac $(SRC)

clean:
	rm -f $(EXECUTABLE)
	rm -f *.class

.PHONY: all clean