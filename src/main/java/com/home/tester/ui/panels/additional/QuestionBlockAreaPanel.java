package com.home.tester.ui.panels.additional;

import com.home.tester.core.SubjectsStore;
import com.home.tester.core.entity.Answer;
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
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());

        JPanel titlePanel = this.componentsFactory.getGridJPanel(2,1);
        JTextField titleField = this.componentsFactory.getTextField(this.block.getTitle());
        titleField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                block.setTitle(titleField.getText());
                SubjectsStore.blockChangedSubject.onNext(block);
            }
        });

        titlePanel.add(this.componentsFactory.getLabel("Title: "));
        titlePanel.add(titleField);

        JPanel additionalPanel = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = this.componentsFactory.getIconButton("app/save_block.png", 30, "");
        additionalPanel.add(addButton);

        root.add(titlePanel,BorderLayout.CENTER);
        root.add(additionalPanel,BorderLayout.LINE_END);
        return root;
    }
    private JPanel getAnswersArea(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        JPanel answersPanel = this.componentsFactory.getGridJPanel(3, 0);

        answersPanel.add(this.componentsFactory.getLabel("",SwingConstants.CENTER));
        answersPanel.add(this.componentsFactory.getLabel("Text",SwingConstants.CENTER));
        answersPanel.add(this.componentsFactory.getLabel(""));

        this.addRow(answersPanel);

        root.add(answersPanel,BorderLayout.PAGE_START);

        JPanel additionalPanel = this.componentsFactory.getJPanel(new BorderLayout());
        JButton addRowButton = this.componentsFactory.getIconButton("app/add_answer.png", 30, "");
        addRowButton.addActionListener(action -> {
            this.addRow(answersPanel);
            SubjectsStore.blockChangedSubject.onNext(this.block);
        });
        additionalPanel.add(addRowButton,BorderLayout.PAGE_START);

        root.add(additionalPanel,BorderLayout.CENTER);
        return root;
    }
    private void addRow(JPanel answersPanel){
        Answer answer = new Answer();
        answer.setText("Answer#1");
        this.block.getAnswers().add(answer);

        JTextField answerField = this.componentsFactory.getTextField("Answer#1");
        answerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                answer.setText(answerField.getText());
                SubjectsStore.blockChangedSubject.onNext(block);
            }
        });
        CheckBoxButton checkBoxButton = new CheckBoxButton(false, isSelected -> {
            answer.setRight(isSelected);
            SubjectsStore.blockChangedSubject.onNext(this.block);
        },30);
        answersPanel.add(checkBoxButton);
        answersPanel.add(answerField);
        JButton deleteButton = this.componentsFactory.getIconButton("app/delete_answer.png", 30, "");
        deleteButton.addActionListener(e -> {
            this.block.getAnswers().remove(answer);
            answersPanel.remove(checkBoxButton);
            answersPanel.remove(answerField);
            answersPanel.remove(deleteButton);

            SubjectsStore.packSubject.onNext(true);
            SubjectsStore.blockChangedSubject.onNext(this.block);
        });
        answersPanel.add(deleteButton);

        SubjectsStore.packSubject.onNext(true);
    }
}
