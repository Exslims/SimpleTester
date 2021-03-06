package com.home.tester.ui.panels;


import com.home.tester.core.*;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.PageJPanel;
import com.home.tester.ui.panels.additional.ResultBlockPanel;
import com.home.tester.ui.panels.additional.UIUtils;
import com.home.tester.ui.panels.utils.VerticalScrollContainer;

import javax.swing.*;
import java.awt.*;


public class DashboardPanel extends PageJPanel implements AsSubscriber{
    private VerticalScrollContainer recentlyContainer;
    @Override
    protected void init() {
        this.subscribe();
        this.navBar.setVisible(false);
        this.add(getOperationsPanel(),BorderLayout.CENTER);
        this.add(getRecentlyList(),BorderLayout.LINE_START);
    }

    @Override
    protected void onNext() {
        /*NOP*/
    }

    @Override
    protected void onBack() {
        /*NOP*/
    }

    private JPanel getRecentlyList() {
        this.recentlyContainer = new VerticalScrollContainer();
        this.recentlyContainer.setBackground(AppThemeColor.BACKGROUND_DARK);
        this.recentlyContainer.setLayout(new BoxLayout(this.recentlyContainer, BoxLayout.Y_AXIS));
        this.recentlyContainer.setMaximumSize(this.getPreferredSize());
        JScrollPane scrollPane = this.componentsFactory.getScrollPane(this.recentlyContainer);
        scrollPane.setPreferredSize(new Dimension(260, 30));
        scrollPane.setBackground(AppThemeColor.BACKGROUND_DARK);
        this.recentlyContainer.getParent().setBackground(AppThemeColor.BACKGROUND_DARK);
        JPanel miscPanel = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.LEFT));
        miscPanel.setBorder(BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR));
        miscPanel.add(this.componentsFactory.getLabel("Recently passed:"), BorderLayout.CENTER);

        this.recentlyContainer.add(UIUtils.wrapToSlide(miscPanel));

        return UIUtils.wrapToSlide(scrollPane);
    }
    private JPanel getOperationsPanel(){
        JPanel root = this.componentsFactory.getGridJPanel(1, 3);
        root.setBorder(BorderFactory.createEmptyBorder(200,200,200,200));
        root.setBackground(AppThemeColor.BACKGROUND_DARK);
        JButton openTest = this.componentsFactory.getButton("OPEN TEST");
        openTest.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.TEST_AREA,null));
        });
        root.add(openTest);
        JButton createTest = this.componentsFactory.getButton("CREATE TEST");
        createTest.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.CREATE_TEST,null));
        });
        root.add(createTest);
        JButton editTest = this.componentsFactory.getButton("EDIT TEST");
        editTest.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.EDIT_TEST,null)); //todo
        });
        root.add(editTest);
        return UIUtils.wrapToSlide(root);
    }

    @Override
    public void subscribe() {
        SubjectsStore.passedTestsSubject.subscribe(resultBlock -> {
            this.recentlyContainer.add(UIUtils.wrapToSlide(new ResultBlockPanel(resultBlock)));
            SubjectsStore.packSubject.onNext(true);
        });
    }
}
