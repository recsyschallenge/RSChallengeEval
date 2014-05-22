RSChallengeEval
===============
This repository contains the official evaluation for the [ACM RecSys Challenge 2014](http://www.recsyschallenge.com "RecSysChallenge").

The program calculates normalized [Discounted Cumulative Gain](http://recsyswiki.com/wiki/Discounted_Cumulative_Gain "DCG") at 10 (nDCG@10) given an input of ranking predictions and true ranking.

Running the evaluator as a standalone program
------------------
The evaluator is a Java program. To run it, download the [compiled jar](https://github.com/recsyschallenge/RSChallengeEval/releases/download/v0.1/rscevaluator-0.1-jar-with-dependencies.jar) and execute it like:
```bash
java -jar rscevaluator-0.1-SNAPSHOT-jar-with-dependencies.jar "/path/to/testfile.dat" "/path/to/predictions"
```

Using the evaluator from your Java program
---------------------------------------
You will need to compile your program with the evaluator in the classpath.
Compiling:
```bash
javac -cp ./rscevaluator-0.1-jar-with-dependencies.jar Example.java
```
Executing:
```bash
java -cp ./rscevaluator-0.1-jar-with-dependencies.jar:. Example
```

An example class using the evaluator:
```Java
import com.recsyschallenge.evaluate.Evaluator;
import java.io.IOException;

public class Example{
	public static void main(String args[]){
		try {
			Evaluator evaluator = new Evaluator("/path/to/testset.csv", "/path/to/predictions.csv");
			System.out.println(evaluator.evaluate());
		} catch (IOException e){
			System.out.println("Incorrect file paths.");
		}
	}
}
```

Building jar with dependencies
------------------------------
Should you want to build the evaluator jar yourself, you will need:
- [Maven](http://maven.apache.org/)
- Compiled and installed [rival](http://rival.recommenders.net) into your local Maven repository.

To build a jar (with dependencies), execute the following command:
```bash
mvn assembly:assembly -DdescriptorId=jar-with-dependencies
```
You can also run the file program directly with Maven, for doing so execute the following command:
```bash
mvn exec:java -Dexec.args="/path/to/testfile.dat /path/to/predictions.dat"
```
