package com.home.tester.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Константин on 15.05.2017.
 */
public class ResultCheckerTest {
    @Test
    public void getResultFrom() throws Exception {
        ResultChecker checker = new ResultChecker();
        IOHelper ioHelper = new IOHelper();

        ioHelper.getLoadedTests().get(0).getQuestionBlocks().get(0).getAnswers().get(1).setMask(true);

        ioHelper.getLoadedTests().get(1).getQuestionBlocks().get(0).getAnswers().get(1).setMask(true);
        ioHelper.getLoadedTests().get(1).getQuestionBlocks().get(1).getAnswers().get(1).setMask(true);
        ioHelper.getLoadedTests().get(1).getQuestionBlocks().get(1).getAnswers().get(3).setMask(true);

        assertEquals(33,checker.getResultFrom(ioHelper.getLoadedTests().get(0)));
        assertEquals(50,checker.getResultFrom(ioHelper.getLoadedTests().get(1)));
    }
}