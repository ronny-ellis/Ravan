javac -cp "lib/*;src" -d bin src/data/*.java
javac -cp "lib/*;src" -d bin src/main/*.java
javac -cp "lib/*;src" -d bin src/olona/*.java
javac -cp "lib/*;src" -d bin src/view/*.java
java -cp "bin;lib/*;src" main.Main