package com.aoros.project1;

public class Main {

    public static void main(String[] args) {
        String path = "F:/Users/Daddio/Documents/NetBeansProjects/Data/project1_data/";
        String csvFile = path + "test.csv";

        QuestionComparisonProcessor processor = new QuestionComparisonProcessor();
        processor.readFile(csvFile);
        processor.processOneStrategy("TF_IDF_CosineSimilarityComparisonStrategy");
//        processor.exportResults(path);
    }
}
