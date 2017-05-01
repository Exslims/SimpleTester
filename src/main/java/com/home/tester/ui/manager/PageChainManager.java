package com.home.tester.ui.manager;


import com.home.tester.core.SubjectsStore;
import com.home.tester.core.AsSubscriber;
import com.home.tester.ui.MainFrame;
import com.home.tester.ui.panels.DashboardPanel;
import com.home.tester.ui.panels.ResultAreaPanel;
import com.home.tester.ui.panels.TestAreaPanel;

import java.awt.*;

public class PageChainManager implements AsSubscriber{
    private MainFrame mainFrame;

    private DashboardPanel dashboardPanel;
    private ResultAreaPanel resultAreaPanel;
    private TestAreaPanel testAreaPanel;

    public void start(){
        EventQueue.invokeLater(()-> {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
        this.dashboardPanel = new DashboardPanel();
        this.resultAreaPanel = new ResultAreaPanel();
        this.testAreaPanel = new TestAreaPanel();

        mainFrame.setContentPanel(dashboardPanel);
    }

    @Override
    public void subscribe() {
        SubjectsStore.stateSubject.subscribe(state -> {
            switch (state.getState()) {
                case DASHBOARD:{
                    this.mainFrame.setContentPanel(dashboardPanel);
                    break;
                }
                case RESULT_ARE: {
                    this.mainFrame.setContentPanel(resultAreaPanel);
                    break;
                }
                case TEST_AREA: {
                    this.mainFrame.setContentPanel(testAreaPanel);
                    break;
                }
            }
        });
    }
}
