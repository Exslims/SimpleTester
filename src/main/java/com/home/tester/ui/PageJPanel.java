package com.home.tester.ui;

import com.home.tester.core.ApplicationReducer;
import com.home.tester.core.ApplicationState;
import com.home.tester.core.SubjectsStore;

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
        root.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.backButton = componentsFactory.getButton("BACK");
        this.backButton.setPreferredSize(new Dimension(110,30));
        this.backButton.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.DASHBOARD,null));
        });

        this.finishButton = componentsFactory.getButton("FINISH");
        this.finishButton.setPreferredSize(new Dimension(110,30));
        this.finishButton.addActionListener(action -> onFinish());
        this.finishButton.setBackground(AppThemeColor.DARK_PRIMARY_COLOR);
        this.finishButton.setForeground(AppThemeColor.BACKGROUND);

        root.add(backButton,BorderLayout.LINE_START);
        root.add(finishButton,BorderLayout.LINE_END);
        this.add(root,BorderLayout.PAGE_START);
    }
}
