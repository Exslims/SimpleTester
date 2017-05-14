package com.home.tester.core;


import com.home.tester.core.entity.Answer;
import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.core.entity.QuestionType;
import com.home.tester.core.entity.TestDescriptor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOHelper {
    @Getter
    private List<TestDescriptor> loadedTests = new ArrayList<>();
    public IOHelper(){
        this.loadedTests = this.getStubTests();
    }
    public void addTest(TestDescriptor descriptor){

    }
    public void removeTest(TestDescriptor descriptor){

    }
    private List<TestDescriptor> getStubTests(){
        TestDescriptor test1 = new TestDescriptor();
        test1.setTitle("Test#1");
        test1.setThreshold(5);
        test1.setTime(10);
        test1.setRandomizeAnswers(true);
        test1.setRandomizeQuestions(true);

        test1.setQuestionBlocks(Arrays.asList(
                new QuestionBlock("Question block#1", Arrays.asList(
                        new Answer("Answer#1",false),
                        new Answer("Answer#2",true),
                        new Answer("Answer#3",false)
                ), QuestionType.SINGLE),
                new QuestionBlock("Question block#1", Arrays.asList(
                        new Answer("Answer#1",false),
                        new Answer("Answer#2",true),
                        new Answer("Answer#3",false),
                        new Answer("Answer#4",true)
                ), QuestionType.MULTIPLE),
                new QuestionBlock("Question block#1", Arrays.asList(
                        new Answer("Answer#1",false),
                        new Answer("Answer#2",true),
                        new Answer("Answer#3",false)
                ), QuestionType.SINGLE)
        ));

        TestDescriptor test2 = new TestDescriptor();
        test2.setTitle("Test#1");
        test2.setThreshold(5);
        test2.setTime(10);
        test2.setRandomizeAnswers(true);
        test2.setRandomizeQuestions(true);

        test2.setQuestionBlocks(Arrays.asList(
                new QuestionBlock("Question block#1", Arrays.asList(
                        new Answer("Answer#1",false),
                        new Answer("Answer#2",true),
                        new Answer("Answer#3",false)
                ), QuestionType.SINGLE),
                new QuestionBlock("Question block#1", Arrays.asList(
                        new Answer("Answer#1",false),
                        new Answer("Answer#2",true),
                        new Answer("Answer#3",false),
                        new Answer("Answer#4",true)
                ), QuestionType.MULTIPLE),
                new QuestionBlock("Question block#1", Arrays.asList(
                        new Answer("Answer#1",false),
                        new Answer("Answer#2",true),
                        new Answer("Answer#3",false)
                ), QuestionType.SINGLE),
                new QuestionBlock("Question block#1", Arrays.asList(
                        new Answer("Answer#1",false),
                        new Answer("Answer#2",false),
                        new Answer("Answer#3",true),
                        new Answer("Answer#4",false),
                        new Answer("Answer#5",true),
                        new Answer("Answer#6",false)
                ), QuestionType.MULTIPLE)
        ));

        return Arrays.asList(test1,test2);
    }
}
