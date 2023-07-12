package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto_resource.DTO_RentedMachines;
import model.Admin;

import model.Machine;
import model.User;


import java.util.ArrayList;



public class DAO {
      private DataSource ds;

	private static String SELECT_ALL_MACHINES = "SELECT * FROM machines";
	private static String INSERT_MACHINE = "INSERT INTO machines (`machine_type`, `stock`, `img_path`)"
															+ " VALUES (?, ?, ?)";
	private static String UPDATE_MACHINE_STOCK = "UPDATE machines SET stock = ? WHERE machine_id = ?";
	private static String DELETE_MACHINE_BY_ID = "DELETE FROM machines WHERE machine_id = ?";
	private static String RENTED_MACHINES = "SELECT * FROM `rented_machines` WHERE `worker_id` = ?";
	private static String GET_UNITS_BY_ID = "SELECT units FROM `rented_machines` WHERE worker_id = ? AND machine_id = ?";
	private static String GET_MACHINE_STOCK_BY_ID = "SELECT `stock` FROM `machines` WHERE `machine_id` = ?";
			
			
	private static String SELECT_ALL_WORKERS = "SELECT * FROM workers";
	private static String SELECT_ID_BY_USERNAME = "SELECT `worker_id` FROM `workers` WHERE `worker_username` = ?";
	private static String INSERT_WORKER = "INSERT INTO workers (`worker_username`) VALUES (?)";
	private static String DELETE_WORKER_BY_ID = "DELETE FROM workers WHERE worker_id = ?";
	private static String CHECK_WORKER = "SELECT * FROM workers WHERE worker_username = ?";
	
	private static String CHECK_ADMIN = "SELECT * FROM admins WHERE admin_username = ?";
	
	private static String FINISH_RENT = "UPDATE rented_machines SET date_rent_end = ? WHERE worker_id = ? AND machine_id = ?";
	private static String RENT_MACHINE = "INSERT INTO rented_machines (`worker_id`, `machine_id`, `units`, `date_of_rent`, `date_rent_end`) "
										+ "VALUES (?, ?, ?, ?, NULL);";
	private static String DELETE_HISTORY = "DELETE FROM `rented_machines` WHERE `worker_id`= ? AND `machine_id` = ?";
	
	public DAO(){
		
	try {
		InitialContext cxt = new InitialContext();
		if ( cxt == null ) { 
		} 
		ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
		if ( ds == null ) { 
		} 		
		} catch (NamingException e) {
		}
	}
	
	public ArrayList<Machine> selectAllMachines(){
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<Machine> listOfMachines = new ArrayList<>();
		Machine machine = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_ALL_MACHINES);
		
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
				
				machine = new Machine();
				
				machine.setMachine_id(rs.getInt("machine_id"));
				machine.setMachine_type(rs.getString("machine_type"));
				machine.setStock(rs.getInt("stock"));
				machine.setImg_path(rs.getString("img_path"));
				
				listOfMachines.add(machine);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfMachines; 
	}
	/////////////////////////////////////////////////////////
	
public ArrayList<User> selectAllWorkers(){
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<User> listOfUsers = new ArrayList<>();
		User user = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_ALL_WORKERS);
		
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
				
				user = new User();
				
				user.setId(rs.getInt("worker_id"));
				user.setUsername(rs.getString("worker_username"));
				
				listOfUsers.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfUsers; 
	}
	
////////////////////////////////////////////////////////////
	
public ArrayList<DTO_RentedMachines> getRentedMachinesById(int id){
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<DTO_RentedMachines> rented_machines_list = new ArrayList<>();
		DTO_RentedMachines rented_machine = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(RENTED_MACHINES);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
				rented_machine = new DTO_RentedMachines();
				
				rented_machine.setWorker_id(rs.getInt("worker_id"));
				rented_machine.setMachine_id(rs.getInt("machine_id"));
				rented_machine.setUnits(rs.getInt("units"));
				rented_machine.setRent_start(rs.getString("date_of_rent"));
				rented_machine.setRent_end(rs.getString("date_rent_end"));
				
				rented_machines_list.add(rented_machine);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rented_machines_list;
	}
///////////////////////////////////////////////////////////

public void updateMachineStock(int id, int newStock){
	
	Connection con = null;
    PreparedStatement pstm = null;
    
    try {
    	
        con = ds.getConnection();
        pstm = con.prepareStatement(UPDATE_MACHINE_STOCK);
        pstm.setInt(1, newStock);
        pstm.setInt(2, id);
        
		pstm.execute();
		
	} catch (SQLException e) {
		e.printStackTrace();
		e.getMessage();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
		e.getMessage();
	}
	
}

//////////////////////////////////////////////////////////

public void finishRenting(String date, int workerId, int machineId){
	
	Connection con = null;
    PreparedStatement pstm = null;
    
    try {
    	
        con = ds.getConnection();
        pstm = con.prepareStatement(FINISH_RENT);
        pstm.setString(1, date);
        pstm.setInt(2, workerId);
        pstm.setInt(3, machineId);
        
		pstm.execute();
		
	} catch (SQLException e) {
		e.printStackTrace();
		e.getMessage();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
		e.getMessage();
	}
	
}
//////////////////////////////////////////////////////////

public void deleteHistory(int workerId, int machineId) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(DELETE_HISTORY);
		pstm.setInt(1, workerId);
		pstm.setInt(2, machineId);
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

//////////////////////////////////////////////////////////
public void insertMachine(Machine m) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(INSERT_MACHINE);
		
		pstm.setString(1, m.getMachine_type());
		pstm.setInt(2, m.getStock());
		pstm.setString(3, m.getImg_path());
		
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
///////////////////////////////////////////////////////////

public void insertRentedMachine(int worker_id, int machine_id, int units_rented, String date) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(RENT_MACHINE);
		
		pstm.setInt(1, worker_id);
		pstm.setInt(2, machine_id);
		pstm.setInt(3, units_rented);
		pstm.setString(4, date);
		
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

///////////////////////////////////////////////////////////

public void deleteMachineById(int id) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(DELETE_MACHINE_BY_ID);
		pstm.setInt(1, id);
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

/////////////////////////////////////////////////////////////
	
public void insertWorker(String username) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(INSERT_WORKER);
		
		pstm.setString(1, username);
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
////////////////////////////////////////////////////

public void deleteWorkerById(int id) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(DELETE_WORKER_BY_ID);
		pstm.setInt(1, id);
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

/////////////////////////////////////////////////////

public User selectWorker(String username) {
	
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	User user = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(CHECK_WORKER);

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		pstm.setString(1, username);
		pstm.execute();
		rs = pstm.getResultSet();
		
		if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("worker_id"));
            user.setUsername(rs.getString("worker_username"));
        }

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return user;
}
////////////////////////////////////////////////////////

public int getUserIdByUsername(String username) {
	
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	User user = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(SELECT_ID_BY_USERNAME);

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		pstm.setString(1, username);
		pstm.execute();
		rs = pstm.getResultSet();
		
		if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("worker_id"));
            
        }

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return user.getId();
}
///////////////////////////////////////////////////////////////////

public int getUnitsRentedById(int workerId, int machineId) {
	
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	int units = 0;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(GET_UNITS_BY_ID);

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		pstm.setInt(1, workerId);
		pstm.setInt(2, machineId);
		pstm.execute();
		rs = pstm.getResultSet();
		
		if (rs.next()) {
			units = rs.getInt("units");
        }

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return units;
}
//////////////////////////////////////////////////////////////////

public int getMachineStockById(int machineId) {
	
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	int stock = 0;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(GET_MACHINE_STOCK_BY_ID);

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		
		pstm.setInt(1, machineId);
		pstm.execute();
		rs = pstm.getResultSet();
		
		if (rs.next()) {
			stock = rs.getInt("stock");
        }

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return stock;
}

///////////////////////////////////////////////////////////////////

public Admin selectAdmin(String username) {
	
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Admin admin = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(CHECK_ADMIN);

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		pstm.setString(1, username);
		pstm.execute();
		rs = pstm.getResultSet();
		
		if(rs.next()) {
			admin = new Admin();
			admin.setId(rs.getInt("admin_id"));
			admin.setUsername(rs.getString("admin_username"));
        }

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return admin;
}
/////////////////////////////////////////

}
