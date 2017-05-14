package com.home.tester.ui.panels.additional.process;

import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.panels.additional.BaseJPanel;
import lombok.Getter;

import javax.swing.*;


public class QuestionBlockPanel extends BaseJPanel{
    @Getter
    private QuestionBlock block;
    public QuestionBlockPanel(QuestionBlock block) {
        this.block = block;
        this.setBorder(BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR));

    }

    @Override
    protected void createView() {

    }
}
