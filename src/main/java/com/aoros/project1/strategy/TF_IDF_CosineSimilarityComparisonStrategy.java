package com.aoros.project1.strategy;

import com.aoros.project1.QuestionCompareLine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TF_IDF_CosineSimilarityComparisonStrategy implements ComparisonStrategy {

    private final Map<String, Integer> wordToAppearanceCountMap = new HashMap<>();
    private final Integer totalNumberOfQuestions;

    public TF_IDF_CosineSimilarityComparisonStrategy(List<QuestionCompareLine> questionData) {
        // build TF(t) = numb_times_term_t_appears_in_a_doc / total_numb_terms_in_doc
        // build IDF(t) = ln(total_numb_docs / numb_docs_with_term_t_in_them)

        // this gets 'total_numb_docs'
        totalNumberOfQuestions = questionData.size() * 2;

        // this allows us to get 'numb_docs_with_term_t_in_them'
        buildWordToAppearanceCountMap(questionData);
    }

    @Override
    public boolean areQuestionsTheSame(String question1, String question2) {
        // TF(t) = numb_times_term_t_appears_in_a_doc / total_numb_terms_in_doc
 
        // this allows us to see TF(t)
        Map<String, Double> tfMapForQ1 = buildTfMap(question1.split(" "));
        Map<String, Double> tfMapForQ2 = buildTfMap(question2.split(" "));

        // still have more to do here ...
        
        return false;
    }

    private Map<String, Double> buildTfMap(String[] words) {
        Double numberOfWords = (double) words.length;
        Map<String, Integer> termCountMap = new HashMap<>();
        for (String word : words) {
            Integer count = termCountMap.get(word);
            if (count != null) {
                count++;
                termCountMap.put(word, count);
            } else {
                termCountMap.put(word, 1);
            }
        }

        Map<String, Double> tfMap = new HashMap<>();
        for (Entry entry : termCountMap.entrySet()) {
            Double count = ((Integer) entry.getValue()).doubleValue();
            tfMap.put((String) entry.getKey(), count / numberOfWords);
        }
        return tfMap;
    }

    private void buildWordToAppearanceCountMap(List<QuestionCompareLine> questionData) {
        // for each question, create a Set of words and add the sets to
        // a list (questionsWordSetsList)
        List<Set<String>> questionsWordSetsList = new ArrayList<>();
        for (QuestionCompareLine line : questionData) {
            questionsWordSetsList.add(createWordSet(line.getQuestion1()));
            questionsWordSetsList.add(createWordSet(line.getQuestion2()));
        }
        // now add the words from the list to the map
        for (Set<String> wordSet : questionsWordSetsList) {
            addWordsToMap(wordSet);
        }
    }

    private Set<String> createWordSet(String question) {
        String[] words = question.split(" ");
        Set<String> questionWordSet = new HashSet<>();
        for (String word : words) {
            questionWordSet.add(word);
        }
        return questionWordSet;
    }

    private void addWordsToMap(Set<String> wordSet) {
        for (String word : wordSet) {
            Integer count = wordToAppearanceCountMap.get(word);
            if (count != null) {
                count++;
                wordToAppearanceCountMap.put(word, count);
            } else {
                wordToAppearanceCountMap.put(word, 1);
            }
        }
    }

    protected Map<String, Integer> getWordToAppearanceCountMap() {
        return wordToAppearanceCountMap;
    }
}
