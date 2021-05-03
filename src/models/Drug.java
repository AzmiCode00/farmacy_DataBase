package models;

public class Drug {
	private long id;
	private long empId;
	private String number;
	private String proDate;
	private String dose;
	private String discripe;
	private double sellPrice;
	private double buyPrice;
	private String name;
	private long quantity;
	private String endDate;
	private int FactoryID;

	//	    		 Drug d2 = new Drug(Integer.parseInt(did), Integer.parseInt(fid), numDr, production, dose, descripe, Double.parseDouble(selling_price), Double.parseDouble(buying_price), Dname, Integer.parseInt(quaninty), end_date)
	
	
	public Drug(long id,int fid, String number, String proDate, String dose, String discripe, double sellPrice,
			double buyPrice, String name, long quantity, String endDate) {
		this.id = id;
		this.FactoryID=fid;
		this.number = number;
		this.proDate = proDate;
		this.dose = dose;
		this.discripe = discripe;
		this.sellPrice = sellPrice;
		this.buyPrice = buyPrice;
		this.name = name;
		this.quantity = quantity;
		this.endDate = endDate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getEmpId() {
		return empId;
	}


	public void setEmpId(long empId) {
		this.empId = empId;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getProDate() {
		return proDate;
	}


	public void setProDate(String proDate) {
		this.proDate = proDate;
	}


	public String getDose() {
		return dose;
	}


	public void setDose(String dose) {
		this.dose = dose;
	}


	public String getDiscripe() {
		return discripe;
	}


	public void setDiscripe(String discripe) {
		this.discripe = discripe;
	}


	public double getSellPrice() {
		return sellPrice;
	}


	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}


	public double getBuyPrice() {
		return buyPrice;
	}


	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getQuantity() {
		return quantity;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public int getFactoryID() {
		return FactoryID;
	}


	public void setFactoryID(int factoryID) {
		FactoryID = factoryID;
	}
	
	
	
	
	
	
	
	
	

}






