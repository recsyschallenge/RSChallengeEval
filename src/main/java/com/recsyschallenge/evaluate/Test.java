package com.recsyschallenge.evaluate;

import net.recommenders.rival.core.DataModel;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author <a href="http://github.com/alansaid">Alan</a>.
 */
public class Test {
    public static void main(String[] args) {

    }

    public DataModel<Long, Long> parse(File f, String token) throws IOException {
        DataModel<Long, Long> dataset = new DataModel<Long, Long>();

        Scanner sc = new Scanner(f);
        sc.findInLine("(\\d+)" + token + "(\\d+)" + token + "(\\d+)" + token + "(\\d+)");
        while (sc.hasNextLine()){

        }
        return dataset;
    }

}
