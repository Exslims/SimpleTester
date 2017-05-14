package com.home.tester.core.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestDescriptor {
    private String title;
    private List<QuestionBlock> questionBlocks = new ArrayList<>();
    private int threshold;
    private int time;
    private boolean randomizeQuestions;
    private boolean randomizeAnswers;
}
