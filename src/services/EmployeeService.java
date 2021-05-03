package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// inset,delte,alter,print,find
import configurations.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Employee;

public class EmployeeService {
	private static EmployeeService employeeService;
	private static ObservableList<Employee> list;//javafx
	private static Statement stmt;
	private static ResultSet rs;


	private EmployeeService() {
		list = FXCollections.observableArrayList();
	}

	//singlton design
	public static EmployeeService getService() throws SQLException {
		if (employeeService == null) {
			stmt = DatabaseConnection.getConeection().createStatement();
			
			employeeService = new EmployeeService();
		}
		return employeeService;
	}

	// get all employees//
	public ObservableList<Employee> getAllEmployees() {
		try {
			list.clear();// javafx gui
			rs = stmt.executeQuery("select * from employee");
			while (rs.next()) {

				list.add(new Employee(rs.getString(1), rs.getString(2),
						Double.parseDouble(rs.getString(3)), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// return name ,given id
	public String getInfo(String id) throws SQLException {
		String name="",gender="",dateOfBirth="" ,salary="",addres="";
		

		String query =("select e.ename,e.gender,e.date_of_birth , e.salary ,e.addres  from employee e where e.id="+id);
		PreparedStatement sqlQuery=DatabaseConnection.getConeection().prepareStatement(query);
				
		rs=sqlQuery.executeQuery();
		
		while(rs.next()) {
		name=rs.getString(1);
		gender=rs.getString(2);
		dateOfBirth=rs.getString(3);
		salary=rs.getString(4);
		addres=rs.getString(5);
		}
		
		return  "Name:"+ name + ", Gender: " + gender + ",Date of birth : " + dateOfBirth + " ,salary :" + salary +",Addres :" + addres;
	}
	// return id,name ,given phone number
	public String getIdAndNameByPhoneNumber(String phoneNumber)  {
		String result="";
		String name="",id="";
		String q=" select e.id ,e.ename from employee e,phone_number p where e.id=p.emp_id and p.pnumber="+phoneNumber;
		PreparedStatement qS = null;
		try {
			qS = DatabaseConnection.getConeection().prepareStatement(q);
		} catch (SQLException e) {
			result=e.getMessage();
		}
		try {
			rs=qS.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=e.getMessage();
		}
		try {
			while(rs.next()) {
				id=rs.getString(1);
				name=rs.getString(2);

				
			}
			result="Id : " + id + " , Name:"+ name;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=e.getMessage();
		}
		
		return result;
	}
	
	// insert new Employee
	public String insert(String i,String n,String s,String a,String g,String d)  {
		String result="";
		String q="insert into employee (id,ename,salary,addres,gender,date_of_birth) VALUES ('"+i+"', '"+n+"' ,'"+s+"','"+a+"','"+g+"','"+d+"'  )";  
		
		
		 
		try {
			stmt.executeUpdate(q);
			result="employee added";
		} catch (SQLException e) {
			result=e.getMessage();
			
		}
	
		
		return result;
	}

	// delete employee ,given id(delete or search just by id)
	public String deleteEmployee(String id) {
		String result="";
		String q1= "delete from phone_number p where  p.emp_id="+id;
		String q2="delete from employee e where e.id="+id;
		
		try {
			stmt.execute(q1);
			stmt.execute(q2);
			result="employee removed";
		} catch (SQLException e) {
			result=e.getMessage();
			
		}
		
		return result;
	}
	
	//get average of salary
	public String getAvgSal() {
		String q="SELECT AVG(e.salary)FROM employee e";
		String result="";
		try {
			rs=stmt.executeQuery(q);
			if(rs.next()) {
				result=rs.getString(1);
			}
			
		} catch (SQLException e) {
			result=e.getMessage();
		}
		
		
		return result;
	}
	//remove all employees
	public String deleteAll() {
		String result="";
		String q="delete from employee e where e.id>-1 ";
		try {
			stmt.execute(q);
			result="all employees removed!";
		} catch (SQLException e) {
			result=e.getMessage();
		}
		
		
		return result;
	}
	
	

	
	
	
}
