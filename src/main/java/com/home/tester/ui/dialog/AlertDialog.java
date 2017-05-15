package com.home.tester.ui.dialog;

import com.home.tester.ui.AppThemeColor;

import javax.swing.*;
import java.awt.*;


public class AlertDialog extends BaseDialog<Boolean,String> {
    public AlertDialog(DialogCallback<Boolean> callback, String text, Component relative) {
        super(callback,relative,text);
    }
    protected void createView(){
        this.setLayout(new BorderLayout());
        this.add(this.componentsFactory.getLabel(this.payload,SwingConstants.CENTER),BorderLayout.PAGE_START);

        JPanel miscPanel = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.CENTER));
        miscPanel.setBackground(AppThemeColor.BACKGROUND_DARK);
        miscPanel.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        JButton okButton = this.componentsFactory.getButton("OK");
        okButton.setForeground(AppThemeColor.BACKGROUND);
        okButton.setBackground(AppThemeColor.PRIMARY_COLOR);
        okButton.addActionListener(action -> {
                this.callback.onAction(true);
                this.setVisible(false);
                this.dispose();
        });
        JButton cancelButton = this.componentsFactory.getButton("CANCEL");
        cancelButton.addActionListener(action -> {
            this.callback.onAction(false);
            this.setVisible(false);
            this.dispose();
        });

        miscPanel.add(cancelButton);
        miscPanel.add(okButton);
        this.add(miscPanel,BorderLayout.PAGE_END);
    }
}
