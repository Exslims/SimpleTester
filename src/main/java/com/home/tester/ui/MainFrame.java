package com.home.tester.ui;

import com.home.tester.core.ApplicationReducer;
import com.home.tester.core.ApplicationState;
import com.home.tester.core.SubjectsStore;
import com.home.tester.ui.panels.DashboardPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    private JPanel currentPanel;
    public MainFrame() throws HeadlessException {
        super("Simple Tester");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(1000,700));
        this.pack();
    }

    public void setContentPanel(JPanel panel){
        if(currentPanel != null){
            this.remove(currentPanel);
        }
        this.add(panel,BorderLayout.CENTER);
        this.currentPanel = panel;
        this.pack();
        this.repaint();
    }
}
