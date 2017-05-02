package com.home.tester.ui;


import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ComponentsFactory {
    private Font REGULAR_FONT;

    public ComponentsFactory() {
        try {
            this.REGULAR_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/Roboto-Bold.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        button.setBorder(BorderFactory.createLineBorder(AppThemeColor.FOREGROUND));
        button.setForeground(AppThemeColor.FOREGROUND);
        button.setFont(REGULAR_FONT.deriveFont(24f));
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
        button.setBorder(BorderFactory.createLineBorder(AppThemeColor.FOREGROUND));
        button.setForeground(AppThemeColor.FOREGROUND);
        button.setFont(REGULAR_FONT.deriveFont(24f));
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
}
