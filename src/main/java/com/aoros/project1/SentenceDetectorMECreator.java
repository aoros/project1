package com.aoros.project1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetectorMECreator {

    private final SentenceDetectorME detector;
    private final String modelInputFileAndPath = "C:/Users/Lappie/Documents/BSU/CS568/OpenNLP_model/en-sent.bin";

    public SentenceDetectorMECreator() {
        try {
            InputStream inputStream = new FileInputStream(modelInputFileAndPath);
            SentenceModel model = new SentenceModel(inputStream);
            detector = new SentenceDetectorME(model);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public SentenceDetectorME getDetector() {
        return detector;
    }
    
    public boolean isQuestion(String sentence) {
        return sentence.endsWith("?");
    }
}
