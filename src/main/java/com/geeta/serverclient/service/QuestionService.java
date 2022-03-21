package com.geeta.serverclient.service;


import com.geeta.serverclient.entity.Question;
import com.geeta.serverclient.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    public Question randomQuestion(String clientRequest) {
            Random rand = new Random();
            int number1, number2, number3;
            String ans;
            String generatedQuestion;
            Question que = null;
            do {
                number1 = rand.nextInt(20);
                number2 = rand.nextInt(20);
                number3 = rand.nextInt(20);
                generatedQuestion = "Please sum the three number " + number3 + ", " + number1 + ", " + number2 + ".";
                ans = String.valueOf(number1 + number2 + number3);
                que = new Question(generatedQuestion, ans);
            } while (questionRepository.findById(ans).isPresent());
            questionRepository.save(que);
            return que;

    }

    public ResponseEntity checkAnswer(String name, String answer) {
        Optional<Question> q = questionRepository.findById(name);
        if(!q.isPresent() || !(q.get().getAnswer().equals(answer))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
