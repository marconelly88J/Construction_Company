package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import model.User;
import validator.Validator;


@WebServlet("/addNewWorker")
public class AddNewWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNewWorker() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		DAO dao = new DAO();
		String action = request.getParameter("action");
		String workerName = request.getParameter("workerName");
		String errorMsg = "";
		String successMsg = "";

		if (action != null) {
			if(workerName != null && !workerName.trim().isEmpty()) {
				//proveriti da li 'workerName' vec postoji u bazi
				if( Validator.userExists(workerName)) {
					errorMsg += "Worker already in database";
					request.setAttribute("errorMsg", errorMsg);
					request.getRequestDispatcher("add-worker.jsp").forward(request, response);
				}else if (workerName.toLowerCase().contains("admin")){
					errorMsg += "Word 'admin' is reserved! ";
					request.setAttribute("errorMsg", errorMsg);
					request.getRequestDispatcher("add-worker.jsp").forward(request, response);
				}else {
					dao.insertWorker(workerName);
					successMsg += "Worker added to database.";
					request.setAttribute("successMsg", successMsg);
					request.getRequestDispatcher("add-worker.jsp").forward(request, response);
				}
			}else {
				errorMsg += "Must enter name!";
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("add-worker.jsp").forward(request, response);
			}
		}

	}

}
