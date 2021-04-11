package com.tm.model;

public class Question {
	private Integer id;
	private String question;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(String question) {
		super();
		this.question = question;
	}

	public Question(Integer id, String question) {
		super();
		this.id = id;
		this.question = question;
	}

}
