package com.home.tester.ui.manager;


import com.home.tester.core.IOHelper;
import com.home.tester.core.SubjectsStore;
import com.home.tester.core.AsSubscriber;
import com.home.tester.core.entity.ResultBlock;
import com.home.tester.core.entity.TestDescriptor;
import com.home.tester.ui.MainFrame;
import com.home.tester.ui.dialog.TestSelectorDialog;
import com.home.tester.ui.panels.CreateTestPanel;
import com.home.tester.ui.panels.DashboardPanel;
import com.home.tester.ui.panels.ResultAreaPanel;
import com.home.tester.ui.panels.TestAreaPanel;

import java.awt.*;

public class PageChainManager implements AsSubscriber{
    private MainFrame mainFrame;

    private DashboardPanel dashboardPanel;
    private ResultAreaPanel resultAreaPanel;
    private TestAreaPanel testAreaPanel;
    private CreateTestPanel createTestPanel;

    private IOHelper ioHelper;

    public void start(){
        EventQueue.invokeLater(()-> {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);

            this.dashboardPanel = new DashboardPanel();
            this.resultAreaPanel = new ResultAreaPanel();
            this.testAreaPanel = new TestAreaPanel();
            this.createTestPanel = new CreateTestPanel();

            this.ioHelper = new IOHelper();
            mainFrame.setContentPanel(dashboardPanel);
            subscribe();
        });
    }

    @Override
    @SuppressWarnings("all")
    public void subscribe() {
        SubjectsStore.stateSubject.subscribe(state -> {
            switch (state.getState()) {
                case DASHBOARD:{
                    this.mainFrame.setContentPanel(dashboardPanel);
                    break;
                }
                case RESULT_AREA: {
                    this.resultAreaPanel.setPayload((ResultBlock) state.getPayload());
                    this.mainFrame.setContentPanel(resultAreaPanel);
                    break;
                }
                case TEST_AREA: {
                    if(state.getPayload() != null){
                        this.testAreaPanel.setPayload((TestDescriptor) state.getPayload());
                        this.mainFrame.setContentPanel(testAreaPanel);
                    }else {
                        IOHelper ioHelper = new IOHelper();
                        new TestSelectorDialog(selectedTest -> {
                            if (selectedTest != null) {
                                this.testAreaPanel.setPayload(selectedTest);
                                this.mainFrame.setContentPanel(testAreaPanel);
                            }
                        }, null, ioHelper.getLoadedTests());
                    }
                    break;
                }
                case CREATE_TEST: {
                    this.createTestPanel.setPayload(new TestDescriptor());
                    this.mainFrame.setContentPanel(createTestPanel);
                    break;
                }
                case EDIT_TEST: {
                    new TestSelectorDialog(selectedTest -> {
                        if(selectedTest != null) {
                            this.createTestPanel.setPayload(selectedTest);
                            this.mainFrame.setContentPanel(createTestPanel);
                        }
                    },null,ioHelper.getLoadedTests());
                    break;
                }
            }
        });
    }
}
