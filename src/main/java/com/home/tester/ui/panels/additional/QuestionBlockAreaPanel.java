package com.home.tester.ui.panels.additional;

import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.ui.AppThemeColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class QuestionBlockAreaPanel extends BaseJPanel{
    private QuestionBlock block;
    public QuestionBlockAreaPanel(QuestionBlock block) {
        super();
        this.block = block;
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,1,1,1,AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(4,4,4,4)
        ));
        this.createView();
    }

    @Override
    protected void createView() {
        this.add(getTitlePanel(),BorderLayout.PAGE_START);
        this.add(getAnswersArea(),BorderLayout.CENTER);

    }
    private JPanel getTitlePanel(){
        JPanel titlePanel = this.componentsFactory.getGridJPanel(2,1);
        JTextField titleField = this.componentsFactory.getTextField(this.block.getTitle());
        titleField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                block.setTitle(titleField.getText());
            }
        });

        titlePanel.add(this.componentsFactory.getLabel("Title: "));
        titlePanel.add(titleField);

        return titlePanel;
    }
    private JPanel getAnswersArea(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        JPanel answersPanel = this.componentsFactory.getGridJPanel(3, 0);

        answersPanel.add(this.componentsFactory.getLabel("Right?",SwingConstants.CENTER));
        answersPanel.add(this.componentsFactory.getLabel("Text",SwingConstants.CENTER));
        answersPanel.add(this.componentsFactory.getLabel(""));

        JTextField answerField = this.componentsFactory.getTextField("Answer#1");

        answersPanel.add(this.componentsFactory.getLabel("yes"));
        answersPanel.add(answerField);
        JButton deleteButton = this.componentsFactory.getIconButton("app/delete_block.png", 30, "");
        deleteButton.setBorder(null);

        answersPanel.add(deleteButton);

        root.add(answersPanel,BorderLayout.PAGE_START);
        return root;
    }
}
