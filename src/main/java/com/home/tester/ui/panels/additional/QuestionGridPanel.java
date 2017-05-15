package com.home.tester.ui.panels.additional;


import com.home.tester.core.ResultChecker;
import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.ui.AppThemeColor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuestionGridPanel extends BaseJPanel {
    private List<QuestionBlock> questionBlocks;
    public QuestionGridPanel(List<QuestionBlock> questionBlocks) {
        this.questionBlocks = questionBlocks;
        this.setLayout(new GridLayout(0,2));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(8,8,8,8)
        ));
        this.createView();
    }

    @Override
    protected void createView() {
        ResultChecker checker = new ResultChecker();
        questionBlocks.forEach(block -> {
            JPanel cell = this.componentsFactory.getJPanel(new BorderLayout());
            cell.add(this.componentsFactory.getLabel(block.getTitle()),BorderLayout.CENTER);
            if(checker.verifyCompliance(block.getAnswers())) {
                cell.add(this.componentsFactory.getIconLabel("app/checked.png",30),BorderLayout.LINE_START);
            }else {
                cell.add(this.componentsFactory.getIconLabel("app/wrong_answer.png",30),BorderLayout.LINE_START);
            }
            this.add(cell);
        });
    }
}
