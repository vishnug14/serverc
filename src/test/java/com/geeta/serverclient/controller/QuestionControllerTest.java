package com.geeta.serverclient.controller;

import com.geeta.serverclient.entity.Question;
import com.geeta.serverclient.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    private Question question;
    private Question wrongAnswer;

    @BeforeEach
    void setUp() {

        question = Question.builder().name("Please sum the numbers 3,5,6.").answer("14").build();


    }

    @Test
    void getQuestion() throws Exception {
        String clientRequest = "Get a me question";
        //question = Question.builder().name("Please sum the numbers 3,5,6.").answer("14").build();
        Mockito.when(questionService.randomQuestion("sdsd")).thenReturn(question);

        mockMvc.perform(get("/?clientRequest=sdsd"))
                .andExpect(status().isOk()).andExpect(content()
                .contentType("application/json"))
                .andExpect(content().string(question.getName()));

    }

    @Test
    void whenAnswerisCorrect_ThenReturnStatusBad() throws Exception {
        Mockito.when(questionService.checkAnswer(question.getName(),question.getAnswer()))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\":\"Please sum the numbers 3,5,6.\",\n" +
                "    \"answer\":\"14\"\n" +
                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void whenAnswerisWrong_ThenReturnStatusBad() throws Exception {
        wrongAnswer = Question.builder().name("Please sum the numbers 3,5,6.").answer("15").build();

        Mockito.when(questionService.checkAnswer(wrongAnswer.getName(),wrongAnswer.getAnswer()))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\":\"Please sum the numbers 3,5,6.\",\n" +
                "    \"answer\":\"15\"\n" +
                "}"))
                .andExpect(status().is4xxClientError());
    }
}