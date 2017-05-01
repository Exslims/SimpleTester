package com.home.tester.ui;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        super("Simple Tester");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(600,700));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        this.createMenuBar();
        this.pack();
    }
    private void createMenuBar(){
        JMenuBar bar = new JMenuBar();

        JMenu operations = new JMenu("Operations");
        operations.add(new JMenuItem("Open test"));
        operations.add(new JMenuItem("Open test"));
        bar.add(operations);
        this.add(bar,BorderLayout.PAGE_START);
    }
}
