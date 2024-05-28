package com.example.QuizApp.Service;

import com.example.QuizApp.Dao.QuestionDao;
import com.example.QuizApp.Dao.QuizDao;
import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.QuestionWrapper;
import com.example.QuizApp.Model.Quiz;
import com.example.QuizApp.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        System.out.println(quiz);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();
        for(Question q: questions){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),
                    q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUsers.add(qw);
        }

        return new ResponseEntity<>(questionsForUsers,HttpStatus.OK);
    }

    public ResponseEntity<String> calculteResult(int id, List<Response> responses){
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int score = 0;
        int i = 0;
        for(Response response:responses){
            if(response.getRightAnswer().equals(questions.get(i).getRightAnswer())){
                score++;
            }
            i++;
        }
        return new ResponseEntity<>("Your score is "+ score, HttpStatus.OK);
}
}
