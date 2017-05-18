package com.home.tester.ui.panels.additional.process;

import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.panels.additional.BaseJPanel;
import com.home.tester.ui.panels.additional.CheckBoxButton;
import com.home.tester.ui.panels.additional.CheckCallback;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;


public class QuestionBlockPanel extends BaseJPanel{
    private GridBagConstraints checkC;
    private GridBagConstraints textC;
    @Getter
    private QuestionBlock block;
    public QuestionBlockPanel(QuestionBlock block) {
        this.block = block;
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(8,8,8,8)
        ));
        this.createView();
    }

    @Override
    protected void createView() {
        this.checkC = new GridBagConstraints();
        this.textC = new GridBagConstraints();
        this.checkC.weightx = 0.05f;
        this.textC.weightx = 0.95f;
        this.checkC.fill = GridBagConstraints.HORIZONTAL;
        this.textC.fill = GridBagConstraints.HORIZONTAL;
        this.checkC.gridx = 0;
        this.textC.gridx = 1;
        this.checkC.gridy = 0;
        this.textC.gridy = 0;

        this.add(this.componentsFactory.getLabel(this.block.getTitle(),30,AppThemeColor.PRIMARY_TEXT), BorderLayout.PAGE_START);
        JPanel qPanel = this.componentsFactory.getJPanel(new GridBagLayout());
        this.block.getAnswers().forEach(q -> {
            qPanel.add(new CheckBoxButton(q.isMask(), q::setMask,30),this.checkC);
            qPanel.add(this.componentsFactory.getLabel(q.getText(),25,AppThemeColor.PRIMARY_TEXT),this.textC);

            this.textC.gridy++;
            this.checkC.gridy++;
        });
        this.add(qPanel,BorderLayout.CENTER);
    }
}
