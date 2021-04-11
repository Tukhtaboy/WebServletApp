package com.tm.model;

public class Ranking {
	private int id;
	private int questionId;
	private int rank;
	private Question question;

	public Ranking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ranking(int questionId, int rank) {
		super();
		this.questionId = questionId;
		this.rank = rank;
	}

	public Ranking(int questionId, int rank, Question question) {
		super();
		this.questionId = questionId;
		this.rank = rank;
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
