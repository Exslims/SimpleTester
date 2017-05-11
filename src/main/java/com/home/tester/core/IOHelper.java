package com.home.tester.core;


import com.home.tester.core.entity.TestDescriptor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class IOHelper {
    @Getter
    private List<TestDescriptor> loadedTests = new ArrayList<>();
    public IOHelper(){

    }
    public void saveTest(TestDescriptor descriptor){

    }
}
