# 204728299 302860986
# salomor3 broyere1
compile: bin
	javac -cp src -d bin src/*/*.java
run:
	java -cp bin main/ExpressionsTest
bonus:
	java -cp bin main/SimplificationDemo
check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*/*.java
bin:
	mkdir bin