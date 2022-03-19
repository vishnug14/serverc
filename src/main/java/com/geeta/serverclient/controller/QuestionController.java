package com.geeta.serverclient.controller;

import com.geeta.serverclient.model.QuestionModel;
import com.geeta.serverclient.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public String getQuestion(@RequestBody String clientRequest){
        return questionService.randomQuestion().getName();
    }

    @PostMapping
    public ResponseEntity getAnswer(@RequestBody QuestionModel questionModel){
        return questionService.checkAnswer(questionModel.getName(),questionModel.getAnswer());
    }
}
