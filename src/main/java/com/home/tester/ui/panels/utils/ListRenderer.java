package com.home.tester.ui.panels.utils;

import com.home.tester.core.entity.TestDescriptor;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.ComponentsFactory;

import javax.swing.*;
import java.awt.*;

public class ListRenderer extends JLabel implements ListCellRenderer<TestDescriptor> {
    private ComponentsFactory componentsFactory = new ComponentsFactory();
    @Override
    public Component getListCellRendererComponent(JList<? extends TestDescriptor> list, TestDescriptor value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = this.componentsFactory.getLabel(value.getTitle());
        if(isSelected){
            label.setBorder(BorderFactory.createLineBorder(AppThemeColor.PRIMARY_COLOR));
        }
        return label;
    }
}
