package com.home.tester.ui.panels;

import com.home.tester.core.ApplicationReducer;
import com.home.tester.core.ApplicationState;
import com.home.tester.core.SubjectsStore;
import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.core.entity.TestDescriptor;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.PageJPanel;
import com.home.tester.ui.dialog.AlertDialog;
import com.home.tester.ui.panels.additional.UIUtils;
import com.home.tester.ui.panels.additional.process.QuestionBlockPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class TestAreaPanel extends PageJPanel<TestDescriptor> {
    private int currentIndex;
    private QuestionBlock currentQuestion;
    private List<QuestionBlock> questions;
    @Override
    protected void init() {
        if(this.payload != null){
            this.currentIndex = 0;
            this.questions = new ArrayList<>(this.payload.getQuestionBlocks());
            if(this.payload.isRandomizeQuestions()) {
                Collections.shuffle(this.questions);
            }
            this.initForm();
        }
    }
    private void initForm(){
        this.removeAll();
        this.createNavigationBar();

        JPanel statusPanel = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.CENTER));
        statusPanel.setBackground(AppThemeColor.BACKGROUND_DARK);
        statusPanel.add(this.componentsFactory.getLabel(currentIndex + 1 + "/" + this.questions.size(),20,AppThemeColor.PRIMARY_TEXT));
        this.navBar.add(statusPanel,BorderLayout.CENTER);

        this.currentQuestion = this.questions.get(this.currentIndex);
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(40,80,80,80));
        root.setBackground(AppThemeColor.BACKGROUND_DARK);
        root.add(UIUtils.wrapToSlide(new QuestionBlockPanel(this.currentQuestion)));
        this.add(root,BorderLayout.CENTER);
    }

    @Override
    protected void onNext() {
        if(this.currentIndex + 1 == this.questions.size()){
            new AlertDialog(value -> {
                if(value){

                    SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.DASHBOARD, null));
                }
            }, "Do you want to continue?",this);
        }else {
            currentIndex++;
            this.initForm();
            SubjectsStore.packSubject.onNext(true);
        }
    }

    @Override
    protected void onBack() {
        SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.DASHBOARD,null));
    }
}
