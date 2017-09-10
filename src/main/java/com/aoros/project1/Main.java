package com.aoros.project1;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String csvFile = "C:/Users/Lappie/Documents/BSU/CS568/test.csv";

        QuestionComparisonProcessor processor = new QuestionComparisonProcessor();
        processor.readFile(csvFile);
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvFile));

            List<QuestionCompareLine> questionData = new ArrayList<>();
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
            System.out.println("The number of lines read in were: " + questionData.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
