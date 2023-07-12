package dto_resource;



public class DTO_RentedMachines {

	private int worker_id;
	private int machine_id;
	private int units;
	private String rent_start;
	private String rent_end;
	
	public DTO_RentedMachines() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DTO_RentedMachines(int worker_id, int machine_id, int units, String rent_start, String rent_end) {
		super();
		this.worker_id = worker_id;
		this.machine_id = machine_id;
		this.units = units;
		this.rent_start = rent_start;
		this.rent_end = rent_end;
	}

	public int getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(int worker_id) {
		this.worker_id = worker_id;
	}

	public int getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public String getRent_start() {
		return rent_start;
	}

	public void setRent_start(String rent_start) {
		this.rent_start = rent_start;
	}

	public String getRent_end() {
		return rent_end;
	}

	public void setRent_end(String rent_end) {
		this.rent_end = rent_end;
	}

	@Override
	public String toString() {
		return "DTO_RentedMachines [worker_id=" + worker_id + ", machine_id=" + machine_id + ", units=" + units
				+ ", rent_start=" + rent_start + ", rent_end=" + rent_end + "]";
	}
	
	

	
}
