package com.home.tester.ui.panels.additional;


import com.home.tester.ui.AppThemeColor;

import javax.swing.*;
import java.awt.*;

public class ResultBlockPanel extends BaseJPanel {
    private int valueResult;
    public ResultBlockPanel(int valueResult) {
        this.valueResult = valueResult;
        this.setPreferredSize(new Dimension(200,80));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(4,4,4,4)
        ));
        this.createView();
    }

    @Override
    protected void createView() {
        this.add(this.componentsFactory.getLabel("Result#" + valueResult,16, AppThemeColor.PRIMARY_TEXT), BorderLayout.CENTER);
        this.add(this.componentsFactory.getCircleProgressBar(valueResult), BorderLayout.LINE_END);
    }
}
