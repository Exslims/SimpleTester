package com.home.tester.ui.dialog;

import com.home.tester.core.entity.TestDescriptor;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.panels.utils.ListRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class TestSelectorDialog extends BaseDialog<TestDescriptor,List<TestDescriptor>> {
    public TestSelectorDialog(DialogCallback<TestDescriptor> callback, Component relative, List<TestDescriptor> payload) {
        super(callback, relative, payload);
        this.setLayout(new BorderLayout());
    }

    @Override
    protected void createView() {
        this.add(this.componentsFactory.getLabel("Please select the test: "),BorderLayout.PAGE_START);

        JList<TestDescriptor> list = new JList<>((TestDescriptor[]) this.payload.toArray());
        list.setCellRenderer(new ListRenderer());
        list.setBorder(BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR));
        this.add(list,BorderLayout.CENTER);

        JPanel miscPanel = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.CENTER));
        miscPanel.setBackground(AppThemeColor.BACKGROUND_DARK);
        miscPanel.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        JButton okButton = this.componentsFactory.getButton("SELECT");
        okButton.setForeground(AppThemeColor.BACKGROUND);
        okButton.setBackground(AppThemeColor.PRIMARY_COLOR);
        okButton.addActionListener(action -> {
            if(list.getSelectedValue() != null) {
                this.callback.onAction(list.getSelectedValue());
                this.setVisible(false);
                this.dispose();
            }
        });
        JButton cancelButton = this.componentsFactory.getButton("CANCEL");
        cancelButton.addActionListener(action -> {
            this.callback.onAction(null);
            this.setVisible(false);
            this.dispose();
        });

        miscPanel.add(cancelButton);
        miscPanel.add(okButton);
        this.add(miscPanel,BorderLayout.PAGE_END);
    }
}
