package com.example.QuizApp.Controller;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.Response;
import com.example.QuizApp.Service.QuestionService;
import com.example.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Questions")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @Autowired
    private QuizService quizService;

    @GetMapping("all")
    public List<Question> getAllQuestion(){
        return service.fetchAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return service.fetchByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        if(question == null){
            return new ResponseEntity<String>("Can't add the question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(service.existsById(question.getId()).isPresent()){
            return new ResponseEntity<>("Question already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        service.saveQuestion(question);
        return new ResponseEntity<>("Question: \n" + question +" \n saved successfully", HttpStatus.OK);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        if(service.existsById(id).isPresent()){
            service.removeQuestionById(id);
            return new ResponseEntity<>("Question with " + id + " deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Question with " + id + " not found...", HttpStatus.NOT_FOUND);
    }
}
