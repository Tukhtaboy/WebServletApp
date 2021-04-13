package com.tm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tm.model.Question;

public class QuestionDao {
	private final String jdbcUserName = "root";
	private final String jdbcPassword = "Mavluda99";
	private final String jdbcUrl = "jdbc:mysql://localhost:3306/webappdb";
	private final DataSourceFactory dataSourceFactory;

	public QuestionDao() throws SQLException {
		dataSourceFactory = new DataSourceFactory(jdbcUrl, jdbcUserName, jdbcPassword);
	}

	public boolean addQuestion(Question qusetion) throws SQLException {
		String sql = "insert into questions(qusetion) value(?)";
		PreparedStatement statement = dataSourceFactory.getJdbcConnection().prepareStatement(sql);
		statement.setString(1, qusetion.getQuestion());
		return statement.execute();
	}

	public List<Question> getAllQuestions() throws SQLException {
		String sql = "select id,question from questions";
		Statement statement = dataSourceFactory.getJdbcConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Question> list = new ArrayList<>();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String ques = resultSet.getString("question");
			Question question = new Question(id, ques);
			list.add(question);
		}

		resultSet.close();
		statement.close();
		return list;
	}
	public Question getQuestionById(int id) throws SQLException {
		String sql = "select id,question from questions where id=?";
		PreparedStatement statement = dataSourceFactory.getJdbcConnection().prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		Question q = new Question(rs.getInt("id"),rs.getString("question"));
		return q;
	}

	public void update(Question questionObj) throws SQLException {
		String sql = "update questions set question=? where id=?";
		PreparedStatement statement = dataSourceFactory.getJdbcConnection().prepareStatement(sql);
		statement.setInt(1, questionObj.getId());
		statement.setString(1, questionObj.getQuestion());
		statement.executeUpdate();
		
	}
	
	public void delete(int id) throws SQLException {
		String sql = "delete from questions where id=?";
		PreparedStatement statement = dataSourceFactory.getJdbcConnection().prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
		
	}
	
}
