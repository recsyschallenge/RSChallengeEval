package com.recsyschallenge.evaluate;

import net.recommenders.rival.core.Parser;
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
        File testFile = new File(args[0]);
        File predictionFile = new File(args[1]);
        SimpleParser testParser = new SimpleParser();
        DataModel test = null;
        DataModel recs = null;
        try {
            recs = testParser.parseData(predictionFile, ",");
            test = testParser.parseData(testFile, ",");
        } catch (IOException e) {
            e.printStackTrace();
        }

        EvaluationMetric ndcg = new NDCG(recs, test, 10);
        ndcg.compute();
        System.out.println(ndcg.getValue());

    }
}
