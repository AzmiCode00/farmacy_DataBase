package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configurations.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Factory;

public class FactoryServices {

	static Statement stmt ;
	private static FactoryServices facService;
	private static ObservableList<Factory> list;

	private FactoryServices() {
		list = FXCollections.observableArrayList();
		try {
			stmt = DatabaseConnection.getConeection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static FactoryServices getService() {
		if (facService == null) {
			facService = new FactoryServices();
		}
		return facService;
	}

	public ObservableList<Factory> getAllFacories() {
		try {
			list.clear();
			ResultSet rs = stmt.executeQuery("select * from factory");
			while (rs.next()) {

				list.add(new Factory(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3),
						rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String delete(String  id)  {
		String result="";	
		String q="delete from factory where id="+id;
		try {
			stmt.execute(q);
			result="Deleted";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=e.getMessage()+"";
		}
		
		return result;
	}
	
	public String Insert(String id, String name, String phone, String address) {
		String result="";	
		String q="INSERT INTO pharmacy.`factory` (id, Fname, address, Fnumber) VALUES ('"+id+"', '"+name+"', '"+address+"', '"+phone+"');";
		try {
			stmt.executeUpdate(q);
			result="Add";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=e.getMessage()+"";
		}
		
		return result;
	}
	
	public String Search(String id) {
		String result="";	
		String q="SELECT * FROM pharmacy.factory where id = '"+id+"';";
		try {
			
			ResultSet rs  =  stmt.executeQuery(q);
			result= rs.getString(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=e.getMessage()+"";
		}
		
		return result;
	}
	
	
	// return name ,given id
		public String getInfo(String id) throws SQLException {
			String name="";
			String query =("select f.Fname from factory f where f.id='"+id+"';");
			PreparedStatement sqlQuery=(PreparedStatement) DatabaseConnection.getConeection().prepareStatement(query);
					
		ResultSet	rs=sqlQuery.executeQuery();
		while(rs.next()) {
			name = rs.getString(1);
		}
		return name;
		}
	
	

 
	
}
