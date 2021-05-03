package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configurations.DatabaseConnection;

public class Report {
	

	private static Report Reportervice;
	private static Statement stmt;
	private static ResultSet rs;

	private Report() {
	}

	// singlton design
	public static Report getService() throws SQLException {
		if (Reportervice == null) {
			stmt = DatabaseConnection.getConeection().createStatement();
			;
			Reportervice = new Report();
		}
		return Reportervice;
	}

	// get avg of salers of employees
	public String getAvgSal() {
		String q = "SELECT AVG(e.salary)FROM employee e";
		String result = "";
		try {
			rs = stmt.executeQuery(q);
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (SQLException e) {
			result = e.getMessage();
		}

		return result;
	}

	// get number of employees
	public String getNumberOfemployee() {
		String res = "";
		String q = "SELECT COUNT(*)FROM employee e";
		try {
			rs = stmt.executeQuery(q);
			if (rs.next()) {
				res = rs.getString(1);
			}
		} catch (SQLException e) {
			res = e.getMessage() + "";
		}

		return res;
	}

	// max salary of employees
	public String getMaxSalary() {
		String res = "";
		String q = "SELECT e.ename,e.salary FROM employee e WHERE e.salary = (SELECT MAX(e.salary)FROM employee e)";
		try {
			rs = stmt.executeQuery(q);
			while (rs.next()) {
				res += rs.getString(1) + ",";
			}
		} catch (SQLException e) {
			res = e.getMessage();
		}

		return res;
	}

	// get expired drugs
	public String getExpiredDr() {
		String res = "";
		String q = "select * from drug d where d.end_date < now();";
		try {
			rs = stmt.executeQuery(q);
			while (rs.next()) {
				res += rs.getString(1)+ " " + rs.getString(9)+ " " + rs.getString(11) + "\n";
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return res;
	}

	// get profit
	public String getProfit() {
		String res = "";
		String q = "(select sum((d.selling_price-d.buying_price)*s.quantity) As 'profit'\r\n"
				+ "from drug d,sells s\r\n" + "where d.id=s.drugID);\r\n" + "\r\n" + "";

		try {
			rs = stmt.executeQuery(q);
			if (rs.next()) {
				res = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res = e.getMessage() + "";
		}

		return res;
	}

	

	
	

}
