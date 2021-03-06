package com.home.tester.core;


import com.home.tester.core.entity.QuestionBlock;
import com.home.tester.core.entity.ResultBlock;
import rx.subjects.PublishSubject;

public class SubjectsStore {
    public static final PublishSubject<ApplicationReducer<?>> stateSubject = PublishSubject.create();
    public static final PublishSubject<Boolean> packSubject = PublishSubject.create();
    public static final PublishSubject<QuestionBlock> blockChangedSubject = PublishSubject.create();
    public static final PublishSubject<QuestionBlock> blockRemovingSubject = PublishSubject.create();
    public static final PublishSubject<QuestionBlock> blockSelectingSubject = PublishSubject.create();
    public static final PublishSubject<ResultBlock> passedTestsSubject = PublishSubject.create();
}
