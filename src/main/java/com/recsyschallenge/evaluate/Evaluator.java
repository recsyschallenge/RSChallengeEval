package com.recsyschallenge.evaluate;

import net.recommenders.rival.core.SimpleParser;
import net.recommenders.rival.core.DataModel;
import net.recommenders.rival.evaluation.metric.EvaluationMetric;
import net.recommenders.rival.evaluation.metric.NDCG;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="http://github.com/alansaid">Alan</a>.
 */
public class Evaluator {

    public static void main(String[] args) {
        System.out.println("RecSys Challenge 2014 Evaluator");
        System.out.println("-------------------------------\n");

        if (args.length < 2){
            System.out.println("No or too few arguments given!\n");

            System.out.println("Usage:\n------");
            System.out.println("The program takes two arguments as input.\n \t1: The test file given by organizers\n \t2: Your predictions\n\n");

            System.out.println("The arguments should be in the form: /path/to/file.dat");

            System.exit(0);
        }

        File testFile = new File(args[0]);
        File predictionFile = new File(args[1]);
        SimpleParser testParser = new SimpleParser();
        DataModel test = null;
        DataModel recs = null;
        try {
            test = testParser.parseData(testFile, ",");
            recs = testParser.parseData(predictionFile, ",");
        } catch (IOException e) {
            System.out.println("The path of either the test or prediction file were incorrect. Double check your paths and run the evaluator again.");
            System.exit(0);
        }

        NDCG ndcg = new NDCG(recs, test, new int[]{10});
        ndcg.compute();
        System.out.println("Evaluated " + recs.getNumUsers() + " users with predictions");
        System.out.println("Validated agains " + test.getNumUsers() + " users in test set\n");
        int recusers = recs.getNumUsers();
        int testusers = test.getNumUsers();
        if (recusers < testusers)
            System.out.println("There were no predictions for " + (testusers - recusers) + " users");
        System.out.println("----------------------\n");
        System.out.println("nDCG@10: " + ndcg.getValueAt(10));
        System.out.println("\n\n");

    }
}
