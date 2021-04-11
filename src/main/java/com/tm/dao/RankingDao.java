package com.tm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tm.model.Question;
import com.tm.model.Ranking;

public class RankingDao {
	private final String jdbcUserName = "root";
	private final String jdbcPassword = "Mavluda99";
	private final String jdbcUrl = "jdbc:mysql://localhost:3306/webappdb";
	private final DataSourceFactory dataSourceFactory;

	public RankingDao() throws SQLException {
		dataSourceFactory = new DataSourceFactory(jdbcUrl, jdbcUserName, jdbcPassword);
	}

	public boolean addRank(Ranking ranking) throws SQLException {
		String sql = "insert into ranking(question_id,rank) value(?,?)";
		PreparedStatement statement = dataSourceFactory.getJdbcConnection().prepareStatement(sql);
		statement.setInt(1, ranking.getQuestionId());
		statement.setInt(2, ranking.getRank());
		return statement.execute();
	}

	public List<Ranking> getAllRanks() throws SQLException {
		String sql = "select r.id,r.qusetion_id,r.rank,q.question from ranking r left join question q on q.id=r.question_id";
		Statement statement = dataSourceFactory.getJdbcConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Ranking> list = new ArrayList();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int questionId = resultSet.getInt("question_id");
			int rank = resultSet.getInt("rank");
			String question = resultSet.getString("question");
			Question question2 = new Question(questionId, question);
			Ranking r = new Ranking(id, questionId, question2);
			list.add(r);
		}
		return list;
	}

	public List<Ranking> getRankByQuestionId(int q_id) throws SQLException {
		String sql = "select r.id,r.qusetion_id,r.rank,q.question from ranking r left join question q on q.id=r.question_id where r.question_id=?";
		PreparedStatement statement = dataSourceFactory.getJdbcConnection().prepareStatement(sql);
		statement.setInt(1, q_id);
		ResultSet resultSet = statement.executeQuery(sql);
		List<Ranking> list = new ArrayList();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int questionId = resultSet.getInt("question_id");
			int rank = resultSet.getInt("rank");
			String question = resultSet.getString("question");
			Question question2 = new Question(questionId, question);
			Ranking r = new Ranking(id, questionId, question2);
			list.add(r);
		}
		return list;
	}
}
