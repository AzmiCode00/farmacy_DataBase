package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import configurations.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CustomerPhone;
import models.Factory;

public class CustomerPhoneService {
	
	static ObservableList<CustomerPhone> list;//javafx 
	 static ResultSet rs;
	
	static Statement stmt ;
	private static CustomerPhoneService facService;

	private CustomerPhoneService() {
		list = FXCollections.observableArrayList();
		try {
			stmt = DatabaseConnection.getConeection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static CustomerPhoneService getService() {
		if (facService == null) {
			facService = new CustomerPhoneService();
		}
		return facService;
	}
	
	
	public  ObservableList<CustomerPhone> getCustomersPhone() {
		try {
			list.clear();// javafx gui
			
			rs = stmt.executeQuery("select * from cus_phone");
			while (rs.next()) {

				list.add(new CustomerPhone(Integer.parseInt(rs.getString(1)), rs.getString(2)));
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// insert;;
	public String insert(String id,String phone) {
		String res="";

		if (phone.matches("[0-9]{10}") || phone.matches("[0-9]{9}") || phone.matches("[0-9]{7}")) {

			try {
				if (phone.matches("[0-9]{10}") || phone.matches("[0-9]{9}") || phone.matches("[0-9]{7}")) {
					String sql = "INSERT INTO `pharmacy`.`cus_phone` (`cus_id`, `CPnumber`) VALUES ('" + id
							+ "', '" + phone + "')";
					int rs;

					rs = ((Statement) stmt).executeUpdate(sql);
					res="added";

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
				res="not added!";

			}

		} else {
			new Alert(AlertType.WARNING, "The phone number must be from ten numbers!!!").show();

		}
		return res;
	}
	
	
	public String delete(String id,String phone) {
		String res="";

		try {
			

			String sql = "DELETE FROM `pharmacy`.`cus_phone` WHERE (`cus_id` = '" + id
					+ "') and (`CPnumber` = '" + phone + "')";
			int rs;

			rs = ((Statement) stmt).executeUpdate(sql);
			res="Deleted";

		} catch (SQLException e1) {
			res=e1.getMessage()+"";
		}
		return res;
	}
	
	
	
	public String search() {
		String res="";
		
		try {
			TableView<CustomerPhone> cusPh = new TableView<>();
			Label emty = new Label("no customers's phone");

			TableColumn<CustomerPhone, Integer> id = new TableColumn<>("Id");
			id.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<CustomerPhone, String> number = new TableColumn<>("Phone Number");
			number.setCellValueFactory(new PropertyValueFactory<>("pnumber"));

			id.setPrefWidth(130);
			number.setPrefWidth(150);

			cusPh.getColumns().addAll(id, number);
			String id2 = JOptionPane.showInputDialog("Enter the customer ID");

			// SELECT * FROM pharmacy.cus_phone WHERE cus_id=3;======> to search for
			// custamer number using id
			String sql = "SELECT * FROM `pharmacy`.`cus_phone` WHERE (`cus_id` = '" + id2 + "')";
			ResultSet rs;

			rs = ((Statement) stmt).executeQuery(sql);
			ObservableList<CustomerPhone> list1 = getCustomersPhone(Integer.parseInt(id2));

			cusPh.setItems(list1);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		return res;
		
	}
	
	private  ObservableList<CustomerPhone> getCustomersPhone(int id) {
		try {
			list.clear();// javafx gui

			rs = ((Statement) stmt)
					.executeQuery("SELECT * FROM `pharmacy`.`cus_phone` WHERE (`cus_id` = '" + id + "')");
			while (rs.next()) {

				list.add(new CustomerPhone(Integer.parseInt(rs.getString(1)), rs.getString(2)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	


}
