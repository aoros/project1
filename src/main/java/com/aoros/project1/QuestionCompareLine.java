package com.aoros.project1;

public class QuestionCompareLine {

    private final int id;
    private final int qid1;
    private final int qid2;
    private final String question1;
    private final String question2;

    public QuestionCompareLine(int id, int qid1, int qid2, String question1, String question2) {
        this.id = id;
        this.qid1 = qid1;
        this.qid2 = qid2;
        this.question1 = question1;
        this.question2 = question2;
    }

    public int getId() {
        return id;
    }

    public int getQid1() {
        return qid1;
    }

    public int getQid2() {
        return qid2;
    }

    public String getQuestion1() {
        return question1;
    }

    public String getQuestion2() {
        return question2;
    }

    @Override
    public String toString() {
        return "QuestionCompareLine{" + "id=" + id + ", qid1=" + qid1 + ", qid2=" + qid2 + ", question1=" + question1 + ", question2=" + question2 + '}';
    }
}
