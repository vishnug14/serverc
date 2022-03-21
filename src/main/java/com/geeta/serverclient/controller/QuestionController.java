package com.geeta.serverclient.controller;

import com.geeta.serverclient.entity.Question;
import com.geeta.serverclient.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getQuestion(@RequestParam String clientRequest){
        return ResponseEntity.ok(questionService.randomQuestion(clientRequest).getName());
    }

    @PostMapping
    public ResponseEntity checkAnswer(@RequestBody Question question){
        return questionService.checkAnswer(question.getName(),question.getAnswer());
    }
}
