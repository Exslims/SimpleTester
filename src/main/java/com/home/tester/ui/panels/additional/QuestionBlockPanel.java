package com.home.tester.ui.panels.additional;

import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.ComponentsFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class QuestionBlockPanel extends BaseJPanel {
    private QuestionBlock block;
    public QuestionBlockPanel(QuestionBlock block) {
        super();
        this.block = block;
        this.setBackground(AppThemeColor.BACKGROUND);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0,2,2,2),
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR)));
        this.setPreferredSize(new Dimension(250,100));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(0,2,2,2),
                        BorderFactory.createLineBorder(AppThemeColor.PRIMARY_COLOR)));
            }
        });

        this.createView();
    }

    protected void createView(){
        this.add(this.componentsFactory.getLabel(this.block.getTitle()));
    }
}
