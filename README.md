RSChallengeEval
===============
This repository contains the official evaluation for the [ACM RecSys Challenge 2014](http://www.recsyschallenge.com "RecSysChallenge").

The program calculates normalized [Discounted Cumulative Gain](http://recsyswiki.com/wiki/Discounted_Cumulative_Gain "DCG") at 10 (nDCG@10) given an input of ranking predictions and true ranking.

Running Evaluation
------------------
The evaluator is a Java program. To run it, download the [compiled jar](https://github.com/recsyschallenge/RSChallengeEval/blob/master/target/rscevaluator-0.1-SNAPSHOT-jar-with-dependencies.jar) and execute it like:
```bash
java -jar rscevaluator-0.1-SNAPSHOT-jar-with-dependencies.jar "/path/to/testfile.dat" "/path/to/predictions"
```

