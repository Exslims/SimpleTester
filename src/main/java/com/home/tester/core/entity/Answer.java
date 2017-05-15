package com.home.tester.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Answer {
    private String text;
    private boolean right;
    private boolean mask;

    public Answer(String text, boolean right){
        this.text = text;
        this.right = right;
    }
}
