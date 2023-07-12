package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import model.Machine;


@WebServlet("/MachineService")
public class MachineService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MachineService() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DAO dao = new DAO();
		String action = request.getParameter("action");
		String errorMsg = "";

		if(action != null) {

			switch (action) {
			case "deleteMachine":

				try {
					dao.deleteMachineById(Integer.parseInt(request.getParameter("id")));

					ArrayList<Machine> machines = new ArrayList<>();
					machines = dao.selectAllMachines();

					request.setAttribute("list_all_machines", machines);
					request.getRequestDispatcher("machine-service.jsp").forward(request, response);

				} catch (FileNotFoundException e) {
					errorMsg += "Machine not found: "+e.getMessage();
					request.setAttribute("errorMsg", errorMsg);
					request.getRequestDispatcher("index_admin.jsp").forward(request, response);
				} catch (IOException e) {
					errorMsg += "error: "+e.getMessage();
					request.setAttribute("errorMsg", errorMsg);
					request.getRequestDispatcher("index_admin.jsp").forward(request, response);
				}
				request.getRequestDispatcher("machine-service.jsp").forward(request, response);
				break;

			default:
				break;
			}

		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = new DAO();
		String action = request.getParameter("action");
		String machineID = request.getParameter("id");
		String newStock = request.getParameter("newStock");
		String errorMsg = "";
		

		if (action != null) {
			switch (action) {
			case "updateStock":
				try {
					int stock = Integer.parseInt(newStock);
					int machine_id = Integer.parseInt(machineID);
					
					
					if (stock >= 0) {
						
						dao.updateMachineStock(machine_id, stock);
					} else {
						errorMsg = "Stock can't be negative!";
					}
				} catch (NumberFormatException e) {
					errorMsg = "Invalid input!";
				} catch (Exception e) {
					e.printStackTrace();
					errorMsg = e.getMessage();
				}

				ArrayList<Machine> machines = dao.selectAllMachines();
				request.setAttribute("list_all_machines", machines);
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("machine-service.jsp").forward(request, response);
				break;

			default:
				break;
			}
		}

	}

}
