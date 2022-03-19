package com.geeta.serverclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@Getter
public class QuestionModel {

    
    private String name;
    private String answer;
}
