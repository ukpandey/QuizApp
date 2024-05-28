package com.example.QuizApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private int id;
    private String rightAnswer;
}
