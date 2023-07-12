package model;

public class Machine {

	private int machine_id;
	private String machine_type;
	private int stock;
	private String img_path;
	
	public Machine() {
		super();
		
	}

	public Machine(int machine_id, String machine_type, int stock, String img_path) {
		super();
		this.machine_id = machine_id;
		this.machine_type = machine_type;
		this.stock = stock;
		this.img_path = img_path;
	}

	public Machine(String machine_type, int stock, String img_path) {
		super();
		this.machine_type = machine_type;
		this.stock = stock;
		this.img_path = img_path;
	}

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}

	public String getMachine_type() {
		return machine_type;
	}

	public void setMachine_type(String machine_type) {
		this.machine_type = machine_type;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	@Override
	public String toString() {
		return "Machine [machine_id=" + machine_id + ", machine_type=" + machine_type + ", stock=" + stock + "]";
	}

	
	
	
	
}
