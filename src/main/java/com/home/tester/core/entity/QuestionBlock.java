package com.home.tester.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionBlock {
    private String title;
    private List<Answer> answers;
}
