package com.cs.consumer_service.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class Student implements Serializable{
    private String studentId;
	private String name;
	private String rollNumber;
}
