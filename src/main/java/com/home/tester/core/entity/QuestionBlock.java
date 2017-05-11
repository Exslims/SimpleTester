package com.home.tester.core.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@EqualsAndHashCode
public class QuestionBlock {
    private static AtomicInteger idGenerator = new AtomicInteger();
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private List<Answer> answers = new ArrayList<>();

    public QuestionBlock(){
        this.id = idGenerator.incrementAndGet();
    }
    public QuestionBlock(String title, List<Answer> answers){
        this.id = idGenerator.incrementAndGet();
        this.title = title;
        this.answers = answers;
    }
}
