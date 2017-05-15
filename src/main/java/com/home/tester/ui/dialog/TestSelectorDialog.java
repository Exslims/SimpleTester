package com.home.tester.ui.dialog;

import com.home.tester.core.entity.TestDescriptor;

import java.awt.*;
import java.util.List;


public class TestSelectorDialog extends BaseDialog<TestDescriptor,List<TestDescriptor>> {
    public TestSelectorDialog(DialogCallback<TestDescriptor> callback, Component relative, List<TestDescriptor> payload) {
        super(callback, relative, payload);
    }

    @Override
    protected void createView() {

    }
}
