package com.home.tester.core.entity;

import lombok.Data;

@Data
public class ResultBlock {
    private TestDescriptor currentTest;
    private int percent;
}
