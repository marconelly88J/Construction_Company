package controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DAO;
import model.Machine;

@WebServlet("/addNewMachine")
public class AddNewMachine extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AddNewMachine() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = new DAO();

		String machineType = request.getParameter("machineType");
		String stock = request.getParameter("stock");
		String img_path = request.getParameter("img_path");

		String errorMsg = "";
		
		String prefix = "img/";
		String sufix = ".jpg";
		String machine_cover_img = prefix+img_path+sufix;

		if (machineType != null && machineType.length() > 0 && 
			stock != null && stock.length() > 0 && img_path != null && img_path.length() > 0 ) {
			try {
				
				int machine_stock = Integer.parseInt(stock);
				Machine m = new Machine(machineType, machine_stock, machine_cover_img);
				dao.insertMachine(m);

				String successMsg = "New machine added successfully!";
				request.setAttribute("successMsg", successMsg);
				request.getRequestDispatcher("add-machine.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				errorMsg += "Some fields must be numbers!";
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("add-machine.jsp").forward(request, response);
			} 
		} else {
			errorMsg += "Must input all fields!<br>";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("add-machine.jsp").forward(request, response);
		} 
	}
}
