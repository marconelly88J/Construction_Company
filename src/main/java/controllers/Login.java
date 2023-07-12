package controllers;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DAO;
import model.Admin;
import model.Machine;
import model.User;
import validator.Validator;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Login() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// terminate user session and redirect to login page
		//String action = request.getParameter("action");

		//if(action != null) {
		//if(action.equals("logout")) {
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    DAO dao = new DAO();
	    String action = request.getParameter("action");
	    String username = request.getParameter("username");
	    String msg = "";

	    if (action != null) {

	        if (username == null || username.trim().isEmpty()) {
	        	
	            msg = "Must enter a name";
	            request.setAttribute("msg", msg);
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	            
	        } else if (Validator.isAdmin(username)) {
	        	
	            Admin admin = new Admin(username);
	            request.getSession().setAttribute("admin_session", admin);
	            response.sendRedirect("index_admin.jsp");
	            
	        } else if (Validator.userExists(username)) {
	        	
	            try {
	                int userId = dao.getUserIdByUsername(username);
	                User user = new User(userId, username);
	                request.getSession().setAttribute("user_session", user);
	                response.sendRedirect("index_user.jsp");
	                
	            } catch (Exception e) {
	            	
	                e.printStackTrace();
	                msg = "An error occurred. Please try again.";
	                request.setAttribute("msg", msg);
	                request.getRequestDispatcher("login.jsp").forward(request, response);
	                
	            }
	            
	        } else if (username.toLowerCase().contains("admin")) {
	        	
	            msg = "Word 'admin' is reserved!";
	            request.setAttribute("msg", msg);
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	            
	        } else {
	        	
	            msg = "No user with that username";
	            request.setAttribute("msg", msg);
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	            
	        }
	    }
	}






}
