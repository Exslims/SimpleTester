package com.home.tester.ui.panels.additional;


import com.home.tester.core.ApplicationReducer;
import com.home.tester.core.ApplicationState;
import com.home.tester.core.SubjectsStore;
import com.home.tester.core.entity.ResultBlock;
import com.home.tester.ui.AppThemeColor;

import javax.swing.*;
import java.awt.*;

public class ResultBlockPanel extends BaseJPanel {
    private ResultBlock resultBlock;
    public ResultBlockPanel(ResultBlock resultBlock) {
        this.resultBlock = resultBlock;
        this.setPreferredSize(new Dimension(200,80));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(4,4,4,4)
        ));
        this.createView();
    }

    @Override
    protected void createView() {
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        JPanel headerPanel = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(this.componentsFactory.getLabel(
                this.resultBlock.getCurrentTest().getTitle(),14, AppThemeColor.PRIMARY_TEXT), BorderLayout.PAGE_START);
        root.add(headerPanel,BorderLayout.PAGE_START);
        JPanel miscPanel = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.LEFT));

        miscPanel.add(
                this.componentsFactory.getLabel(this.resultBlock.getRightCount()+"/"+this.resultBlock.getCurrentTest().getQuestionBlocks().size(),
                        12f,AppThemeColor.SECONDARY_TEXT)
        );
        miscPanel.add(
                this.componentsFactory.getLabel(this.resultBlock.getRightCount() +".00",
                        12f,AppThemeColor.SECONDARY_TEXT)
        );
        root.add(miscPanel,BorderLayout.CENTER);
        this.add(root,BorderLayout.LINE_START);
        this.add(this.componentsFactory.getCircleProgressBar(this.resultBlock.getPercent(),13f), BorderLayout.CENTER);

        JPanel statusPanel = this.componentsFactory.getGridJPanel(1,2);
        JButton restartButton = this.componentsFactory.getIconButton("app/restart.png", 26, "");
        restartButton.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.TEST_AREA,this.resultBlock.getCurrentTest()));
        });
        String statusIconPath = (this.resultBlock.getCurrentTest().getThreshold() > this.resultBlock.getRightCount())? "app/wrong_answer.png" :
                "app/checked.png";
        statusPanel.add(restartButton);
        statusPanel.add(this.componentsFactory.getIconLabel(statusIconPath, 26));
        this.add(statusPanel,BorderLayout.LINE_END);
    }
}
