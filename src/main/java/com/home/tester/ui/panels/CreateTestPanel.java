package com.home.tester.ui.panels;

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

public class CreateTestPanel extends PageJPanel {
    private VerticalScrollContainer entriesContainer;
    @Override
    protected void init() {
        if(this.payload != null) {
            this.removeAll();
            this.createNavigationBar();
            this.createForm();
        }
    }
    private void createForm(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));


        root.add(getGeneralPanel(),BorderLayout.PAGE_START);
        root.add(new QuestionBlockAreaPanel(new QuestionBlock("Title#",null)),BorderLayout.CENTER);
        this.add(getTestEntriesPanel(),BorderLayout.LINE_END);
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
    private JScrollPane getTestEntriesPanel(){
        this.entriesContainer = new VerticalScrollContainer();
        this.entriesContainer.setBackground(AppThemeColor.BACKGROUND);
        this.entriesContainer.setLayout(new BoxLayout(this.entriesContainer,BoxLayout.Y_AXIS));
        JScrollPane scrollPane = this.componentsFactory.getScrollPane(this.entriesContainer);
        for (int i = 0; i < 7; i++) {
            this.entriesContainer.add(new QuestionBlockPanel(new QuestionBlock("Block#" + i,null)));
        }
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
    private enum DataType {
        TEST_TITLE, THRESHOLD
    }
}
