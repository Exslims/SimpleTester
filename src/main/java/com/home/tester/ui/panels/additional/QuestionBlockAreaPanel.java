package com.home.tester.ui.panels.additional;

import com.home.tester.core.AsSubscriber;
import com.home.tester.core.SubjectsStore;
import com.home.tester.core.entity.Answer;
import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.ui.AppThemeColor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class QuestionBlockAreaPanel extends BaseJPanel implements AsSubscriber{
    @Getter @Setter
    private QuestionBlock block;

    private GridBagConstraints checkC;
    private GridBagConstraints textC;
    private GridBagConstraints removeC;

    public QuestionBlockAreaPanel(QuestionBlock block) {
        super();
        this.block = block;
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1,1,1,1,AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(8,4,8,4)
        ));
        this.createView();
        this.subscribe();
    }

    @Override
    protected void createView() {
        this.checkC = new GridBagConstraints();
        this.textC = new GridBagConstraints();
        this.removeC = new GridBagConstraints();

        this.checkC.weightx = 0.05f;
        this.textC.weightx = 0.9f;
        this.removeC.weightx = 0.05f;

        this.checkC.fill = GridBagConstraints.HORIZONTAL;
        this.textC.fill = GridBagConstraints.HORIZONTAL;
        this.removeC.fill = GridBagConstraints.HORIZONTAL;

        this.checkC.gridx = 0;
        this.textC.gridx = 1;
        this.removeC.gridx = 2;
        this.checkC.gridy = 0;
        this.textC.gridy = 0;
        this.removeC.gridy = 0;

        this.add(getTitlePanel(),BorderLayout.PAGE_START);
        this.add(getAnswersArea(),BorderLayout.CENTER);

    }
    private JPanel getTitlePanel(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());

        JPanel titlePanel = this.componentsFactory.getGridJPanel(2,1);
        JTextField titleField = this.componentsFactory.getTextField(this.block.getTitle());
        titleField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                block.setTitle(titleField.getText());
                SubjectsStore.blockChangedSubject.onNext(block);
            }
        });

        titlePanel.add(this.componentsFactory.getLabel("Question title: "));
        titlePanel.add(titleField);
        root.add(titlePanel,BorderLayout.CENTER);
        return root;
    }
    private JPanel getAnswersArea(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        JPanel answersPanel = this.componentsFactory.getJPanel(new GridBagLayout());

        answersPanel.add(this.componentsFactory.getLabel("",SwingConstants.CENTER),this.checkC);
        answersPanel.add(this.componentsFactory.getLabel("Text",SwingConstants.CENTER),this.textC);
        answersPanel.add(this.componentsFactory.getLabel(""),this.removeC);

        this.checkC.gridy++;
        this.textC.gridy++;
        this.removeC.gridy++;

        if(this.block.getAnswers().size() == 0){
            Answer answer = new Answer("Answer#1", false);
            this.block.getAnswers().add(answer);
            this.addRow(answersPanel, answer);
        }else {
            this.block.getAnswers().forEach(answer ->
                    this.addRow(answersPanel,answer));
        }

        root.add(answersPanel,BorderLayout.PAGE_START);

        JPanel additionalPanel = this.componentsFactory.getJPanel(new BorderLayout());
        JButton addRowButton = this.componentsFactory.getIconButton("app/add_answer.png", 30, "");
        addRowButton.addActionListener(action -> {
            Answer answer = new Answer("Answer#1", false);
            this.addRow(answersPanel,answer);
            this.block.getAnswers().add(answer);
            SubjectsStore.blockChangedSubject.onNext(this.block);
        });
        additionalPanel.add(addRowButton,BorderLayout.PAGE_START);

        root.add(additionalPanel,BorderLayout.CENTER);
        return root;
    }
    private void addRow(JPanel answersPanel, Answer answer){
        JTextField answerField = this.componentsFactory.getTextField(answer.getText());
        answerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                answer.setText(answerField.getText());
                SubjectsStore.blockChangedSubject.onNext(block);
            }
        });
        CheckBoxButton checkBoxButton = new CheckBoxButton(answer.isRight(), isSelected -> {
            answer.setRight(isSelected);
            SubjectsStore.blockChangedSubject.onNext(this.block);
        },30);
        answersPanel.add(checkBoxButton,this.checkC);
        answersPanel.add(answerField,this.textC);
        JButton deleteButton = this.componentsFactory.getIconButton("app/delete_answer.png", 30, "");
        deleteButton.addActionListener(e -> {
            this.block.getAnswers().remove(answer);
            answersPanel.remove(checkBoxButton);
            answersPanel.remove(answerField);
            answersPanel.remove(deleteButton);

            SubjectsStore.packSubject.onNext(true);
            SubjectsStore.blockChangedSubject.onNext(this.block);
        });
        answersPanel.add(deleteButton,this.removeC);

        SubjectsStore.packSubject.onNext(true);

        this.checkC.gridy++;
        this.textC.gridy++;
        this.removeC.gridy++;
    }

    @Override
    public void subscribe() {
        SubjectsStore.blockSelectingSubject.subscribe(block -> {
            this.setBlock(block);
            this.removeAll();
            if(block != null) {
                this.createView();
            }
            SubjectsStore.packSubject.onNext(true);
        });
    }
}
