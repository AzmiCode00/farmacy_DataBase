package services;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import configurations.*;

import configurations.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class SalesService {

	private JFrame frmSales;
	private JTextField EID;
	private JTextField CID;
	private JTextField DID;
	private JTextField Quantity;

	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesService window = new SalesService();
					window.frmSales.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SalesService() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSales = new JFrame();
		frmSales.setTitle("Sales");
		frmSales.setSize(new Dimension(1070, 470));
		frmSales.setResizable(false);
		frmSales.getContentPane().setForeground(Color.GREEN);
		frmSales.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		frmSales.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("sell");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DatabaseConnection.getConeection();

				Statement stmt = null;
				try {
					stmt = (Statement) DatabaseConnection.getConeection().createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String date =(LocalDate.now().format(formatter).trim());
					int rs ;
					ResultSet rs1 = stmt.executeQuery("select quaninty from drug where drug.id = '"+DID.getText()+"'");
					if(rs1.next()){
						   
						
					if(Integer.parseInt(rs1.getString(1).trim()) +1 > Integer.parseInt(Quantity.getText())){
						//SELECT * FROM drug  where drug.end_date < now() ;
						rs1 =  stmt.executeQuery("SELECT * FROM drug  where drug.end_date < now() and drug.id = '"+DID.getText()+"'");
						if(rs1.next() && rs1.getBoolean(1)){
							;
							
								throw new SQLException("EXPIRED Drug ");
							
							
						}
					rs = stmt.executeUpdate("INSERT INTO `pharmacy`.`sells` ( `drugID`, `employeeID`, `quantity`, `dateOfsale`) VALUES ( '"+DID.getText()+"', '"+EID.getText()+"' , '"+Quantity.getText()+"', '"+date+"');");
					
					rs = stmt.executeUpdate(	"INSERT INTO `pharmacy`.`takes` (`drugID`, `customerID`,  `dateOFsale`) VALUES ('"+DID.getText()+"' , '"+CID.getText()+"' ,  '"+date+"');");
				
					rs = stmt.executeUpdate("UPDATE `pharmacy`.`drug` SET quaninty = quaninty -"+Quantity.getText()+" WHERE (`id` = '"+DID.getText()+"');");
					
					}
					else {
						 
						JOptionPane.showMessageDialog(null,"there is only " + rs1.getString(1) + "   !  left please choose a lesser value ", "Error : 404  ", JOptionPane.INFORMATION_MESSAGE);

					
					}
					}
					Object col [] = {"drugID" , "customerID " , "SID" ,"dateOFsale" , "employeeID" , "quantity" } ;
					DefaultTableModel demoList = new DefaultTableModel(col, 0) ;
					 
					 rs1 = stmt.executeQuery("SELECT sells.drugID , takes.customerID , sells.SID  , sells.dateOfsale ,  sells.employeeID,  sells.quantity FROM sells ,takes WHERE  sells.SID=takes.SID");
					
					while (rs1.next()) {
						//(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)}
						
						demoList.addRow(new Object [] {rs1.getString(1), rs1.getString(2) , rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6)});
						
					}
					table_1.setModel(demoList);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error : 404", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(46, 345, 174, 49);
		frmSales.getContentPane().add(btnNewButton);
		
		EID = new JTextField();
		EID.setBounds(46, 73, 174, 20);
		frmSales.getContentPane().add(EID);
		EID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("employee ID");
		lblNewLabel.setBounds(46, 48, 109, 14);
		frmSales.getContentPane().add(lblNewLabel);
		
		JLabel lblCustomerId = new JLabel("customer ID ");
		lblCustomerId.setBounds(230, 48, 109, 14);
		frmSales.getContentPane().add(lblCustomerId);
		
		CID = new JTextField();
		CID.setColumns(10);
		CID.setBounds(230, 73, 174, 20);
		frmSales.getContentPane().add(CID);
		
		JLabel lblDrugid = new JLabel("DrugID");
		lblDrugid.setBounds(414, 48, 109, 14);
		frmSales.getContentPane().add(lblDrugid);
		
		DID = new JTextField();
		DID.setColumns(10);
		DID.setBounds(414, 73, 174, 20);
		frmSales.getContentPane().add(DID);
		
		JLabel lblQuantity = new JLabel("quantity");
		lblQuantity.setBounds(598, 48, 109, 14);
		frmSales.getContentPane().add(lblQuantity);
		
		Quantity = new JTextField();
		Quantity.setColumns(10);
		Quantity.setBounds(598, 73, 174, 20);
		frmSales.getContentPane().add(Quantity);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 119, 910, 192);
		frmSales.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Statement stmt = null;
				try {
					stmt = (Statement) DatabaseConnection.getConeection().createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					
					int rs = stmt.executeUpdate("DELETE FROM `pharmacy`.`sells` WHERE (`SID` = '"+table_1.getValueAt(table_1.getSelectedRow(), 2)+"');");
					rs = stmt.executeUpdate("DELETE FROM `pharmacy`.`takes` WHERE (`SID` = '"+table_1.getValueAt(table_1.getSelectedRow(), 2)+"');");
					rs = stmt.executeUpdate("UPDATE `pharmacy`.`drug` SET quaninty = quaninty +"+table_1.getValueAt(table_1.getSelectedRow(), 5)+" WHERE (`id` = '"+table_1.getValueAt(table_1.getSelectedRow(), 0)+"');");

					Object col [] = {"drugID" , "customerID " , "SID" ,"dateOFsale" , "employeeID" , "quantity" } ;					DefaultTableModel demoList = new DefaultTableModel(col, 0) ;
					 
					ResultSet  rs1 = stmt.executeQuery("SELECT sells.drugID , takes.customerID , sells.SID  , sells.dateOfsale ,  sells.employeeID,  sells.quantity FROM sells ,takes WHERE  sells.SID=takes.SID");
					
					while (rs1.next()) {
						//(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)}
						
						demoList.addRow(new Object [] {rs1.getString(1), rs1.getString(2) , rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6)});
						
					}
					table_1.setModel(demoList);
					
				
				
				} catch (SQLException e) {
					
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error : 404", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(230, 345, 174, 49);
		frmSales.getContentPane().add(btnDelete);
		
		JButton btnSearchByCid = new JButton("search by CID");
		btnSearchByCid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ID = JOptionPane.showInputDialog("Enter the customers ID  ");
				DatabaseConnection.getConeection();

				Statement stmt;
				try {
					stmt = (Statement) DatabaseConnection.getConeection().createStatement();
				
					Object col [] = {"drugID" , "customerID " , "SID" ,"dateOFsale" , "employeeID" , "quantity" } ;					DefaultTableModel demoList = new DefaultTableModel(col, 0) ;
					DefaultTableModel demoList1 = new DefaultTableModel(col, 0) ;
					 
					ResultSet  rs = stmt.executeQuery("SELECT sells.drugID , takes.customerID , sells.SID  , sells.dateOfsale ,  sells.employeeID,  sells.quantity FROM sells ,takes WHERE  sells.SID=takes.SID AND customerID = '"+ID+"'");
					
					while (rs.next()) {
						//(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)}
						
						demoList1.addRow(new Object [] {rs.getString(1), rs.getString(2) , rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
						
					}
					table_1.setModel(demoList1);
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error : 404", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
				
				
				
				
				
			}
		});
		btnSearchByCid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSearchByCid.setBounds(414, 345, 174, 49);
		frmSales.getContentPane().add(btnSearchByCid);
		
		JButton btnShowAll = new JButton("show all");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object col [] = {"drugID" , "customerID " , "SID" ,"dateOFsale" , "employeeID" , "quantity" } ;
				DefaultTableModel demoList = new DefaultTableModel(col, 0) ;
				Statement stmt;
				try {
					stmt = (Statement) DatabaseConnection.getConeection().createStatement();
				
					ResultSet rs1 = stmt.executeQuery("SELECT sells.drugID , takes.customerID , sells.SID  , sells.dateOfsale ,  sells.employeeID,  sells.quantity FROM sells ,takes WHERE  sells.SID=takes.SID");
				
					while (rs1.next()) {
						//(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)}
						
						demoList.addRow(new Object [] {rs1.getString(1), rs1.getString(2) , rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6)});
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error : 404", JOptionPane.INFORMATION_MESSAGE);
				}
				table_1.setModel(demoList);
			}
		});
		btnShowAll.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShowAll.setBounds(598, 345, 174, 49);
		frmSales.getContentPane().add(btnShowAll);
		
		JButton btnClose = new JButton("close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSales.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClose.setBounds(782, 345, 174, 49);
		frmSales.getContentPane().add(btnClose);
	}
}
