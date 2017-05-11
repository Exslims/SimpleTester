package com.home.tester.ui.panels;


import com.home.tester.core.ApplicationReducer;
import com.home.tester.core.ApplicationState;
import com.home.tester.core.SubjectsStore;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.PageJPanel;

import javax.swing.*;
import java.awt.*;


public class DashboardPanel extends PageJPanel {

    @Override
    protected void init() {
        this.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        this.backButton.setVisible(false);
        this.finishButton.setVisible(false);
        JPanel root = new JPanel(new GridLayout(0,3,5,0));
        JButton openTest = this.componentsFactory.getIconButton("app/openTest.png", 128, "OPEN TEST");
        openTest.setBorder(BorderFactory.createLineBorder(AppThemeColor.PRIMARY_COLOR));
        JButton createTest = this.componentsFactory.getIconButton("app/createTest.png", 128, "CREATE TEST");
        createTest.setBorder(BorderFactory.createLineBorder(AppThemeColor.PRIMARY_COLOR));
        createTest.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.CREATE_TEST,null));
        });
        JButton editTest = this.componentsFactory.getIconButton("app/editTest.png", 128, "EDIT TEST");
        editTest.setBorder(BorderFactory.createLineBorder(AppThemeColor.PRIMARY_COLOR));
        root.add(openTest);
        root.add(createTest);
        root.add(editTest);
        this.add(root,BorderLayout.CENTER);
    }
}
