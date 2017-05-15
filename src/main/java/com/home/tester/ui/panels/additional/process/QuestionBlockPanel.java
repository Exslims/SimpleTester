package com.home.tester.ui.panels.additional.process;

import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.panels.additional.BaseJPanel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;


public class QuestionBlockPanel extends BaseJPanel{
    @Getter
    private QuestionBlock block;
    public QuestionBlockPanel(QuestionBlock block) {
        this.block = block;
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(8,8,8,8)
        ));
        this.createView();
    }

    @Override
    protected void createView() {
        this.add(this.componentsFactory.getLabel(this.block.getTitle()), BorderLayout.PAGE_START);
    }
}
