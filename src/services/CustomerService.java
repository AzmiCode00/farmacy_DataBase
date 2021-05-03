package services;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;

import configurations.DatabaseConnection;
import models.Customer;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;

public class CustomerService extends JFrame {

	private JPanel contentPane;
	private JTextField cnameTF;
	private JTextField CIDTF;
	private JTextField CAdressTF;
	private JTable table;
	static CustomerService frame = new CustomerService();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerService() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1081, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cnameTF = new JTextField();
		cnameTF.setText("customerName");
		cnameTF.setBounds(24, 45, 86, 20);
		contentPane.add(cnameTF);
		cnameTF.setColumns(10);

		JLabel lblCustomersName = new JLabel("Customer Name");
		lblCustomersName.setBounds(24, 23, 102, 14);
		contentPane.add(lblCustomersName);

		CIDTF = new JTextField();
		CIDTF.setText("5");
		CIDTF.setColumns(10);
		CIDTF.setBounds(136, 45, 86, 20);
		contentPane.add(CIDTF);

		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(136, 23, 78, 14);
		contentPane.add(lblCustomerId);

		CAdressTF = new JTextField();
		CAdressTF.setText("Ramallah");
		CAdressTF.setColumns(10);
		CAdressTF.setBounds(244, 45, 86, 20);
		contentPane.add(CAdressTF);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(244, 23, 78, 14);
		contentPane.add(lblAddress);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.BLUE);
		scrollPane.setBounds(24, 117, 886, 226);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name", "ID", "Adress" }));
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		scrollPane.setViewportView(table);

		JButton ADDBT = new JButton("Add customer");
		ADDBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseConnection.getConeection();

				Statement stmt = null;
				try {
					stmt = (Statement) DatabaseConnection.getConeection().createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					int rs = stmt.executeUpdate("INSERT INTO pharmacy.`customer` (id, cname, address) VALUES ('"
							+ CIDTF.getText() + "', '" + cnameTF.getText() + "', '" + CAdressTF.getText() + "');");
					Object col[] = { "ID", "Name ", "adress" };
					DefaultTableModel demoList = new DefaultTableModel(col, 0);

					ResultSet rs1 = stmt.executeQuery("select * from Customer");

					while (rs1.next()) {
						// (Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)}

						demoList.addRow(new Object[] { rs1.getString(1), rs1.getString(2), rs1.getString(3) });

					}
					table.setModel(demoList);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error : 404", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		ADDBT.setBounds(750, 36, 160, 23);
		contentPane.add(ADDBT);

		JButton btnNewButton = new JButton("Delete customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Statement stmt = null;
				try {
					stmt = (Statement) DatabaseConnection.getConeection().createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {

					int rs = stmt.executeUpdate("DELETE FROM pharmacy.`customer` WHERE (id = '"
							+ table.getValueAt(table.getSelectedRow(), 0) + "');");
					Object col[] = { "ID", "Name ", "adress" };
					DefaultTableModel demoList = new DefaultTableModel(col, 0);

					ResultSet rs1 = stmt.executeQuery("select * from Customer");

					while (rs1.next()) {
						// (Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)}

						demoList.addRow(new Object[] { rs1.getString(1), rs1.getString(2), rs1.getString(3) });

					}
					table.setModel(demoList);

				} catch (SQLException e) {

					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error : 404", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(750, 62, 160, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Show all");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				DatabaseConnection.getConeection();

				Statement stmt = null;
				try {
					stmt = (Statement) DatabaseConnection.getConeection().createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					Object col[] = { "ID", "Name ", "adress" };
					DefaultTableModel demoList = new DefaultTableModel(col, 0);

					ResultSet rs = stmt.executeQuery("select * from Customer");

					while (rs.next()) {
						// (Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)}

						demoList.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) });

					}
					table.setModel(demoList);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error : 404", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(750, 86, 160, 20);
		contentPane.add(btnNewButton_1);

		JButton ADDBT_1 = new JButton("Search for a customer");
		ADDBT_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter the customer name ");
				DatabaseConnection.getConeection();

				Statement stmt;
				try {
					stmt = (Statement) DatabaseConnection.getConeection().createStatement();

					Object col[] = { "ID", "Name ", "adress" };
					DefaultTableModel demoList = new DefaultTableModel(col, 0);

					ResultSet rs = stmt.executeQuery("SELECT * FROM pharmacy.customer WHERE cname = '" + name + "'; ");

					while (rs.next()) {
						// (Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)}

						demoList.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) });

					}
					table.setModel(demoList);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error : 404",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		ADDBT_1.setBounds(750, 11, 160, 23);
		contentPane.add(ADDBT_1);

		JButton back = new JButton("back");
		back.setFont(new Font("Tahoma", Font.PLAIN, 19));
		back.setForeground(Color.RED);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frame.setVisible(false);

			}
		});
		back.setBounds(909, 413, 146, 40);
		contentPane.add(back);
	}
}