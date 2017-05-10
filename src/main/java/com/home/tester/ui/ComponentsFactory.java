package com.home.tester.ui;


import com.home.tester.ui.panels.utils.ScrollUI;
import com.home.tester.ui.panels.utils.VerticalScrollContainer;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ComponentsFactory {
    private Font REGULAR_FONT;
    private Font BOLD_FONT;

    public ComponentsFactory() {
        try {
            this.BOLD_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/Roboto-Bold.ttf"));
            this.REGULAR_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/Roboto-Light.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public JLabel getLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(AppThemeColor.PRIMARY_TEXT);
        label.setFont(REGULAR_FONT.deriveFont(18f));
        return label;
    }
    public JLabel getLabel(String text, int align) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(align);
        label.setForeground(AppThemeColor.PRIMARY_TEXT);
        label.setFont(REGULAR_FONT.deriveFont(18f));
        return label;
    }
    public JTextField getTextField(String text){
        JTextField textField = new JTextField(text);
        textField.setForeground(AppThemeColor.PRIMARY_TEXT);
        textField.setFont(REGULAR_FONT.deriveFont(18f));
        return textField;
    }
    public JButton getIconButton(String iconPath, int iconSize, String text) {
        JButton button = new JButton(""){
            @Override
            protected void paintBorder(Graphics g) {
                if(!this.getModel().isPressed()) {
                    super.paintBorder(g);
                }
            }
        };
        button.setBorder(BorderFactory.createLineBorder(AppThemeColor.DARK_PRIMARY_COLOR));
        button.setForeground(AppThemeColor.DARK_PRIMARY_COLOR);
        button.setFont(BOLD_FONT.deriveFont(24f));
        button.setText(text);
        button.setBackground(AppThemeColor.BACKGROUND);
        button.setFocusPainted(false);
        button.addChangeListener(e->{
            if(!button.getModel().isPressed()){
                button.setBackground(button.getBackground());
            }
        });
        button.setVerticalAlignment(SwingConstants.CENTER);
        BufferedImage icon = null;
        try {
            BufferedImage buttonIcon = ImageIO.read(getClass().getClassLoader().getResource(iconPath));
            icon = Scalr.resize(buttonIcon, iconSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(icon != null){
            button.setIcon(new ImageIcon(icon));
        }
        return button;
    }
    public JButton getButton(String text) {
        JButton button = new JButton(""){
            @Override
            protected void paintBorder(Graphics g) {
                if(!this.getModel().isPressed()) {
                    super.paintBorder(g);
                }
            }
        };
        button.setBorder(BorderFactory.createLineBorder(AppThemeColor.DARK_PRIMARY_COLOR));
        button.setForeground(AppThemeColor.DARK_PRIMARY_COLOR);
        button.setFont(BOLD_FONT.deriveFont(24f));
        button.setText(text);
        button.setBackground(AppThemeColor.BACKGROUND);
        button.setFocusPainted(false);
        button.addChangeListener(e->{
            if(!button.getModel().isPressed()){
                button.setBackground(button.getBackground());
            }
        });
        button.setVerticalAlignment(SwingConstants.CENTER);
        return button;
    }
    public JPanel getJPanel(LayoutManager layoutManager){
        JPanel panel = new JPanel(layoutManager);
        panel.setBackground(AppThemeColor.BACKGROUND);
        return panel;
    }
    public JPanel getVerticalJPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(AppThemeColor.BACKGROUND);
        return panel;
    }
    public JPanel getGridJPanel(int col,int row){
        JPanel panel = new JPanel(new GridLayout(row,col));
        panel.setBackground(AppThemeColor.BACKGROUND);
        return panel;
    }
    public JScrollPane getScrollPane(VerticalScrollContainer container){
        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        scrollPane.setBackground(AppThemeColor.BACKGROUND);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        container.getParent().setBackground(AppThemeColor.BACKGROUND);
        JScrollBar vBar = scrollPane.getVerticalScrollBar();
        vBar.setBackground(AppThemeColor.BACKGROUND);
        vBar.setUI(new ScrollUI());
        vBar.setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
        vBar.setUnitIncrement(3);
        vBar.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

        return scrollPane;
    }
}
