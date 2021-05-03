package models;

public class Factory {
	private long id;
	private String name;
	private String addres;
	private String phone;
	
	
	public Factory(long id, String name, String addres, String phone) {
		this.id = id;
		this.name = name;
		this.addres = addres;
		this.phone=phone;
	}
	
	


	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddres() {
		return addres;
	}


	public void setAddres(String addres) {
		this.addres = addres;
	}


	
	
	
	
	
	

}
