package com.home.tester.ui.panels.additional;


import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.ComponentsFactory;

import javax.swing.*;
import java.awt.*;

public class UIUtils {
    private static ComponentsFactory componentsFactory = new ComponentsFactory();
    public static JPanel wrapToSlide(JComponent panel){
        JPanel wrapper = componentsFactory.getJPanel(new BorderLayout());
        wrapper.setBackground(AppThemeColor.BACKGROUND_DARK);
        wrapper.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        wrapper.add(panel,BorderLayout.CENTER);
        return wrapper;
    }
}
