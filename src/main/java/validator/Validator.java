package validator;

import dao.DAO;
import model.Admin;
import model.User;

public class Validator {
	
	public static boolean userExists(String username) {
		DAO dao = new DAO();
		User user = null;
		try {
			user = dao.selectWorker(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(user != null)
			return true;
		return false;

	}

	public static boolean isAdmin(String username) {
		DAO dao = new DAO();
		Admin admin = null;
		try {
			admin = dao.selectAdmin(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(admin != null)
			return true;
		return false;
	}
	
}
