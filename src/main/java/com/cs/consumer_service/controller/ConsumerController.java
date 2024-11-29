package com.cs.consumer_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.consumer_service.dto.Student;
import com.cs.consumer_service.listener.MessageListener;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jms.Queue;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/consume")
public class ConsumerController {

    @Autowired
    private MessageListener messageListener;

    @Autowired
    private Queue queue;

    @GetMapping("/getProducerMessages")
    public  List<Student> getProducerMessages() {
        List<Student> students=new ArrayList<Student>();
        try {
            ObjectMapper mapper = new ObjectMapper();
			List<String> jsonMessage = (List<String>) messageListener.getMessages();
            for(String singleMsg:jsonMessage){
                students.add(mapper.readValue(singleMsg, Student.class));
            }
			return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getProducerMessagesFromTopic")
    public  List<Student> getProducerMessagesFromTopic() {
        ////List<Student> students=new ArrayList<Student>();
        try {
            //ObjectMapper mapper = new ObjectMapper();
			return messageListener.getMessagesFromTopic();
            } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
