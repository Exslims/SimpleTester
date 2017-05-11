package com.home.tester.ui.panels.additional;

import com.home.tester.core.AsSubscriber;
import com.home.tester.core.SubjectsStore;
import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.ui.AppThemeColor;
import com.home.tester.ui.ComponentsFactory;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class QuestionBlockPanel extends BaseJPanel implements AsSubscriber {
    @Getter
    private QuestionBlock block;
    public QuestionBlockPanel(QuestionBlock block) {
        super();
        this.block = block;
        this.setBackground(AppThemeColor.BACKGROUND);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0,2,2,2),
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR)));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(0,2,2,2),
                        BorderFactory.createLineBorder(AppThemeColor.PRIMARY_COLOR)));
            }
        });
        this.subscribe();
        this.createView();
    }

    protected void createView(){
        JPanel miscPanel = this.componentsFactory.getJPanel(new BorderLayout());

        miscPanel.add(this.componentsFactory.getLabel(this.block.getTitle()),BorderLayout.CENTER);
        JButton removeButton = this.componentsFactory.getIconButton("app/remove.png", 20, "");
        removeButton.addActionListener(action ->
                SubjectsStore.blockRemovingSubject.onNext(this.block));
        miscPanel.add(removeButton,BorderLayout.LINE_END);

        JPanel answersPanel = this.componentsFactory.getVerticalJPanel();
        this.block.getAnswers().forEach(answer -> {
            JPanel panel = this.componentsFactory.getJPanel(new BorderLayout());

            String iconPath = (answer.isRight())?"app/checked.png" : "app/unchecked.png";

            panel.add(this.componentsFactory.getIconLabel(iconPath,20),BorderLayout.LINE_START);
            panel.add(this.componentsFactory.getLabel(answer.getText(),15f,AppThemeColor.SECONDARY_TEXT),BorderLayout.CENTER);
            answersPanel.add(panel);
        });
        this.add(miscPanel,BorderLayout.PAGE_START);
        this.add(answersPanel,BorderLayout.CENTER);
    }

    @Override
    public void subscribe() {
        SubjectsStore.blockChangedSubject.subscribe(block -> {
            if(this.block.equals(block)){
                this.removeAll();
                this.createView();
                SubjectsStore.packSubject.onNext(true);
            }
        });
    }
}
