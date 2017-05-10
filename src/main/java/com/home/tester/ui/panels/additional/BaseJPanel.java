package com.home.tester.ui.panels.additional;

import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.ComponentsFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Константин on 10.05.2017.
 */
public abstract class BaseJPanel extends JPanel {
    protected ComponentsFactory componentsFactory = new ComponentsFactory();
    public BaseJPanel(){
        super(new BorderLayout());
        this.setBackground(AppThemeColor.BACKGROUND);
    }
    protected abstract void createView();
}
