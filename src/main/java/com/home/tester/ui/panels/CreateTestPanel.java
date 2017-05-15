package com.home.tester.ui.panels;

import com.home.tester.core.ApplicationReducer;
import com.home.tester.core.ApplicationState;
import com.home.tester.core.AsSubscriber;
import com.home.tester.core.SubjectsStore;
import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.core.entity.TestDescriptor;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.PageJPanel;
import com.home.tester.ui.dialog.AlertDialog;
import com.home.tester.ui.panels.additional.CheckBoxButton;
import com.home.tester.ui.panels.additional.create.CreateQuestionBlockAreaPanel;
import com.home.tester.ui.panels.additional.create.CreateQuestionBlockPanel;
import com.home.tester.ui.panels.additional.UIUtils;
import com.home.tester.ui.panels.utils.VerticalScrollContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateTestPanel extends PageJPanel<TestDescriptor> implements AsSubscriber{
    private VerticalScrollContainer entriesContainer;
    private JPanel root;
    @Override
    protected void init() {
        if(this.payload != null) {
            this.removeAll();
            this.createNavigationBar();
            this.createForm();
            this.subscribe();
        }
    }
    private void createForm(){
        this.root = this.componentsFactory.getJPanel(new BorderLayout());
        this.root.setBackground(AppThemeColor.BACKGROUND_DARK);
        this.root.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));

        this.root.add(getGeneralPanel(),BorderLayout.PAGE_START);
        this.add(getListEntryPanel(),BorderLayout.LINE_END);
        this.add(root, BorderLayout.CENTER);
    }
    private JPanel getGeneralPanel() {
        JPanel generalPanel = this.componentsFactory.getGridJPanel(2,4);
        generalPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(8,4,8,4)
        ));
        JTextField titleField = this.componentsFactory.getTextField(this.payload.getTitle());
        JTextField thresholdField = this.componentsFactory.getTextField(
                String.valueOf(this.payload.getThreshold()));

        this.bindData(titleField,DataType.TEST_TITLE);
        this.bindData(thresholdField,DataType.THRESHOLD);

        generalPanel.add(this.componentsFactory.getLabel("Title:"));
        generalPanel.add(titleField);
        generalPanel.add(this.componentsFactory.getLabel("Threshold:"));
        generalPanel.add(thresholdField);
        generalPanel.add(this.componentsFactory.getLabel("Randomize questions:"));
        generalPanel.add(new CheckBoxButton(this.payload.isRandomizeQuestions(), isSelected ->
                this.payload.setRandomizeQuestions(isSelected),30));
        generalPanel.add(this.componentsFactory.getLabel("Randomize answers:"));
        generalPanel.add(new CheckBoxButton(this.payload.isRandomizeAnswers(), isSelected ->
                this.payload.setRandomizeAnswers(isSelected),30));
        return UIUtils.wrapToSlide(generalPanel);
    }
    private JPanel getListEntryPanel(){
        this.entriesContainer = new VerticalScrollContainer();
        this.entriesContainer.setBackground(AppThemeColor.BACKGROUND);
        this.entriesContainer.setLayout(new BoxLayout(this.entriesContainer,BoxLayout.Y_AXIS));
        this.entriesContainer.setMaximumSize(this.getPreferredSize());
        JScrollPane scrollPane = this.componentsFactory.getScrollPane(this.entriesContainer);
        scrollPane.setPreferredSize(new Dimension(200,30));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(4,4,4,4)
        ));
        this.root.add(UIUtils.wrapToSlide(new CreateQuestionBlockAreaPanel(null)),BorderLayout.CENTER);
        JPanel miscPanel = this.componentsFactory.getJPanel(new BorderLayout());
        JButton addNewButton = this.componentsFactory.getIconButton("app/add_block.png", 30, "");
        addNewButton.addActionListener(action -> {
            QuestionBlock newBlock = new QuestionBlock("Title#", new ArrayList<>());

            SubjectsStore.blockSelectingSubject.onNext(newBlock);
            this.entriesContainer.add(new CreateQuestionBlockPanel(newBlock));
            this.payload.getQuestionBlocks().add(newBlock);
            SubjectsStore.packSubject.onNext(true);
        });
        miscPanel.add(this.componentsFactory.getLabel("Questions:"),BorderLayout.CENTER);
        miscPanel.add(addNewButton,BorderLayout.LINE_END);

        this.entriesContainer.add(miscPanel);
        this.payload.getQuestionBlocks().forEach(block -> {
            this.entriesContainer.add(new CreateQuestionBlockPanel(block));
        });

        JPanel panel = UIUtils.wrapToSlide(scrollPane);
        panel.setBorder(BorderFactory.createEmptyBorder(8,4,8,8));
        return panel;
    }

    @Override
    protected void onNext() {
        if(this.payload.getQuestionBlocks().size() > 0){
            new AlertDialog(value -> {
                if(value) {
                    SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.DASHBOARD, null));
                }
            },"Do you want to continue?",this);
        }

    }

    @Override
    protected void onBack() {
        new AlertDialog(value -> {
            if(value) {
                SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.DASHBOARD, null));
            }
        },"Do you want to quit?",this);
    }

    private void bindData(JTextField field, DataType dataType){
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (dataType){
                    case TEST_TITLE: {
                        payload.setTitle(field.getText());
                        break;
                    }
                    case THRESHOLD: {
                        payload.setThreshold(Integer.valueOf(field.getText()));
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void subscribe() {
        SubjectsStore.blockRemovingSubject.subscribe(block -> {
            Arrays.stream(this.entriesContainer.getComponents()).forEach(component -> {
                if(component instanceof CreateQuestionBlockPanel){
                    if(((CreateQuestionBlockPanel) component).getBlock().equals(block)){
                        this.entriesContainer.remove(component);
                        this.payload.getQuestionBlocks().remove(block);
                        SubjectsStore.packSubject.onNext(true);
                    }
                }
            });
        });
    }

    private enum DataType {
        TEST_TITLE, THRESHOLD
    }
}
