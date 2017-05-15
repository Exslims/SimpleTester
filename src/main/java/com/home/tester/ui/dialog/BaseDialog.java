package com.home.tester.ui.dialog;

import com.home.tester.ui.ComponentsFactory;

import javax.swing.*;
import java.awt.*;


public abstract class BaseDialog<T,P> extends JDialog {
    protected DialogCallback<T> callback;
    protected P payload;
    protected ComponentsFactory componentsFactory = new ComponentsFactory();
    public BaseDialog(DialogCallback<T> callback, Component relative, P payload) {
        this.payload = payload;
        this.callback = callback;
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.createView();
        this.pack();
        this.setLocationRelativeTo(relative);
        this.setVisible(true);
    }
    protected abstract void createView();
}
