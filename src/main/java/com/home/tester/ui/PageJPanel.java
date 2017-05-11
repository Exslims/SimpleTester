package com.home.tester.ui;

import com.home.tester.core.ApplicationReducer;
import com.home.tester.core.ApplicationState;
import com.home.tester.core.SubjectsStore;
import com.home.tester.ui.panels.additional.UIUtils;

import javax.swing.*;
import java.awt.*;

public abstract class PageJPanel<T> extends JPanel {
    protected JButton backButton;
    protected JButton finishButton;
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
    protected void onFinish(){
        SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.DASHBOARD,null));
    }
    public void setPayload(T payload) {
        this.payload = payload;
        this.init();
    }
    protected void createNavigationBar(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        root.setBackground(AppThemeColor.BACKGROUND_DARK);
        root.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.backButton = componentsFactory.getIconButton("app/prev_button.png",36,"");
        this.backButton.setBorder(BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR));
        this.backButton.setPreferredSize(new Dimension(110,30));
        this.backButton.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.DASHBOARD,null));
        });

        this.finishButton = componentsFactory.getIconButton("app/next_button.png",36,"");
        this.finishButton.setBorder(BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR));
        this.finishButton.setPreferredSize(new Dimension(110,30));
        this.finishButton.addActionListener(action -> onFinish());

        root.add(backButton,BorderLayout.LINE_START);
        root.add(finishButton,BorderLayout.LINE_END);
        this.add(UIUtils.wrapToSlide(root),BorderLayout.PAGE_START);
    }
}
