package com.home.tester.ui;

import com.home.tester.core.AsSubscriber;
import com.home.tester.core.SubjectsStore;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements AsSubscriber{
    private JPanel currentPanel;
    public MainFrame() throws HeadlessException {
        super("Simple Tester");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(1000,700));
        this.setMinimumSize(new Dimension(1000,700));
        this.subscribe();
        this.pack();
        this.setLocationRelativeTo(null);
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

    @Override
    public void subscribe() {
        SubjectsStore.packSubject.subscribe(state -> {
            this.pack();
            this.repaint();
        });
    }
}
