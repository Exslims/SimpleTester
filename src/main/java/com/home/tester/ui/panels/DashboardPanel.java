package com.home.tester.ui.panels;


import com.home.tester.core.ApplicationReducer;
import com.home.tester.core.ApplicationState;
import com.home.tester.core.SubjectsStore;
import com.home.tester.ui.BaseJPanel;

import javax.swing.*;
import java.awt.*;


public class DashboardPanel extends BaseJPanel{

    @Override
    protected void init() {
        this.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        this.backButton.setVisible(false);
        this.finishButton.setVisible(false);
        JPanel root = new JPanel(new GridLayout(1,3,5,0));
        JButton openTest = this.componentsFactory.getIconButton("app/openTest.png", 128, "OPEN TEST");
        JButton createTest = this.componentsFactory.getIconButton("app/createTest.png", 128, "CREATE TEST");
        createTest.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.CREATE_TEST,null));
        });
        JButton editTest = this.componentsFactory.getIconButton("app/editTest.png", 128, "EDIT TEST");
        root.add(openTest);
        root.add(createTest);
        root.add(editTest);
        this.add(root,BorderLayout.CENTER);
    }

    @Override
    protected void validateForm() {
        /*NOPE*/
    }
}
