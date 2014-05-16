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
        System.out.println("Usage:\n------");
        System.out.println("The program takes two arguments as input. 1: test, 2: predictions\n\n");

        System.out.println("The arguments should be in the form: /path/to/file.dat");
        if (args.length < 2){
            System.out.println("No or too few arguments given");
            System.exit(0);
        }

        File testFile = new File(args[0]);
        File predictionFile = new File(args[1]);
        SimpleParser testParser = new SimpleParser();
        DataModel test = null;
        DataModel recs = null;
        try {
            recs = testParser.parseData(predictionFile, ",");
            test = testParser.parseData(testFile, ",");
        } catch (IOException e) {
            System.out.println("The path of either the test or prediction file were incorrect. Double check your paths and run the evaluator again.");
            System.exit(0);
        }

        EvaluationMetric ndcg = new NDCG(recs, test, 10);
        ndcg.compute();
        System.out.println("Evaluated " + recs.getNumUsers() + " users with predictions against " + test.getNumUsers() + " users in validation set");
        System.out.println("nDCG@10: " + ndcg.getValue());

    }
}
