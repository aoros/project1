package com.aoros.project1.strategy;

public class ExactMatchComparisonStrategy implements ComparisonStrategy {

    @Override
    public boolean areQuestionsTheSame(String question1, String question2) {
        return false;
    }

}
