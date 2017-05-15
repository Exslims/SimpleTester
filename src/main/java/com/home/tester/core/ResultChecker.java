package com.home.tester.core;


import com.home.tester.core.entity.Answer;
import com.home.tester.core.entity.TestDescriptor;

import java.util.List;

public class ResultChecker {
    private int rCount = 0;
    /**
     * Checking result fo test.
     * @param descriptor selected test.
     * @return % of right answers.
     */
    public int getResultFrom(TestDescriptor descriptor){
        this.rCount = 0;
        int qCount = descriptor.getQuestionBlocks().size();

        descriptor.getQuestionBlocks().forEach(block -> {
            if(verifyCompliance(block.getAnswers())){
                rCount++;
            }
        });
        return (int)((this.rCount * 100f) / qCount);
    }
    private boolean verifyCompliance(List<Answer> answers){
        for (Answer answer : answers) {
            if(!(answer.isMask() == answer.isRight())){
                return false;
            }
        }
        return true;
    }
}
