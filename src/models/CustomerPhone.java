package models;

public class CustomerPhone {
	private int id;
	private String pnumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPnumber() {
		return pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public CustomerPhone(int id, String pnumber) {
		super();
		this.id = id;
		this.pnumber = pnumber;
	}

}
