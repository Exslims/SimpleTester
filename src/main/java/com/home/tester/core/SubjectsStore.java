package com.home.tester.core;


import rx.subjects.PublishSubject;

public class SubjectsStore {
    public static final PublishSubject<ApplicationReducer<?>> stateSubject = PublishSubject.create();
}
