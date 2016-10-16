del *.class
javac -cp .;mysql-connector-java-5.1.40-bin.jar *.java
jar -cfv jardir/Project04Fox.jar *.java *.class

