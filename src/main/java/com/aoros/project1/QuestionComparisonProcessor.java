package com.aoros.project1;

import com.aoros.project1.strategy.ComparisonStrategy;
import com.aoros.project1.strategy.TF_IDF_CosineSimilarityComparisonStrategy;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionComparisonProcessor {

    private final List<QuestionCompareLine> questionData = new ArrayList<>();
    private final List<ComparisonStrategy> strategies = new ArrayList<>();

    public void readFile(String csvFile) {
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvFile));

            String[] line;
            boolean isHeaderLine = true;
            while ((line = reader.readNext()) != null) {
                if (!isHeaderLine) {
                    questionData.add(new QuestionCompareLine(
                            Integer.parseInt(line[0]),
                            Integer.parseInt(line[1]),
                            Integer.parseInt(line[2]),
                            line[3], line[4]));
                } else {
                    isHeaderLine = false;
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToStrategyList(ComparisonStrategy exactMatchComparisonStrategy) {
        strategies.add(exactMatchComparisonStrategy);
    }

    /**
     * Process the strategies on each line. The assumption is that once a
     * strategy has identified questions that are the same, then it won't be
     * processed for remaining strategies.
     */
    public void processMultipleStrategies() {
        for (QuestionCompareLine line : questionData) {
            for (ComparisonStrategy strategy : strategies) {
                if (line.getIsDuplicate() == null || line.getIsDuplicate() == 0) {
                    line.setIsDuplicate(strategy.areQuestionsTheSame(line.getQuestion1(), line.getQuestion2()));
                }
            }
        }
    }

    public void processOneStrategy(String strategy) {
        ComparisonStrategy comparisonStrategy;
        if (strategy.equals("TF_IDF_CosineSimilarityComparisonStrategy")) {
            comparisonStrategy = new TF_IDF_CosineSimilarityComparisonStrategy(questionData);
        } else {
            comparisonStrategy = strategies.get(0);
        }

        for (QuestionCompareLine line : questionData) {
            line.setIsDuplicate(comparisonStrategy.areQuestionsTheSame(line.getQuestion1(), line.getQuestion2()));
        }
    }

    public void exportResults(String path) {
        String csvPathAndFile = path + getFileName();
        CSVWriter writer;
        try {
            writer = new CSVWriter(new FileWriter(csvPathAndFile));
            writer.writeNext(new String[]{"id", "is_duplicate"});
            for (QuestionCompareLine line : questionData) {
                writer.writeNext(new String[]{Integer.toString(line.getId()), Integer.toString(line.getIsDuplicate())});
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String getFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        Date now = new Date();
        return "Result_" + dateFormat.format(now) + ".csv";
    }

}
