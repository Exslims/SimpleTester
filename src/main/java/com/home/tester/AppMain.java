package com.home.tester;


import com.home.tester.ui.MainFrame;
import com.home.tester.ui.manager.PageChainManager;

import java.awt.*;

public class AppMain {
    public static void main(String[] args) {
        new PageChainManager().start();
    }
}
