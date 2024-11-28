package com.cs.consumer_service.listener;

import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.cs.consumer_service.dto.Student;
@Component
public class MessageListener {

    private final List<String> messages=new ArrayList<>();

    @JmsListener(destination = "${spring.activemq.queue.name}")
    public void receiveMessages(String message) {
        messages.add(message);
        System.out.println("Received message: " + message);
    }

    @JmsListener(destination = "${spring.activemq.topic}",containerFactory = "jmsListenerContainerFactory")
    public void receiveMessagesFromTopic(Student student){
        System.out.println("Received Messages from topic: "+student);
    }
    public List<String> getMessages(){
        return new ArrayList<>(messages);
    }

    public void clearMessages(){
        messages.clear();
    }
}