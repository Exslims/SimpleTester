package com.home.tester.core.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResultBlock {
    private TestDescriptor currentTest;
    private List<QuestionBlock> passedQuestions;
    private int durationTime;
    private int rightCount;
    private int percent;
}
