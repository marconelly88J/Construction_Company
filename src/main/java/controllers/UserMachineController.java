package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

import model.Machine;


@WebServlet("/UserMachineController")
public class UserMachineController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public UserMachineController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		DAO dao = new DAO();
		String action = request.getParameter("action");
		String error = "";

		if(action != null) {
			switch (action) {

			case "listAllMachines":

				try {
					ArrayList<Machine> machines = dao.selectAllMachines();
					request.setAttribute("list_all_machines", machines);
					request.getRequestDispatcher("list-machines.jsp").forward(request, response);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					error = "File not found: "+e.getMessage();
					request.setAttribute("error", error);
					request.getRequestDispatcher("index_user.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
					error = "error: "+e.getMessage();
					request.setAttribute("error", error);
					request.getRequestDispatcher("index_user.jsp").forward(request, response);
				}
				break;

			default:
				break;
			}
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
