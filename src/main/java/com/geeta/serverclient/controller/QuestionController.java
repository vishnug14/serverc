package com.geeta.serverclient.controller;

import com.geeta.serverclient.entity.Question;
import com.geeta.serverclient.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getQuestion(@RequestParam String clientRequest){
        log.info("Inside get Question method of QuestionController Class");
        return ResponseEntity.ok(questionService.randomQuestion(clientRequest).getName());
    }

    @PostMapping
    public ResponseEntity checkAnswer(@RequestBody Question question){
        log.info("Inside checkAnswer method of QuestionController Class");
        return questionService.checkAnswer(question.getName(),question.getAnswer());
    }
}
