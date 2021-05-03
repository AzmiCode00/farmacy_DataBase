package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


import models.*;
import configurations.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DrugService {
	
	static Statement stmt ;
	private static DrugService drugService;
	private static ObservableList<Drug> list;
	
	private  DrugService() {
		list = FXCollections.observableArrayList();
		try {
			stmt = DatabaseConnection.getConeection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static DrugService getService() {
		if (drugService == null) {
			drugService = new DrugService();
		}
		return drugService;
	}

	public ObservableList<Drug> getAllDrugs() {
		try {
			list.clear();
			ResultSet rs = stmt.executeQuery("select * from drug");
			while (rs.next()) {
				list.add(new Drug(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						Double.parseDouble(rs.getString(7)), Double.parseDouble(rs.getString(8)), rs.getString(9),
						Integer.parseInt(rs.getString(10)), rs.getString(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String getInfo(String id) {
		String name = "ID: "+id+" Not Found";
		String query =("SELECT Dname FROM drug where id = '"+id+"';");
		try {
			Statement stmt = (Statement) DatabaseConnection.getConeection().createStatement();
			ResultSet	rs = stmt.executeQuery(query);
			while(rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error : 404", JOptionPane.INFORMATION_MESSAGE);
		}
		return name;
	}
	
	public String delete(String  id)  {
		String result="";	
		String q="delete from drug where id="+id;
		try {
			stmt.execute(q);
			result="Deleted";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=e.getMessage()+"";
		}
		return result;
	}
	
	public String Insert(String did, String fid, String number, String production, String dose, String descripe, String selling_price, String buying_price, String Dname, String quaninty, String end_date) {
		String result="";	
		String q="INSERT INTO pharmacy.`drug` (id, fac_id, Dnumber, production_date, dose, descripe, selling_price, buying_price, Dname, quaninty, end_date) VALUES ('"+did+"', '"+fid+"','"+number+"', '"+production+"', '"+dose+"', '"+descripe+"', '"+selling_price+"', '"+buying_price+"', '"+Dname+"', '"+quaninty+"', '"+end_date+"');";
		try {
			stmt.executeUpdate(q);
			result="Add";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=e.getMessage()+"";
		}
		
		return result;
	}
	


}