package com.home.tester.ui.manager;


public class PageChainManager {
    private static class PageChainManagerHolder {
        static final PageChainManager HOLDER_INSTANCE = new PageChainManager();
    }
    public static PageChainManager INSTANCE = PageChainManagerHolder.HOLDER_INSTANCE;


}
