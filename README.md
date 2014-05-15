RSChallengeEval
===============

Evlauation for RecSysChallenge 2014

Running Evaluation
------------------
java -jar rscevaluator-0.1-SNAPSHOT-jar-with-dependencies.jar "/path/to/testfile.dat" "/path/to/predictions"

Building jar with dependencies
------------------------------
mvn assembly:assembly -DdescriptorId=jar-with-dependencies
