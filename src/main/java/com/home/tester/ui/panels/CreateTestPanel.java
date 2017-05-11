package com.home.tester.ui.panels;

import com.home.tester.core.AsSubscriber;
import com.home.tester.core.SubjectsStore;
import com.home.tester.core.entity.Answer;
import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.core.entity.TestDescriptor;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.PageJPanel;
import com.home.tester.ui.panels.additional.QuestionBlockAreaPanel;
import com.home.tester.ui.panels.additional.QuestionBlockPanel;
import com.home.tester.ui.panels.utils.VerticalScrollContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateTestPanel extends PageJPanel implements AsSubscriber{
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
        this.root.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));

        this.root.add(getGeneralPanel(),BorderLayout.PAGE_START);
        this.add(getListEntryPanel(),BorderLayout.LINE_END);
        this.add(root, BorderLayout.CENTER);
    }
    private JPanel getGeneralPanel() {
        JPanel generalPanel = this.componentsFactory.getGridJPanel(2,2);
        generalPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(4,4,4,4)
        ));
        JTextField titleField = this.componentsFactory.getTextField(((TestDescriptor) this.payload).getTitle());
        JTextField thresholdField = this.componentsFactory.getTextField(
                String.valueOf(((TestDescriptor) this.payload).getThreshold()));

        this.bindData(titleField,DataType.TEST_TITLE);
        this.bindData(thresholdField,DataType.THRESHOLD);

        generalPanel.add(this.componentsFactory.getLabel("Title:"));
        generalPanel.add(titleField);
        generalPanel.add(this.componentsFactory.getLabel("Threshold:"));
        generalPanel.add(thresholdField);
        return generalPanel;
    }
    private JScrollPane getListEntryPanel(){
        this.entriesContainer = new VerticalScrollContainer();
        this.entriesContainer.setBackground(AppThemeColor.BACKGROUND);
        this.entriesContainer.setLayout(new BoxLayout(this.entriesContainer,BoxLayout.Y_AXIS));
        this.entriesContainer.setMaximumSize(this.getPreferredSize());
        JScrollPane scrollPane = this.componentsFactory.getScrollPane(this.entriesContainer);
        scrollPane.setPreferredSize(new Dimension(200,30));

        JPanel miscPanel = this.componentsFactory.getJPanel(new BorderLayout());
        JButton addNewButton = this.componentsFactory.getIconButton("app/add_block.png", 30, "");


        addNewButton.addActionListener(action -> {
            Component layoutComponent = ((BorderLayout) this.root.getLayout()).getLayoutComponent(BorderLayout.CENTER);
            if(layoutComponent != null) {
                this.root.remove(layoutComponent);
            }
            QuestionBlock newBlock = new QuestionBlock("Title#", new ArrayList<>());

            this.root.add(new QuestionBlockAreaPanel(newBlock),BorderLayout.CENTER);
            this.entriesContainer.add(new QuestionBlockPanel(newBlock));

            SubjectsStore.packSubject.onNext(true);
        });
        miscPanel.add(this.componentsFactory.getLabel("Questions:"),BorderLayout.CENTER);
        miscPanel.add(addNewButton,BorderLayout.LINE_END);

        this.entriesContainer.add(miscPanel);
        ((TestDescriptor) this.payload).getQuestionBlocks().forEach(block -> {
            this.entriesContainer.add(new QuestionBlockPanel(block));
        });

        return scrollPane;
    }

    @Override
    protected void onFinish() {
        super.onFinish();
    }

    private void bindData(JTextField field, DataType dataType){
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (dataType){
                    case TEST_TITLE: {
                        ((TestDescriptor) payload).setTitle(field.getText());
                        break;
                    }
                    case THRESHOLD: {
                        ((TestDescriptor) payload).setThreshold(Integer.valueOf(field.getText()));
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
                if(component instanceof QuestionBlockPanel){
                    if(((QuestionBlockPanel) component).getBlock().equals(block)){
                        this.entriesContainer.remove(component);
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
