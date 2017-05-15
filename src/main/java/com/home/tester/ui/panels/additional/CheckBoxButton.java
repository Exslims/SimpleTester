package com.home.tester.ui.panels.additional;

import com.home.tester.ui.AppThemeColor;
import lombok.Getter;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class CheckBoxButton extends JButton {
    @Getter
    private boolean selected;

    private BufferedImage checkedIcon;
    private BufferedImage uncheckedIcon;

    private CheckCallback callback;

    public CheckBoxButton(boolean selected, CheckCallback checkCallback,int iconSize) {
        super();
        this.selected = selected;
        this.callback = checkCallback;
        this.setBorder(null);
        this.setForeground(AppThemeColor.PRIMARY_COLOR);
        this.setBackground(AppThemeColor.BACKGROUND);
        this.setFocusPainted(false);
        this.addChangeListener(e -> {
            if(!this.getModel().isPressed()){
                this.setBackground(this.getBackground());
            }
        });
        this.setVerticalAlignment(SwingConstants.CENTER);

        try{
            this.checkedIcon = ImageIO.read(getClass().getClassLoader().getResource("app/checked.png"));
            this.uncheckedIcon = ImageIO.read(getClass().getClassLoader().getResource("app/unchecked.png"));

            this.checkedIcon = Scalr.resize(this.checkedIcon,iconSize);
            this.uncheckedIcon = Scalr.resize(this.uncheckedIcon,iconSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        this.setSelected(this.selected);

        this.addActionListener(action -> {
            if(this.selected){
                this.setSelected(false);
                this.callback.onAction(false);
            }else {
                this.setSelected(true);
                this.callback.onAction(true);
            }
        });
    }




    @Override
    protected void paintBorder(Graphics g) {
        if(!this.getModel().isPressed()) {
            super.paintBorder(g);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        this.changeIcon();
    }
    private void changeIcon(){
        if(this.selected){
            this.setIcon(new ImageIcon(this.checkedIcon));
        }else {
            this.setIcon(new ImageIcon(this.uncheckedIcon));
        }
    }
}
