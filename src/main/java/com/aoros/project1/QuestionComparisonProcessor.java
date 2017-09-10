package com.aoros.project1;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionComparisonProcessor {

    private final List<QuestionCompareLine> questionData = new ArrayList<>();

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

}
