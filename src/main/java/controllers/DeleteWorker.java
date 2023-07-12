package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import model.User;


@WebServlet("/DeleteWorker")
public class DeleteWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DeleteWorker() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DAO dao = new DAO();
		String action = request.getParameter("action");
		//String id = request.getParameter("id");
		String msg = "";
		
		if(action != null) {
			if(action.equals("deleteWorkerById")) {
				dao.deleteWorkerById(Integer.parseInt(request.getParameter("id")));
				
				ArrayList<User> list_all_workers = dao.selectAllWorkers();
				request.setAttribute("list_all_workers", list_all_workers);
				request.getRequestDispatcher("list-workers.jsp").forward(request, response);
			}
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
