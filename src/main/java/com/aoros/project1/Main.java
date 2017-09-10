package com.aoros.project1;

import com.aoros.project1.strategy.LiteralExactMatchComparisonStrategy;

public class Main {

    public static void main(String[] args) {
        String path = "C:/Users/Lappie/Documents/BSU/CS568/";
        String csvFile = path + "test.csv";

        QuestionComparisonProcessor processor = new QuestionComparisonProcessor();
        processor.readFile(csvFile);
        processor.addToStrategyList(new LiteralExactMatchComparisonStrategy());
        processor.processUntilFoundOrDone();
        processor.exportResults(path);
    }
}
