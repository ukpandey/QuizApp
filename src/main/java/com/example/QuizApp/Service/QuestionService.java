package com.example.QuizApp.Service;

import com.example.QuizApp.Dao.QuestionDao;
import com.example.QuizApp.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao dao;

    public List<Question> fetchAllQuestions() {
        return dao.findAll();
    }
    public List<Question> fetchByCategory(String category) {
        return dao.findBycategory(category);
    }

    public Question saveQuestion(Question question){
        return dao.save(question);
    }

    public Optional<Question> existsById(int id) {
        return dao.findById(id);
    }

    public void removeQuestionById(int id){
        dao.deleteById(id);
    }
}
