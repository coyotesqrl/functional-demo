package com.connexta.functional.beans;

import java.util.HashMap;
import java.util.Map;

public class Review {
    private int reviewYear;

    private Map<Integer, Integer> questionRankings = new HashMap<>();

    private Map<Integer, String> questionComments = new HashMap<>();

    public int getReviewYear() {
        return reviewYear;
    }

    public void setReviewYear(int reviewYear) {
        this.reviewYear = reviewYear;
    }

    public Map<Integer, Integer> getQuestionRankings() {
        return questionRankings;
    }

    public Integer getQuestionRanking(Integer qNum) {
        return questionRankings.get(qNum);
    }

    public Map<Integer, String> getQuestionComments() {
        return questionComments;
    }

    public String getQuestionComment(Integer qNum) {
        return questionComments.get(qNum);
    }

    public void answerQuestion(Integer qNum, Integer ranking) {
        this.answerQuestion(qNum, ranking, null);
    }

    public void answerQuestion(Integer qNum, Integer ranking, String comment) {
        questionRankings.put(qNum, ranking);
        if (comment != null) {
            questionComments.put(qNum, comment);
        }
    }
}
