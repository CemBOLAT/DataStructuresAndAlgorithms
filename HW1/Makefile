EXECUTABLE := Test.class
SRC := *.java

all : 
	@echo 'make    run|clean|re|rerun'

run : $(EXECUTABLE)
	@echo "Running $(EXECUTABLE)..."
	@java Test

$(EXECUTABLE) : $(SRC)
	@echo "Compiling source files..."
	@javac $(SRC)

clean:
	@echo "Cleaning up..."
	@rm -f $(EXECUTABLE)
	@rm -f *.class

re : clean all

rerun : re run

.PHONY: all clean re rerun run
