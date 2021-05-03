package models;

public class Employee {
	private String id;
	private String name;
	private double salary;
	private String addres;
	private String gender;
	private String datetOfBirth;

	public Employee(String id, String name, double salary, String addres, String dender, String datetOfBirth) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.addres = addres;
		this.gender = dender;
		this.datetOfBirth = datetOfBirth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDatetOfBirth() {
		return datetOfBirth;
	}

	public void setDatetOfBirth(String datetOfBirth) {
		this.datetOfBirth = datetOfBirth;
	}

}
