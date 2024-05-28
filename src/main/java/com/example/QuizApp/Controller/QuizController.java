package com.example.QuizApp.Controller;

import com.example.QuizApp.Model.QuestionWrapper;
import com.example.QuizApp.Model.Response;
import com.example.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService service;
    @PostMapping("create/category/{category}/numQ/{numQ}/title/{title}")
    public ResponseEntity<String> createQuiz(@PathVariable String category, @PathVariable int numQ, @PathVariable String title){
        return service.createQuiz(category,numQ,title);
    }

    @GetMapping("getQuiz/id/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return service.getQuiz(id);
    }

    @PostMapping("submit/id/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return service.calculteResult(id, responses);
    }
}
