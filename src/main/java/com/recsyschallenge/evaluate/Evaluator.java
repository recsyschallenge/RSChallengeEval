package com.recsyschallenge.evaluate;

import net.recommenders.rival.core.SimpleParser;
import net.recommenders.rival.core.DataModel;
import net.recommenders.rival.evaluation.metric.ranking.NDCG;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="http://github.com/alansaid">Alan</a>.
 */
public class Evaluator {


    /**
     * The NDCG object used to evaluate the predictions.
     */
    public NDCG ndcg;

    /**
     * The data model based on the testset.
     */
    public DataModel test;

    /**
     * The data model based on the predictions.
     */
    public DataModel predictions;

    /**
     * The value at which to evaluate NDCG, i.e. ndcg@10
     */
    public static final int AT = 10;

    /**
     * Constructor for the evaluator.
     * @param testsetPath The path to the file containing the testset.
     * @param predictionsetPath The path to the file containing the predictions.
     * @throws IOException When the files at either @testsetPath or @predictionsetPath do not exist.
     */
    public Evaluator(String testsetPath, String predictionsetPath) throws IOException{
        File testFile = new File(testsetPath);
        File predictionFile = new File(predictionsetPath);
        SimpleParser testParser = new SimpleParser();
        this.test = testParser.parseData(testFile, ",");
        this.predictions = testParser.parseData(predictionFile, ",");
        this.ndcg = new NDCG(predictions, test, new int[]{AT});
    }

    /**
     * Main method of the evaluator. Generates command line output containing the nDCG and the number of users in the testset and predictions.
     * @param args command line arguments containing the paths to the file containing the testset (args[0]) and the predictions (args[1]).
     */
    public static void main(String[] args) {
        System.out.println("RecSys Challenge 2014 Evaluator");
        System.out.println("-------------------------------\n");

        if (args.length < 2){
            System.out.println("No or too few arguments given!\n");
            System.out.println("Usage:\n------");
            System.out.println("The program takes two arguments as input.\n \t1: The test file given by organizers\n \t2: Your predictions\n\n");
            System.out.println("The arguments should be in the form: /path/to/file.dat\n");
            System.out.println("Example:");
            System.out.println("$ java -jar rscevaluator-0.1-jar-with-dependencies.jar /data/recsyschallenge/test_solution.dat /data/recsyschallenge/predictions.dat");
            System.exit(0);
        }
        Evaluator evaluator = null;
        try {
            evaluator = new Evaluator(args[0], args[1]);
        } catch (IOException e){
            System.out.println("The path of either the test or prediction file were incorrect. Double check your paths and run the evaluator again.");
            System.exit(0);
        }
        System.out.println("Evaluated " + evaluator.getNumPredictedUsers() + " users with predictions");
        System.out.println("Validated against " + evaluator.getNumTestUsers() + " users in test set\n");
        int recUsers = evaluator.getNumPredictedUsers();
        int testUsers = evaluator.getNumTestUsers();
        if (recUsers < testUsers)
            System.out.println("There were no predictions for " + (testUsers - recUsers) + " users");
        System.out.println("----------------------\n");
        System.out.println("nDCG@10: " + evaluator.evaluate());
        System.out.println("\n\n");
    }

    /**
     * Compute nDCG@10 for the files given in the constructor.
     * @return  nDCG@10
     */
    public double evaluate(){
        ndcg.compute();
        return ndcg.getValueAt(AT);
    }

    /**
     * Get the number of users in the testset.
     * @return the number of users in the testset.
     */
    public int getNumTestUsers(){
        return test.getNumUsers();
    }

    /**
     * Get the number of users in the prediction set.
     * @return the number of users in the prediction set.
     */
    public int getNumPredictedUsers(){
        return predictions.getNumUsers();
    }
}
