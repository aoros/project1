package com.aoros.project1.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SimpleTriGramComparisonStrategy implements ComparisonStrategy {

    private static final Double SAME_SCORE_THRESHOLD = 0.05d;

    @Override
    public boolean areQuestionsTheSame(String question1, String question2) {
        Map<String, Integer> q1NGramMap = createGramMap(question1);
        Map<String, Integer> q2NGramMap = createGramMap(question2);

        int numOfPhrasesInCommon = getPhrasesInCommon(question1, question2);
        double score = new Double(numOfPhrasesInCommon) / (q1NGramMap.size() + q2NGramMap.size());

        return score > SAME_SCORE_THRESHOLD;
    }

    private int getNumberOfWords(String question) {
        return question.split(" ").length;
    }

    private Map<String, Integer> createGramMap(String question) {
        Map<String, Integer> gramMap = new HashMap<>();

        String[] questionArray = question.split(" ");
        switch (questionArray.length) {
            case 1:
                gramMap.put(questionArray[0], 1);
                break;
            case 2:
                gramMap.put(questionArray[0] + "," + questionArray[1], 1);
                break;
            default:
                for (int i = 0; i < questionArray.length - 2; i++) {
                    int count = 1;
                    String entry = questionArray[i] + "," + questionArray[i + 1] + "," + questionArray[i + 2];
                    if (gramMap.containsKey(entry)) {
                        count += gramMap.get(entry);
                    }
                    gramMap.put(entry, count);
                }
                break;
        }
        return gramMap;
    }

    private int getPhrasesInCommon(String question1, String question2) {
        Map<String, Integer> q1NGramMap = createGramMap(question1);
        Map<String, Integer> q2NGramMap = createGramMap(question2);

        int count = 0;
        for (Entry entry : q1NGramMap.entrySet()) {
            String q1Gram = (String) entry.getKey();
            if (q2NGramMap.containsKey(q1Gram)) {
                count += (Integer) entry.getValue() + q2NGramMap.get(q1Gram);
            }
        }
        return count;
    }
}
