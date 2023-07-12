package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.DAO;
import dto_resource.DTO_RentedMachines;
import model.Machine;
import model.User;


@WebServlet("/UserRentController")
public class UserRentController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public UserRentController() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		DAO dao = new DAO();

		/////////
		try {
			User user =   (User) request.getSession().getAttribute("user_session");
			ArrayList<DTO_RentedMachines> rented_machines = dao.getRentedMachinesById(user.getId());

			request.setAttribute("rented_machines", rented_machines);
			request.getRequestDispatcher("user_rented-machines.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		DAO dao = new DAO();
		String action = request.getParameter("action");
		String units = request.getParameter("units");
		String workerId = request.getParameter("workerId");
		String machineId = request.getParameter("machineId");
		String machineStock = request.getParameter("machineStock");
		String rentTime = request.getParameter("rentTime");
		
		String rentMsg = "";

		if(action != null) {
			switch (action) {

			case "rentMachine":
				
			    if ( units != null && !units.trim().isEmpty() ) {
			        try {
			            int units_rented = Integer.parseInt(units);
			            int user_id = Integer.parseInt(workerId);
			            int machine_id = Integer.parseInt(machineId);
			            int machine_stock = Integer.parseInt(machineStock);
			            int machine_new_stock = machine_stock - units_rented;
			            
			            //System.out.println("units to rent: "+units_rented+" ");
			            //System.out.println("machine stock before renting: "+machine_stock);
			            //System.out.println("machine stock after rent: "+machine_new_stock);

			            if (machine_new_stock >= 0) {
			                dao.insertRentedMachine(user_id, machine_id, units_rented, rentTime);
			                dao.updateMachineStock(machine_id, machine_new_stock);

			                ArrayList<Machine> machines = dao.selectAllMachines();
			                request.setAttribute("list_all_machines", machines);
			                
			                rentMsg = "Machine is rented";
			                request.setAttribute("rentMsg", rentMsg);
			                
			                request.getRequestDispatcher("list-machines.jsp").forward(request, response);
			            } else {
			                rentMsg = "Out of stock!";
			                request.setAttribute("rentMsg", rentMsg);
			                request.getRequestDispatcher("list-machines.jsp").forward(request, response);
			            }
			        } catch (Exception e) {
			            rentMsg = "Problem with renting machine";
			            request.setAttribute("rentMsg", rentMsg);
			            request.getRequestDispatcher("list-machines.jsp").forward(request, response);
			            e.printStackTrace();
			        }
			    } else {
			        rentMsg = "Enter number of units";
			        request.setAttribute("rentMsg", rentMsg);
	                request.getRequestDispatcher("list-machines.jsp").forward(request, response);
			    }
			    
			    break;
	////////////////////////////////////////////////////////////////////////////////////	
			case "finishRent":
				
				try {

					int user_id = Integer.parseInt(workerId);
					int machine_id = Integer.parseInt(machineId);
					
					int machine_stock = dao.getMachineStockById(machine_id);
					int units_rented = dao.getUnitsRentedById(user_id, machine_id);
					
					//System.out.println("rented units: "+units_rented);
					
					dao.finishRenting(rentTime, user_id, machine_id); // upisujem datum kraja rentiranja
					int machineNewStock = units_rented + machine_stock;// stock za update u 'machines'
					
					//System.out.println("new stock for machine id:"+machine_id+" = "+machineNewStock);
					
					dao.updateMachineStock(machine_id, machineNewStock);

					User user =   (User) request.getSession().getAttribute("user_session");
					ArrayList<DTO_RentedMachines> rented_machines = dao.getRentedMachinesById(user.getId());

					request.setAttribute("rented_machines", rented_machines);
					request.getRequestDispatcher("user_rented-machines.jsp").forward(request, response);

				} catch (Exception e) {
					rentMsg = "Problem with renting machine";
					e.printStackTrace();
				}
				break;
				/////////////////////////////////////////////////////////////////////////
				
			case "deleteHistory":
				
				try {
					
					int worker_id = Integer.parseInt(workerId);
					int machine_id = Integer.parseInt(machineId);
					
					dao.deleteHistory(worker_id, machine_id);
					User user =   (User) request.getSession().getAttribute("user_session");
					ArrayList<DTO_RentedMachines> rented_machines = dao.getRentedMachinesById(user.getId());

					request.setAttribute("rented_machines", rented_machines);
					request.getRequestDispatcher("user_rented-machines.jsp").forward(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			///////////////////////////////////////////////////////////////	
			default:
				break;
			}
		}


	}

}
