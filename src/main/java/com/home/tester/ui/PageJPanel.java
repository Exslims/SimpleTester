package com.home.tester.ui;

import com.home.tester.ui.panels.additional.UIUtils;

import javax.swing.*;
import java.awt.*;

public abstract class PageJPanel<T> extends JPanel {
    protected JPanel navBar;
    protected JButton backButton;
    protected JButton nextButton;
    protected T payload;
    protected ComponentsFactory componentsFactory = new ComponentsFactory();
    protected PageJPanel(){
        super(new BorderLayout());
        this.setBackground(AppThemeColor.BACKGROUND_DARK);
        this.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        this.createNavigationBar();
        init();
    }
    protected abstract void init();
    protected abstract void onNext();
    protected abstract void onBack();
    public void setPayload(T payload) {
        this.payload = payload;
        this.init();
    }
    protected void createNavigationBar(){
        this.navBar = this.componentsFactory.getJPanel(new BorderLayout());
        this.navBar.setBackground(AppThemeColor.BACKGROUND_DARK);
        this.navBar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.backButton = componentsFactory.getIconButton("app/prev_button.png",36,"");
        this.backButton.setBorder(BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR));
        this.backButton.setPreferredSize(new Dimension(110,30));
        this.backButton.addActionListener(action -> onBack());

        this.nextButton = componentsFactory.getIconButton("app/next_button.png",36,"");
        this.nextButton.setBorder(BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR));
        this.nextButton.setPreferredSize(new Dimension(110,30));
        this.nextButton.addActionListener(action -> onNext());

        this.navBar.add(backButton,BorderLayout.LINE_START);
        this.navBar.add(nextButton,BorderLayout.LINE_END);
        this.add(UIUtils.wrapToSlide(this.navBar),BorderLayout.PAGE_START);
    }
}
