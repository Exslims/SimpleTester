package com.home.tester.core;


import com.home.tester.core.entity.Answer;
import com.home.tester.core.entity.QuestionBlock;
import lombok.Getter;

import java.util.List;

public class ResultChecker {
    @Getter
    private int rCount = 0;
    /**
     * Checking result fo test.
     * @param questionBlocks array of questions.
     * @return % of right answers.
     */
    public int getResultFrom(List<QuestionBlock> questionBlocks){
        this.rCount = 0;
        int qCount = questionBlocks.size();

        questionBlocks.forEach(block -> {
            if(verifyCompliance(block.getAnswers())){
                rCount++;
            }
        });
        return (int)((this.rCount * 100f) / qCount);
    }
    public boolean verifyCompliance(List<Answer> answers){
        for (Answer answer : answers) {
            if(!(answer.isMask() == answer.isRight())){
                return false;
            }
        }
        return true;
    }
}
