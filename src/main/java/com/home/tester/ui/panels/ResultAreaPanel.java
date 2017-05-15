package com.home.tester.ui.panels;

import com.home.tester.core.ApplicationReducer;
import com.home.tester.core.ApplicationState;
import com.home.tester.core.SubjectsStore;
import com.home.tester.core.entity.ResultBlock;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.PageJPanel;
import com.home.tester.ui.panels.additional.QuestionGridPanel;
import com.home.tester.ui.panels.additional.UIUtils;

import javax.swing.*;
import java.awt.*;


public class ResultAreaPanel extends PageJPanel<ResultBlock> {
    @Override
    protected void init() {
        if(this.payload != null){
            this.removeAll();
            this.createNavigationBar();
            this.createView();
        }

    }
    private void createView(){
        this.nextButton.setVisible(false);
        this.backButton.setIcon(new ImageIcon(this.componentsFactory.getIcon("app/forse_back.png",30)));
        JPanel outer = this.componentsFactory.getJPanel(new BorderLayout());
        outer.setBorder(BorderFactory.createEmptyBorder(40,80,80,80));
        outer.setBackground(AppThemeColor.BACKGROUND_DARK);

        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        root.setBackground(AppThemeColor.BACKGROUND_DARK);
        root.add(UIUtils.wrapToSlide(new QuestionGridPanel(this.payload.getPassedQuestions())),BorderLayout.CENTER);
        root.add(UIUtils.wrapToSlide(getStatusPanel()),BorderLayout.LINE_END);
        outer.add(UIUtils.wrapToSlide(root),BorderLayout.CENTER);
        this.add(outer,BorderLayout.CENTER);
    }

    private JPanel getStatusPanel(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        root.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(4,4,4,4)
        ));
        root.add(this.componentsFactory.getLabel(
                this.payload.getCurrentTest().getTitle(),22, AppThemeColor.PRIMARY_TEXT), BorderLayout.PAGE_START);
        JProgressBar circleProgressBar = this.componentsFactory.getCircleProgressBar(this.payload.getPercent(),26f);
        circleProgressBar.setPreferredSize(new Dimension(200,50));
        root.add(circleProgressBar,BorderLayout.CENTER);

        JPanel statusPanel = this.componentsFactory.getJPanel(new BorderLayout());
        JPanel constraintsPanel = this.componentsFactory.getVerticalJPanel();
        constraintsPanel.add(
                this.componentsFactory.getLabel("Result: " + this.payload.getRightCount()+"/"+this.payload.getCurrentTest().getQuestionBlocks().size(),
                        18f,AppThemeColor.SECONDARY_TEXT)
        );
        constraintsPanel.add(
                this.componentsFactory.getLabel("Duration: " + this.payload.getRightCount() +".00",
                        18f,AppThemeColor.SECONDARY_TEXT)
        );
        statusPanel.add(constraintsPanel,BorderLayout.CENTER);
        String status = (this.payload.getCurrentTest().getThreshold() > this.payload.getRightCount())? "Status: NOT PASSED" :
                "Status: PASSED";
        statusPanel.add(this.componentsFactory.getLabel(status,
                24f,AppThemeColor.PRIMARY_TEXT),BorderLayout.PAGE_END);
        root.add(statusPanel,BorderLayout.PAGE_END);
        return root;
    }
    @Override
    protected void onNext() {
        /*NOP*/
    }

    @Override
    protected void onBack() {
        SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.DASHBOARD,null));
    }

}
