package com.tm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tm.dao.QuestionDao;
import com.tm.model.Question;

/**
 * Servlet implementation class MainServelet
 */
@WebServlet("/MainServelet")
public class MainServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDao dao;

	/**
	 * @throws SQLException
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServelet() throws SQLException {
		super();
		dao = new QuestionDao();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertQuestion(request, response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateQuestion(request, response);
				break;
			default:
				listQuestion(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void listQuestion(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Question> listQuestion = dao.getAllQuestions();
		request.setAttribute("listQuestion", listQuestion);
		RequestDispatcher dispatcher = request.getRequestDispatcher("questionList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("QuestionForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Question existingQuestion = dao.getQuestionById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editform.jsp");
		request.setAttribute("question", existingQuestion);
		dispatcher.forward(request, response);

	}

	private void insertQuestion(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String question = request.getParameter("question");

		Question qusetionObj = new Question(question);
		dao.addQuestion(qusetionObj);
		response.sendRedirect("list");
	}

	private void updateQuestion(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String question = request.getParameter("question");

		Question questionObj = new Question(id, question);
		dao.update(questionObj);
		response.sendRedirect("list");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		response.sendRedirect("list");

	}

}
