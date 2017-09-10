package com.aoros.project1.strategy;

public class LiteralExactMatchComparisonStrategy implements ComparisonStrategy {

    @Override
    public boolean areQuestionsTheSame(String question1, String question2) {
        return (question1.equals(question2));
    }
}
